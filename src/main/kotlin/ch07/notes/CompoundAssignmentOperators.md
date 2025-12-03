* **§7.1 重载算术运算符 §7.1.2 重载复合赋值运算符**
* **P.184 代码清单**

**书中 P.184  “另一个办法是，用 val 替换 var，这样 plusAssign 运算就不再适用。”这句话描述不准确。**

```kotlin
package ch07.ex1_2_3_CompoundAssignmentOperators3

/**
 * @author runningpig66
 * @date 2025/12/3 周三
 * @time 15:24
 * §7.1 重载算术运算符 §7.1.2 重载复合赋值运算符
 * P.184 代码清单
 *
 * notes: CompoundAssignmentOperators.md & 底部注释笔记
 * 书中 P.184 “另一个办法是，用 val 替换 var，这样 plusAssign 运算就不再适用。”这句话描述不准确。
 */
data class Point(var x: Int, var y: Int)

// plus 函数也可以不返回新的 Point 而就地修改
operator fun Point.plus(that: Point): Point {
    return Point(x + that.x, y + that.y)
}

// plusAssign 函数也可以不就地修改什么都不做，但只能返回 Unit
operator fun Point.plusAssign(that: Point) {
    x += that.x;
    y += that.y
}

fun main() {
//     var p1 = Point(10, 20)           // [1]
    val p1 = Point(10, 20)       // [2]
    val p2 = Point(30, 40)
    val p3 = p1 + p2
    p1 += p2                            // [3]
}
/* 注意：
在 [1] 处打开、[2] 处注释的状态，[3] 处代码报错，编译器无法判断 += 运算符应该调用 plus 还是 plusAssign.
+= 可以映射到 plus 函数，此时 p1 += p2 相当于 p1 = p1.plus(p2),
p1 是 var 可变引用，可以被重新赋值指向新的对象，所以 += 可以映射到 plus 函数（通过临时注释 plusAssign 函数可以印证这一点）。
+= 也可以映射到 plusAssign 函数，此时的 += 只是相当于 plusAssign 函数的语法糖，
p1 += p2 相当于 p1.plusAssign(p2). 故两个函数都可以被映射，导致编译器无法区分而报错。

如果注释掉 [1] 打开 [2] , 编译器不再报错。原因是 p1 变为了不可变引用。
当 p1 作为不可变引用时，对于 plus 函数，p1 += p2 相当于 p1 = p1.plus(p2) 中的 p1 无法再赋值，
所以此时的 plus 函数只能对运算符 + 生效，无法对潜在的 += 生效。
而 += 对于 plusAssign 函数依然有效，同上，+= 只是相当于 plusAssign 函数的语法糖，
p1 是可变引用，还是不可变引用，都对 p1 调用 plusAssign 函数没有影响。
而在 plusAssign 函数的内部会对 p1 的成员进行就地修改，而不是返回新的修改后的对象。
即：p1 作为不可变引用调用 += ，只能映射到 plusAssign 函数。

那么此时注意书中的描述：“另一个办法是，用 val 替换 var，这样 plusAssign 运算就不再适用。”
而我们上方所做的，是用 val 替换 var，plusAssign 运算照样适用，只要对象内容可以就地修改。

“用 val 替换 var，plusAssign 运算就不再适用”这句话并不准确，书中的描述可能是在讨论不可变对象的情况：
即如果我们使用不可变对象（类的属性是val），那么 plusAssign 函数无法实际修改属性，因此就不适用了。
在这种情况下，我们只能使用 plus 函数，并且 p1 必须是 var 才能进行 += 操作（因为需要重新赋值）。
书中的描述应修正为“用 val 替换 var 会使 plus 不适用于 +=，但 plusAssign 仍然适用”

我们通常的做法是：
- 对于不可变类，只定义 plus 等返回新对象的运算符函数。
- 对于可变类，可以定义 plusAssign 等就地修改的运算符函数，但不要同时定义 plus（除非有特别需要，并且注意避免冲突）。
+= 会优先调用 plusAssign，如果没有，再尝试用 plus（要求左值是 var）。
*/

```

------

结论先说在前面：

* 你写的代码实验 **完全正确**。
* 你指出「**用 val 替换 var，这样 plusAssign 运算就不再适用**」这句话不对，这个判断也是对的。
* 你的长注释整体逻辑没大问题，只是有两三处可以更精确一点，我帮你打磨一下。

---

## 1. Kotlin 官方规则到底是怎样的？

先把语言规则讲清楚（这部分你以后可以当“标准答案”记住）：

对表达式：

```kotlin
a += b
```

编译器会做下面几步：

1. **先看有没有 `plusAssign`：**

    * 如果存在 `operator fun plusAssign(...)`：

        * 再看“退化成 `a = a + b`”这条路是不是也合法：

            * 如果同时存在 `plus`，并且

                * `a` 是 **var**（可重新赋值），而且
                * `plus` 的返回类型是 `a` 的子类型
            * ⇒ **两个展开都合法，必须报“运算符重载歧义”错误。**
        * 否则（`plus` 不存在、或那条路本身不合法）：

            * 要求 `plusAssign` 返回 `Unit`，然后生成代码：

              ```kotlin
              a.plusAssign(b)
              ```

2. **如果根本没有 `plusAssign`：**

    * 尝试生成：

      ```kotlin
      a = a.plus(b)
      ```
    * 这时候 `a` 必须是 **var**，否则不能赋值。

关键点：

* `plusAssign` 路径 **对 val / var 都行**；
* `plus` 路径必须要能变成赋值 `a = ...`，所以只对 **var** 有效；
* 当两条路都通时，就像你看到的那样：**报“assignment operators ambiguity”错误**，而不是“优先选 plusAssign”。

---

## 2. 用这个规则看你的代码

你的代码：

```kotlin
data class Point(var x: Int, var y: Int)

operator fun Point.plus(that: Point): Point {
    return Point(x + that.x, y + that.y)
}

operator fun Point.plusAssign(that: Point) {
    x += that.x
    y += that.y
}
```

### 情况一：`var p1`

```kotlin
var p1 = Point(10, 20)
val p2 = Point(30, 40)
p1 += p2
```

* `plusAssign` 存在 → `p1.plusAssign(p2)` 是合法的。
* `plus` 也存在，返回的是 `Point`，与 `p1` 类型一致；再加上 `p1` 是 **var**，
  → `p1 = p1.plus(p2)` 这条展开也合法。
* 两条路都合法 ⇒ 触发规则里的“**重载歧义**” ⇒ 正是你看到的编译错误。

这一段你的注释：

> 在 [1] 处打开… 编译器无法判断 += 运算符应该调用 plus 还是 plusAssign.

✅ 这一句是对的，本质就是上面说的“两个展开都成立 → 歧义”。

---

### 情况二：`val p1`

```kotlin
val p1 = Point(10, 20)
val p2 = Point(30, 40)
p1 += p2
```

* `plusAssign` 路线依然合法：`p1.plusAssign(p2)` **不需要**给 `p1` 重新赋值，所以对 `val` 没有限制。
* `plus` 路线就不行了：如果展开成 `p1 = p1.plus(p2)`，这里对 `p1` 赋值，会违反 `val` 规则，**这条展开直接非法**。
* 现在只有 `plusAssign` 这条路可用 ⇒ 编译器就选它 ⇒ 调用 `plusAssign`，像你实验到的那样。

也就是说：

> “用 val 替换 var，这样 plusAssign 运算就不再适用”

这句话确实是错的；真正发生的是：

> “用 val 替换 var，这样 **plus 路线不再适用**，只剩下 plusAssign 可以用于 `+=`。”

这跟你的实验结果完全一致。

---

## 3. 你长注释里哪里对、哪里可以再精细一点？

我按段点评一下（只挑关键点说）：

### ✅ 正确的部分

1. **关于 var 时的歧义：**

> `p1 += p2` 既可以变成 `p1 = p1.plus(p2)`，也可以变成 `p1.plusAssign(p2)`，所以编译器报错。

这段解释 **完全正确**，和官方规则一致，只是可以补一句“这在规范里叫 operator overloading ambiguity”。

2. **关于 val 时 plus 失效、只剩 plusAssign：**

> 当 p1 是 val 时，`p1 = p1.plus(p2)` 这条展开不合法，所以 `+=` 只能映射到 plusAssign。

✅ 这也是对的。

3. **关于“不要同时给一个类型加 plus 和 plusAssign”：**

> * 不可变类：只提供 `plus`，返回新对象
> * 可变类：只提供 `plusAssign`，就地修改
> * 同时提供要特别小心避免冲突

这个是 Kotlin 官方和社区推荐的“惯例做法”，和标准库对 `Collection` / `MutableCollection` 的设计是一致的。

### 需要微调的两点

1. **“+= 会优先调用 plusAssign，如果没有，再尝试用 plus”**

更精确的说法应该是：

> `+=` **优先尝试扩展为 `a.plusAssign(b)`。如果这条路走不通**，
> 才会尝试“退化”为 `a = a.plus(b)`。
> 但如果两种展开都合法，则视为“重载歧义”，报错，而不是简单选 plusAssign。

也就是：**有优先级，但不允许“两个都对”这种情况悄悄过关。**

2. **你猜书上是在讲“不可变对象（属性是 val）所以 plusAssign 不适用”**

这里有点混了两个“不可变”概念：

* `val p1`：引用本身不能重新指向别的对象；
* `data class Point(val x: Int, val y: Int)`：对象内部字段不能改。

`plusAssign` 能不能被编译器用，**只跟“左值是不是 val/var”无关**，它只是个普通成员/扩展函数：

* `val p1 = Point(...)` 时一样可以写 `p1.plusAssign(...)`，只要函数存在。
* 至于函数内部能不能修改字段，那取决于 `Point` 里字段是不是 `var` —— 这是另一层面的事情。

所以“因为对象字段是 val，所以 plusAssign 不适用”这句话从语言规则角度不成立，只能说“你**想要的那种”就地修改语义**没法实现，因此**设计上**不应该提供 plusAssign。

---

## 4. 帮你整理一个“最终版记忆模板”

你以后可以按这个版本记就好了：

1. **`a += b` 的展开规则：**

    * 如果有 `plusAssign`：

        * 若 `plus` 也存在且 `a` 是 `var` 且 `a.plus(b)` 返回子类型 ⇒ **报歧义错误**；
        * 否则使用 `a.plusAssign(b)`。
    * 如果没有 `plusAssign`：

        * 尝试 `a = a.plus(b)`，此时要求 `a` 是 `var`。

2. **关于 `val` / `var`：**

    * `plusAssign` 不要求 `a` 是 `var`，`val` 也能用；
    * 只有走 `a = a.plus(b)` 这条路径时，才会要求 `a` 是 `var`。

3. **推荐设计习惯：**

    * 不可变类型（例如 `Point(val x: Int, val y: Int)`）：

        * 实现 `operator fun plus(...)`: 返回新对象；
        * 不实现 `plusAssign`。
    * 可变类型（例如 `Point(var x: Int, var y: Int)` 的“构建器”风格）：

        * 实现 `operator fun plusAssign(...)`：就地修改；
        * 一般不再实现同签名的 `plus`，避免 `+=` 歧义。

4. **你这段实验的“教科书意义”：**

    * `var p1` + `plus` + `plusAssign` ⇒ **歧义，报错**；
    * `val p1` + `plus` + `plusAssign` ⇒ **只用 plusAssign，正常编译**；
    * 直接打脸了书里那句“用 val 替换 var，plusAssign 不再适用”。

---

如果你愿意，我可以再帮你把这段长注释改成一个“精简+完全正确版本”，以后直接复制贴在代码里当笔记。
