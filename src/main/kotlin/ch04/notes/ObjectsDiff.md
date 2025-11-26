**既然 companion object 和 普通嵌套 object 都是“嵌套单例”，为啥 Kotlin 还要区分成两种？**

这个问题问得特别好，其实就是在问：

> 既然 **companion object** 和 普通嵌套 **object** 都是“嵌套单例”，
>  为啥 Kotlin 还要区分成两种

我给你一个直观结论先记在脑子里：

> **companion object = 这个类的“静态 API 区域”**
>  **嵌套 object = 只是刚好写在类里面的一个单例对象**

然后我们再往下拆。

------

## 1. 共同点：都是“嵌套单例类”

你刚总结的几点都是对的：

- 都是写在类里面，JVM 上都是“静态嵌套类 + 一个唯一实例”
- 构造器对外都是不可见的（语义上单例）
- 用户都不能 `Person.Companion()` / `Person.SomeObject()` 去 new

所以在 **JVM 实现层**，它们长得非常像，这一点你已经通过反编译看到了。

------

## 2. 真正的区别：**语义角色 + 使用方式不一样**

### 2.1 companion object：这个类对外的“静态界面”

典型写法：

```kotlin
class Person private constructor(val name: String) {

    companion object {
        const val DEFAULT_AGE = 0
        fun fromName(name: String) = Person(name)
    }
}
```

特点：

1. **每个类最多只能有一个 companion object**。
    就像“一个类只有一个 static 成员区域”。

2. Kotlin 给它很多**特殊语法照顾**：

   - 在 Kotlin 代码里可以直接写：

     ```kotlin
     Person.fromName("Bob")
     Person.DEFAULT_AGE
     ```

     不用写 `Person.Companion.xxx`。

   - 对 Java 互操作时，可以配合 `@JvmStatic`、`@JvmField`，把里边的方法/字段映射成真正的 `static`：

     ```kotlin
     companion object {
         @JvmStatic fun parse(...) { ... }
         @JvmField val LOG = Logger.getLogger(...)
     }
     ```

     Java 侧就能 `Person.parse()` / `Person.LOG` 这样用。

3. 阅读代码时你一看到 `companion object`，大脑里自然会想：

   > “这里放的是这个类的工厂方法、常量、工具函数……也就是**这个类的静态 API**。”

换句话说：
 **companion object 是“概念上属于 Person 这个类型”的东西的家。**

------

### 2.2 普通嵌套 object：只是一个“顺便放在类里的单例工具”

还是拿你之前的例子：

```kotlin
data class Person(val name: String, var age: Int) {
    object NameComparator : Comparator<Person> {
        override fun compare(p1: Person, p2: Person): Int =
            p1.name.compareTo(p2.name)
    }
}
```

特点：

1. **一个类里可以有很多个嵌套 object**：

   ```kotlin
   class Person {
       object NameComparator : Comparator<Person> { ... }
       object AgeComparator : Comparator<Person> { ... }
       object Cache { ... }
   }
   ```

2. 访问方式总是带着自己的名字：

   ```kotlin
   persons.sortedWith(Person.NameComparator)
   persons.sortedWith(Person.AgeComparator)
   ```

   Kotlin 不会对它做 “Person.xxx” 这种静态糖。

3. 语义上更像是：

   > “这个类内部顺手定义了几个单例工具 / 策略对象，刚好写在类里面而已，
   >  用的时候要指明名字。”

所以：

- `Person.NameComparator` → **是一个具体的比较器对象**；
- `Person.Companion` → **是“Person 类型本身”的那一块静态空间**。

这两个层次不一样。

------

## 3. 为啥不只保留一种？——语言设计上的考虑

你可以把 Kotlin 的设计理解成三层“和类相关的东西”：

```text
1. 完全独立的工具：顶层函数 / 顶层对象
2. 属于某个类，但和实例无关的：companion object
3. 放在类里的其他单例工具：嵌套 object
```

### 3.1 如果只有“嵌套 object”，会有什么问题？

假设 Kotlin 一开始就没有 companion，只有：

```kotlin
class Person private constructor(val name: String) {
    object StaticArea {
        const val DEFAULT_AGE = 0
        fun fromName(name: String) = Person(name)
    }
}
```

那用的时候要么写：

```kotlin
Person.StaticArea.fromName("Bob")
```

要么靠各种命名约定（Static、Factory、Utils……），语义很散，**没有谁是“官方的静态区域”**。

而 Kotlin 想要一个很明确的东西来替代 Java 的 `static`，因此专门造了一个名字：

> **「伴生对象 companion」= 这个类的“静态伴侣”，自然地挂在类型下面。**

于是：

- 语义更清晰：看到 `companion object` 就知道“这块是静态 API 区域”；
- 可以加语言级别特性（`A.fromXxx()` 语法、反射里的 `KClass.companionObject` 等）；
- 对 Java 互操作做特殊优化（`@JvmStatic`、`@JvmField`）。

### 3.2 如果只有 companion，没有普通嵌套 object，会怎样？

那像这种“一个类内部定义多个单例策略对象”的写法就会很别扭：

```kotlin
class Person {
    // 如果只有 companion，就只能在 companion 里塞各种 Comparator？
    companion object {
        object NameComparator : Comparator<Person> { ... }
        object AgeComparator : Comparator<Person> { ... }
    }
}
```

用的时候变成：

```kotlin
persons.sortedWith(Person.NameComparator)
persons.sortedWith(Person.AgeComparator)
```

视觉上这些东西都挂在 companion 下，看起来好像都是“类的静态 API”，
 但其实只是几个具体的工具/对象，概念上是 **“类里的一员”**，而不是“类自己的静态接口”。

所以 Kotlin 保留了更普通、语义中性的：

```kotlin
object Something
```

你只要想表达“这里需要一个简单的单例对象”，无论它是顶层的、还是嵌套在类里的，都可以用 `object`。

------

## 4. 可以用一个具体例子来记住差异

```kotlin
class Person private constructor(val name: String) {

    // 1. 这个类对外的静态 API：
    companion object {
        const val DEFAULT_AGE = 0
        fun fromName(name: String) = Person(name)
    }

    // 2. 这个类内部用的一些单例工具：
    object NameComparator : Comparator<Person> { ... }
    object AgeComparator  : Comparator<Person> { ... }
}
```

- **从 Kotlin 调用：**

  ```kotlin
  // 静态 API
  val p = Person.fromName("Bob")
  println(Person.DEFAULT_AGE)
  
  // 单例工具
  persons.sortedWith(Person.NameComparator)
  persons.sortedWith(Person.AgeComparator)
  ```

- **从 Java 看形式：**

  ```java
  Person.Companion.fromName("Bob");    // 伴生对象
  Person.DEFAULT_AGE;                  // const 提升到外部类
  
  Person.NameComparator.INSTANCE;      // 普通嵌套 object 的实例字段
  ```

你会发现：

- **companion 负责“这个类的静态接口”**；
- **普通 object 负责“这个上下文里刚好需要的单例对象”**。

------

### 一句话总总结

> Kotlin 之所以有 **companion object** 和 **嵌套 object** 两种形式，
>  不是因为 JVM 实现必须不同，而是为了在**语义层面清晰地区分**：
>
> - 「这是一块 **类级别的静态 API**」（companion）
> - 「这只是 **放在类里面的一个单例工具 / 对象**」（普通 object）
>
> 这样读代码的人一眼就能看出：哪些东西是“这个类型对外的静态界面”，
>  哪些只是“附属的小工具对象”，不会混在一起变成一团 static