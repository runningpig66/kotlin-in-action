 * §6.1 可空性 §6.1.10 类型参数的可空性
 * P.153 代码清单 6.13 处理可空的类型参数

```kotlin
/** From: 6.1.10_NullabilityOfTypeParameters.kt
 * [1] 处书中错误注释：“因为 "t" 可能为 null, 所以必须使用安全调用”这里描述不准确。
 *
 * 当写作： println(t?.hashCode()) 的时候，
 * 字节码： System.out.println(t != null ? t.hashCode() : null); （输出 null）
 *
 * 当写作： println(t.hashCode()) 的时候，
 * 字节码： int var1 = t != null ? t.hashCode() : 0; System.out.println(var1);（输出 0）
 *
 * 编译器对 hashCode、toString、equals 做了安全优化，避免抛出 NullPointerException。
 * 对于除这三个以外的任何方法，T 可能为 null 时不使用 ?. 仍然会有 NPE 风险。
 */

/* 对应字节码：
    public static final void printHashCode(Object t) {
       System.out.println(t != null ? t.hashCode() : null);
    }
 */
fun <T> printHashCode(t: T) {
    // [1] 书中错误注释：因为 "t" 可能为 null, 所以必须使用安全调用
    println(t?.hashCode())
}

/* 对应字节码：
    public static final void printHashCode1(Object t) {
       int var1 = t != null ? t.hashCode() : 0;
       System.out.println(var1);
    }
 */
fun <T> printHashCode1(t: T) {
    println(t.hashCode())
}

fun main() {
    printHashCode(null) // "T" 被推导成 "Any?"
    printHashCode1(null)
}
/* Output:
null
0
 */

```

------

你这次核对得非常细，结论总体是对的，我帮你把这几层关系理一理：

------

## 1. 书上这两句各自是什么意思？

代码（代码清单 6.13）：

```kotlin
fun <T> printHashCode(t: T) {
    println(t?.hashCode())
}
```

调用：

```kotlin
printHashCode(null)
```

书上说的两点：

1. **“T 被推导成可空类型 Any?”**
    这句话在 **Kotlin 类型系统层面是对的**：
   - 泛型形参 `T` 的默认上界是 `Any?`。
   - 调用 `printHashCode(null)` 时，类型推断会把 `T` 推成 `Any?`，所以 `t` 的静态类型是 `Any?`，可以保存 `null`。
2. **“因为 T 可能为 null，所以必须使用安全调用”**
    这一句就显得 **不够准确甚至可以认为是错的**——你已经用字节码把它戳穿了。

------

## 2. 为啥 `t.hashCode()` 也不会 NPE？

你写的两个版本：

```kotlin
fun <T> printHashCode(t: T) {
    println(t?.hashCode())
}

fun <T> printHashCode1(t: T) {
    println(t.hashCode())
}

fun main() {
    printHashCode(null)   // 输出 null
    printHashCode1(null)  // 输出 0
}
```

反编译结果（简化一下）：

```java
// 对应 t?.hashCode()
System.out.println(t != null ? t.hashCode() : null);

// 对应 t.hashCode()
int var1 = t != null ? t.hashCode() : 0;
System.out.println(var1);
```

这说明：

- `t?.hashCode()` 是标准的 **安全调用** 语义：
   receiver 为 null → 整个表达式结果就是 `null`。
- `t.hashCode()` 在这里 **不是直接调 `Object.hashCode()`**，而是被 Kotlin 编译器看成对
   `Any?.hashCode()` 的调用，它的语义就是：
   receiver 为 null → 返回 `0`，而不是抛 NPE。

原因就是你提到的那句：

> 编译器对 `hashCode`、`toString`、`equals` 做了安全优化，避免抛出 `NullPointerException`。

更精确一点说：
 Kotlin 标准库里对 **`Any?` 定义了这三个扩展函数**（概念上大致是）：

```kotlin
fun Any?.hashCode(): Int =
    if (this != null) this.hashCode() else 0

fun Any?.toString(): String =
    if (this != null) this.toString() else "null"

fun Any?.equals(other: Any?): Boolean =
    if (this === null) other === null else this.equals(other)
```

所以当编译器看到 `t.hashCode()` 而 `t` 的可能类型里包含 `null` 时，
 它会选择上面这个 **扩展函数版本**，而不是 `Any.hashCode()` 的成员版本，
 然后就生成了你看到的那段“判空后返回 0”的字节码。

> 结论：在这个例子里，**不使用安全调用也不会 NPE，只是结果不同**：
>  `t?.hashCode()` → `null`
>  `t.hashCode()`   → `0`

------

## 3. 那书上那句“必须使用安全调用”怎么理解？

更准确的说法应该是：

- 对于一个泛型形参 `T`，在没有上界约束时，调用者可以传入 `null`，所以 **`T` 在运行时可能为 null**。
- **“对可能为 null 的对象调用普通方法时，一般确实应该用 `?.`”**，否则就可能 NPE。
- 但 **`hashCode` / `toString` / `equals` 是特例**，Kotlin 为它们在 `Any?` 上提供了“对 null 友好的实现”，所以就算不用 `?.` 也不会 NPE。

书上的写法有点“教学化简化”：
 用 `hashCode` 做例子，但是按“普通方法”的思路来讲，就产生了你现在看到的矛盾。

从实战角度，你可以这样记：

- **规则（通用）**：
   `T` 可能为空 → 调用时要么 `?.`，要么先手动判空。
- **例外（只有这三个）**：
   `t.hashCode()` / `t.toString()` / `t.equals(x)` 在 Kotlin 里对 `t == null` 有特殊处理，不会抛 NPE。

------

## 4. “T 推导成 Any?” vs 字节码里的 `Object`

这个也顺便说清楚：

- Kotlin 层面：`T` 被推成 `Any?`，所以参数 `t` 的类型是 `Any?`，能存 null。
- JVM 字节码层面：泛型擦除 + Kotlin 的类型映射，`Any?` 就变成了 `java.lang.Object`，
   **字节码里本来就不区分可空/非空**，所以你看到 `Object t` 是正常的。

所以：

> “T 被推导成 Any?”（Kotlin 视角）
>  和
>  “字节码参数类型是 Object”（JVM 视角）
>  两者并不矛盾，只是两个层级的描述。

------

## 5. 回到你的旧笔记：对不对？

你当时的黄色注释大意是：

> 必须用安全调用访问方法？这里描述不准确。
>  编译器对 hashCode / toString / equals 做了特殊处理，所以不用安全调用也不会 NPE。

这几个点可以这样评价：

- “必须用安全调用”这一条 —— **确实不准确**，你的质疑是对的。

- “hashCode / toString / equals 被特殊处理” —— **这个结论是正确的**，你用字节码证明了。

- 如果想再严谨一点，可以在笔记里多加一句：

  > 对于除这三个以外的任何方法，`T` 可能为 null 时不使用 `?.` 仍然会有 NPE 风险。

如果你愿意，我可以帮你把这一段旧笔记重写成一条更“教科书级”的总结，你直接贴回书上做新的备注。
