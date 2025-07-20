package ch06.ex3_5_1_Arrays

// 代码清单 6.29 使用数组
fun main(args: Array<String>) {
    for (i in args.indices) {
        println("Argument $i is: ${args[i]}")
    }
}