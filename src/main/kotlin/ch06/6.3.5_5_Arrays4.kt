package ch06.ex3_5_5_Arrays4

// 代码清单 6.32 对数组使用 forEachIndexed
fun main(args: Array<String>) {
    args.forEachIndexed { index, element ->
        println("Argument $index is: $element")
    }
}