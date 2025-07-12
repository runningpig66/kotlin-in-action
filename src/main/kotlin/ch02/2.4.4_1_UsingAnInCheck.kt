package ch02.ex4_4_1_UsingAnInCheck

fun isLetter(c: Char) = c in 'A'..'Z' || c in 'a'..'z'
fun isNotDigit(c: Char) = c !in '0'..'9'

fun main() {
    println(isLetter('q'))
    println(isNotDigit('x'))
}