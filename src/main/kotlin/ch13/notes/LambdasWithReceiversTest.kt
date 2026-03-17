package ch13.notes

/**
 * @author runningpig66
 * @date 3月14日 周六
 * @time 22:39
 */
class Outer {
    val outVal = "我是最外层"
}

class Middle {
    val midVal = "我是中间层"
}

class Inner {
    val inVal = "我是最内层"
}

// 定义三个带接收者的 DSL 构建函数
fun buildOuter(action: Outer.() -> Unit) = Outer().action()
fun buildMiddle(action: Middle.() -> Unit) = Middle().action()
fun buildInner(action: Inner.() -> Unit) = Inner().action()
fun main() {
    buildOuter {
        buildMiddle {
            buildInner {
                println(inVal)
                println(midVal)
                println(outVal)
                println(this@buildInner.inVal)
                println(this@buildMiddle.midVal)
                println(this@buildOuter.outVal)
            }
        }
    }
}
