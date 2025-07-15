package ch04.ex1_4_2_InnerAndNestedClasses1

// 代码清单 P.78
class Outer {
    inner class Inner {
        fun getOuterReference(): Outer = this@Outer
    }
}