package ch13.HtmlTags

/**
 * @author runningpig66
 * @date 3月15日 周日
 * @time 17:05
 * Listing 13.11 A full implementation of a simple HTML builder [IMP]
 * 清单 13.11 一个简单 HTML 构建器的完整实现
 */
@DslMarker
annotation class HtmlTagMarker

@HtmlTagMarker
open class Tag(val name: String) {
    private val children = mutableListOf<Tag>() // 存储所有嵌套标签

    protected fun <T : Tag> doInit(child: T, init: T.() -> Unit) {
        child.init() // 初始化子标签
        children.add(child) // 存储对子标签的引用
    }

    override fun toString() =
        "<$name>${children.joinToString("")}</$name>" // 将生成的 HTML 以字符串形式返回
}

fun table(init: TABLE.() -> Unit) = TABLE().apply(init)

class TABLE : Tag("table") {
    /*fun tr(init: TR.() -> Unit) {
        children.add(TR().apply(init))
    }*/
    fun tr(init: TR.() -> Unit) {
        doInit(TR(), init) // 创建、初始化并将一个新的 TR 标签实例添加到 TABLE 的子元素中
    }
}

class TR : Tag("tr") {
    /*fun td(init: TD.() -> Unit) {
        children.add(TD().apply(init))
    }*/
    fun td(init: TD.() -> Unit) = doInit(TD(), init) // 将一个新的 TD 标签实例添加到 TR 的子元素中
}

class TD : Tag("td")

fun createTable() =
    table {
        tr {
            td {
            }
        }
    }

fun main() {
    println(createTable())
}
/* Output:
<table><tr><td></td></tr></table>
 */
