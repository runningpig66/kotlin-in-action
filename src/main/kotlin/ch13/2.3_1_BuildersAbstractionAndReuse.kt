package ch13.ex2_3_1_BuildersAbstractionAndReuse

import kotlinx.html.*
import kotlinx.html.stream.createHTML

/**
 * @author runningpig66
 * @date 3月15日 周日
 * @time 20:39
 */
// Listing 13.14 Building a page with a table of contents using a Kotlin HTML builder
// 清单 13.14 使用 Kotlin HTML 构建器构建带目录的页面
fun _buildBookList() = createHTML().body {
    ul {
        li { a("#0") { +"The Three-Body Problem" } }
        li { a("#1") { +"The Dark Forest" } }
        li { a("#2") { +"Death’s End" } }
    }

    h2 { id = "0"; +"The Three-Body Problem" }
    p { +"The first book tackles..." }

    h2 { id = "1"; +"The Dark Forest" }
    p { +"The second book starts with..." }

    h2 { id = "2"; +"Death’s End" }
    p { +"The third book contains..." }
}

// 数据收集类：只负责收集数据，不关心 HTML
@HtmlTagMarker
class LISTWITHTOC {
    val entries = mutableListOf<Pair<String, String>>()
    fun item(headline: String, body: String) {
        entries.add(headline to body)
    }
}

// 2. 基于带接收者 Lambda 的类型安全构建器模式：为实现复杂对象的初始化与结构化配置，推荐采用高阶函数结合“带接收者的 Lambda 表达式
// （即 T.() -> Unit）”作为尾随参数的设计模式。在该模式的具体实现中，由高阶函数内部统一负责目标对象的实例化，
// 随后以上述对象作为上下文接收者（即 Lambda 作用域内的 this 指针）执行传入的闭包逻辑，最终返回已完成状态配置的对象实例。
// 此范式是对传统面向对象构建器（Builder）的声明式演进，它在调用端隐藏了底层对象的创建过程与繁琐的设值方法（Setter）调用，
// 使调用者能够以高内聚、高可读的代码层级结构完成对象状态的装配。
fun BODY.listWithToc(block: LISTWITHTOC.() -> Unit) {
    val listWithToc = LISTWITHTOC()
    listWithToc.block()
    ul {
        for ((index, entry) in listWithToc.entries.withIndex()) {
            li { a("#$index") { +entry.first } }
        }
    }
    for ((index, entry) in listWithToc.entries.withIndex()) {
        h2 { id = "$index"; +entry.first }
        p { +entry.second }
    }
}

// Listing 13.15 Building a page with a table of contents with helper functions
// 清单 13.15 使用辅助函数构建带目录的页面
fun buildBookList() = createHTML().body {
    // 1. DSL 作用域内的代码抽象与模块化：在处理带有接收者的 Lambda 表达式（如 DSL 结构中的闭包）时，若内部的构建或业务逻辑过于复杂，
    // 应将具体逻辑提取为该接收者类型的扩展函数。此设计可有效避免单一 Lambda 代码块变得庞大且难以维护。通过为接收者类定义专门的扩展函数，
    // 被提取的方法能够在其所属的 DSL 作用域内被直接且无缝地调用，如同该类的原生成员方法一般。
    // 这种重构方式在不破坏 DSL 声明式语法结构和可读性的前提下，实现了复杂逻辑的模块化封装与代码复用。
    listWithToc {
        item("The Three-Body Problem", "The first book tackles...")
        item("The Dark Forest", "The second book starts with...")
        item("Death’s End", "The third book contains...")
    }
}

fun main() {
    // println(_buildBookList())
    println(buildBookList())
}
/* Output:
<body>
  <ul>
    <li><a href="#0">The Three-Body Problem</a></li>
    <li><a href="#1">The Dark Forest</a></li>
    <li><a href="#2">Death’s End</a></li>
  </ul>
  <h2 id="0">The Three-Body Problem</h2>
  <p>The first book tackles...</p>
  <h2 id="1">The Dark Forest</h2>
  <p>The second book starts with...</p>
  <h2 id="2">Death’s End</h2>
  <p>The third book contains...</p>
</body>
 */
