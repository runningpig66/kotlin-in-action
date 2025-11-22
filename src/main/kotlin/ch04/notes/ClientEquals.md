**以下两者的 equals() 实现方式有什么区别？**

package ch04.ex3_1_2_2_ObjectEqualityEquals1 包下的 4.3.1.2_2_ObjectEqualityEquals1.kt

```kotlin
override fun equals(other: Any?): Boolean {
        // 检查 "other" 是不是一个 Client:
        // Kotlin 中的 is 检查是 Java 中 instanceof 的模拟，用来检查一个值是否为一个指定的类型。
        if (other == null || other !is Client) {
            return false
        }
        // 检查对应的属性是否相等
        return name == other.name && postalCode == other.postalCode
    }
```

package ch04.ex3_1_2_2_ObjectEqualityEquals2 包下的 4.3.1.2_2_ObjectEqualityEquals2.kt

```kotlin
    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (javaClass != other?.javaClass) {
            return false
        }
        other as Client
        if (postalCode != other.postalCode) {
            return false
        }
        if (name != other.name) {
            return false
        }
        return true
    }
```


先说结论：**第二种写法更“严格”、更安全一些，特别是遇到子类时；第一种写法更简单，但有潜在的坑。**

我把两段稍微整理一下（逻辑等价）：

### 写法 A

```kotlin
override fun equals(other: Any?): Boolean {
    if (other == null || other !is Client)      // 1. 判空 + 判类型（is）
        return false

    // 2. 对应属性是否相等
    return name == other.name &&
           postalCode == other.postalCode
}
```

### 写法 B

```kotlin
override fun equals(other: Any?): Boolean {
    if (this === other) return true             // 1. 引用相等，快速返回（同一个对象）

    if (javaClass != other?.javaClass)          // 2. 要求“运行时类”完全相同
        return false

    other as Client                             // 3. 明确向下转型（已保证安全）

    if (postalCode != other.postalCode) return false
    if (name != other.name) return false

    return true
}
```

------

## 1. 对“类型”的要求不一样

### 写法 A：允许子类参加比较（`is Client`）

```kotlin
other !is Client
```

- `is Client` 用的是 **“是这个类型或其子类型”** 的语义。

- 所以如果你以后写了：

  ```kotlin
  class VipClient(name: String, postalCode: Int, val level: Int) : Client(name, postalCode)
  ```

  那么：

  ```kotlin
  val c: Client = Client("A", 1)
  val v: Client = VipClient("A", 1, 10)
  
  c == v   // 可能是 true（只比 name 和 postalCode）
  ```

- 这时候就会出现经典问题：**对称性被破坏**。
   如果 `VipClient` 重写了自己的 `equals`，可能 `v == c` 的结果和 `c == v` 不一致。

> 所以写法 A 看起来简单，其实对子类不太友好。

### 写法 B：要求“运行时类完全一致”（`javaClass`）

```kotlin
if (javaClass != other?.javaClass) return false
```

- 这里比较的是 **`javaClass`**，也就是真正的运行时类：
  - `Client::class.java` 和 `VipClient::class.java` 不相等。
- 这样就保证：**只有同一种具体类的对象才有资格互相比较**。

好处：

- 不会出现“父类只看一部分字段，子类多了字段”的那种怪异情况。
- 更符合《Effective Java》里推荐的做法：除非你非常清楚要支持子类参与相等比较，否则用 *exact class* 更安全。

------

## 2. 空值与快速返回

### 写法 B 多了一个“快速路径”：

```kotlin
if (this === other) return true
```

- 当你做 `x == x`，或者集合中比较到同一个对象时，可以马上返回 `true`，不需要再看字段。
- 性能上的收益很小，但这是 Java / Kotlin 常见的习惯写法。

### 两者对 `null` 的处理效果是一致的

- 写法 A：`other == null` 直接 `return false`。
- 写法 B：`other?.javaClass` 为 `null`，一定不等于 `this.javaClass`，也会 `return false`。

------

## 3. 字段比较方式上的差异

本质一样，只是风格不同：

- 写法 A：一次性用一个布尔表达式返回：

  ```kotlin
  return name == other.name && postalCode == other.postalCode
  ```

- 写法 B：分开写：

  ```kotlin
  if (postalCode != other.postalCode) return false
  if (name != other.name) return false
  return true
  ```

差异：

1. **可读性**：当字段很多时，拆成多行 `if` 容易加日志 / 调试，也更容易逐条看是哪一项不相等。
2. **行为**：完全等价，都是用 Kotlin 的 `==` / `!=`（内部调用 `equals`，并做了空安全处理）。

------

## 4. 实际项目里我会如何选？

如果你写的是类似 `Client` 这种 **数据类 / 值对象**，且:

- 不打算让它有复杂的继承体系；
- 或者即便以后有子类，也不希望子类和父类互相相等；

那我会更倾向于 **写法 B 这种 “javaClass + 强转” 风格**，理由：

1. `this === other` 快速路径 + `javaClass` 精确比较 —— 和很多框架（包括 Kotlin `data class` 的 `equals` 实现）思路相近。
2. 避免子类参与比较带来的坑。
3. 逻辑更“模板化”：以后写别的实体类，直接复制这一套骨架改字段即可。

写法 A 也不是错，只是它隐含了一个设计选择：**「允许子类与父类在逻辑上被视为同一类值」**。这在某些抽象层次是合理的（例如纯抽象 AST 节点），但需要你非常清楚地接受那种对称性/传递性的风险。

------

## 小结一句方便你写在注释里

> 两个版本的 `equals` 主要差别在于：
>
> - **A** 使用 `other is Client`，允许子类实例参与比较，实现简单但对子类不太安全；
> - **B** 先用 `this === other` 和 `javaClass != other?.javaClass` 严格限制“同一运行时类”才比较，再强转后逐字段比较，更符合值对象/实体类在实际项目中的常见实现方式。