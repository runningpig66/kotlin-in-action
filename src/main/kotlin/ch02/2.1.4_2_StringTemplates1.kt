package ch02.ex1_4_2_StringTemplates1

fun main(args: Array<String>) {
    if (args.isNotEmpty()) {
        println("Hello, ${args[0]}")
    }
}