package ch13.ex4_1_KotlinxHtml

import kotlinx.html.*
import kotlinx.html.stream.createHTML

/**
 * @author runningpig66
 * @date 3月14日 周六
 * @time 3:13
 * 13.1.4 Building HTML with an internal DSL
 * 13.1.4 使用内部 DSL 构建 HTML
 * Listing 13.4 Producing a simple HTML table with a Kotlin HTML builder
 * 代码清单 13.4 使用 Kotlin HTML 构建器生成一个简单的 HTML 表格
 * Listing 13.6 Making receivers of HTML builder calls explicit
 * 代码清单 13.6 显式指定 HTML 构建器调用的接收者
 */
fun createSimpleTable() = createHTML()
    .table {
        tr {
            td { +"cell" }
        }
    }

fun createAnotherTable() = createHTML().table {
    val numbers = mapOf(1 to "one", 2 to "two")
    for ((num, string) in numbers) {
        tr {
            td { +"$num" }
            td { +string }
        }
    }
}

// 13.6: 为了更清楚地说明这里发生了什么，你可以重写代码清单 13.4，将所有接收者显式化。
// 提醒一下，你可以通过 this@foo 访问作为 foo 函数参数的 lambda 的接收者。
fun createSimpleTable2() = createHTML().table {
    this@table.tr { // this@table 的类型为 TABLE。
        this@tr.td { // this@tr 的类型为 TR。
            +"cell" // 此处可以使用类型为 TD 的隐式接收者 this@td。
        }
    }
}

fun main() {
    // createSimpleTable 函数返回一个包含此 HTML 片段的字符串。
    println(createSimpleTable())
    // 生成的 HTML 包含了所需的数据：
    println(createAnotherTable())

    // Listing 13.7 Having multiple receivers in scope can lead to confusing code.
    // 代码清单 13.7 作用域内存在多个接收者可能会导致代码混乱。
    createHTML().body {
        a {
            img {
                // 隐式调用报错，因为触发了 @DslMarker 拦截
                // error: 'var href: String' cannot be called in this context with an implicit receiver.
                // Use an explicit receiver if necessary.
                //- href = "https://..."

                // 显式指明操作的是哪一层的接收者，编译器才会放行
                this@a.href = "https://..."
            }
        }
    }
}
/* Output:
<table>
  <tr>
    <td>cell</td>
  </tr>
</table>

<table>
  <tr>
    <td>1</td>
    <td>one</td>
  </tr>
  <tr>
    <td>2</td>
    <td>two</td>
  </tr>
</table>
 */
