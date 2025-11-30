 * §5.4 使用 Java 函数式接口 §5.4.2 SAM 构造方法：显式地把 lambda 转换成函数式接口
 * P.131 代码清单 5.15 使用 SAM 构造方法来重用 listener 实例

    还有，尽管方法调用中的 SAM 转换一般都自动发生，但是当把 lambda 作为参数传给一个重载方法时，也有编译器不能选择正确的重载的情况。这时，使用显式的 SAM 构造方法是解决编译器错误的好方法。

------

你理解得没错：

> **平常情况**：如果只有一个参数是 “单抽象方法接口（SAM 接口）” 的重载版本，那我们在调用时直接写 lambda，编译器会自动做 SAM 转换，完全 OK。

书上这句“**当把 lambda 作为参数传给一个重载方法时，也有编译器不能选择正确的重载的情况**”，说的是**有多个重载都可以接受这个 lambda**，编译器不知道你到底想用哪一个，于是报“重载解析失败”。这时候就要用 **显式 SAM 构造方法** 或者强制类型来帮它选。

------

## 1. 最典型的场景：多个 SAM 接口重载

举个最简单的例子（伪造一个 Java/Kotlin 混合库的感觉）：

```kotlin
fun interface A {
    fun run()
}

fun interface B {
    fun run()
}

// 两个重载
fun doJob(action: A) { println("A version") }
fun doJob(action: B) { println("B version") }
```

这时如果你这样调用：

```kotlin
doJob { println("Hello") }
```

从 **lambda 的形状** 上看，`{ println("Hello") }` 完全可以被当成 `A`，也可以被当成 `B`，
 编译器就懵了：**两个重载都匹配，但没有一个“更具体”**，于是报错。

这时你就要**显式告诉它我要哪一个**：

```kotlin
// 写法 1：SAM 构造方法
doJob(A { println("Hello") })

// 写法 2：先存成带类型的变量，再传入
val a: A = { println("Hello") }
doJob(a)
```

这里 `A { ... }` 就是书里说的 **SAM 构造方法** —— 显式把 lambda 变成某个函数式接口的实例。

------

## 2. Kotlin 函数类型 + Java SAM 重载也会撞车

再构造一个更像实际库的例子：

```kotlin
fun interface OnDone {
    fun done()
}

// Kotlin 提供了两个重载
fun post(action: () -> Unit) { println("Kotlin fun type") }
fun post(action: OnDone)     { println("Java SAM type") }
```

调用：

```kotlin
post { println("done") }
```

从形状上看，这个 lambda 既能当成 `() -> Unit`，也能当成 `OnDone`，
 Kotlin 依然不知道该选哪个重载 —— 又是重载歧义。

解决方式同样是：

```kotlin
post(OnDone { println("done") })   // 明确用 SAM 版本
// 或者
val action: () -> Unit = { println("done") }
post(action)                       // 明确用函数类型版本
```

------

## 3. 书上那句话想强调什么？

> 尽管方法调用中的 SAM 转换一般都自动发生；
>  但是当把 lambda 作为参数传给一个**重载方法**时，也有编译器不能选择正确的重载的情况。
>  这时，使用**显式的 SAM 构造方法**是解决编译器错误的好方法。

翻译成人话就是：

- **只有一个候选（一个接受 SAM 的重载）时**：
   → 你写 `foo { ... }` 就完事，自动 SAM 转换。
- **有好几个候选，而且你的 lambda 外形都能匹配**：
   → 编译器不知道选谁，就报“重载冲突 / 不明确”。
   → 你就用 `SomeInterface { ... }` 这种 **显式 SAM 构造方法** 来帮它下定决心。

所以这句话对应的就是：

- **调用点只写了一个 lambda**
- **但方法有多个重载，每个重载的参数都是可以由 lambda 自动 SAM 的接口**

这种情况在你自己写 demo 时不常见，但在 Java 老库 / Kotlin 封装 Java 的库里挺容易遇到，比如：

- `ExecutorService.submit(Runnable task)`
- `ExecutorService.submit(Callable<T> task)`

在 Kotlin 里要是两个都可见，再写个：

```kotlin
executor.submit { println("hi") }   // 对 Runnable 和 Callable 都匹配
```

也会出现这种“编译器不知道你要哪一个”的场景。

------

## 简短总结帮你记

- ✅ **一个 SAM 重载**：直接 `foo { ... }`，自动 SAM，最舒服。
- ⚠️ **多个重载、都能吃掉这个 lambda**：编译器懵了，重载歧义。
- ✅ 解决办法：
  - 用 **SAM 构造方法**：`foo(MyListener { ... })`
  - 或者先赋给带具体类型的变量，再传进去。

你刚才那句理解可以保留，但加一句补充记忆：

> “平常只有一个接受 SAM 的重载时，可以直接传 lambda；
>  如果有多个都能接受这个 lambda，就要用 SAM 构造方法或显式类型来帮编译器选重载。”
