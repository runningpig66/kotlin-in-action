package ch13.ex2_2_UsingLambdasWithReceiversInHTMLBuilders1

/**
 * @author runningpig66
 * @date 3月15日 周日
 * @time 20:13
 * Listing 13.12 Generating tags dynamically with an HTML builder
 * 清单 13.12 使用 HTML 构建器动态生成标签
 */
@DslMarker
annotation class HtmlTagMarker

@HtmlTagMarker
open class Tag(val name: String) {
    private val children = mutableListOf<Tag>()

    protected fun <T : Tag> doInit(child: T, init: T.() -> Unit) {
        child.init()
        children.add(child)
    }

    override fun toString() = "<$name>${children.joinToString("")}</$name>"
}

fun table(init: TABLE.() -> Unit) = TABLE().apply(init)

class TABLE : Tag("table") {
    fun tr(init: TR.() -> Unit) = doInit(TR(), init)
}

class TR : Tag("tr") {
    fun td(init: TD.() -> Unit) = doInit(TD(), init)
}

class TD : Tag("td")

fun createTable() =
    table {
        tr {
            td {
            }
        }
    }

fun createAnotherTable() = table {
    for (i in 1..2) {
        tr {
            td {
            }
        }
    }
}

fun main() {
    println(createTable())
    println(createAnotherTable())
}
/* Output:
<table><tr><td></td></tr></table>
<table><tr><td></td></tr><tr><td></td></tr></table>
 */
