package ch02.ex4_2_2_RangesProgressions1

/**
 * @author runningpig66
 * @date 2025/10/17 周五
 * @time 11:46
 * P.38 代码清单 2.23 迭代带步长的区间
 */
fun fizzBuzz(i: Int) = when {
    i % 15 == 0 -> "FizzBuzz "
    i % 3 == 0 -> "Fizz "
    i % 5 == 0 -> "Buzz "
    else -> "$i "
}

fun main() {
    for (i in 100 downTo 1 step 2) {
        print(fizzBuzz(i))
    }
}
/* Output:
Buzz 98 Fizz 94 92 FizzBuzz 88 86 Fizz 82 Buzz Fizz 76 74 Fizz Buzz 68 Fizz 64 62 FizzBuzz 58 56 Fizz 52 Buzz Fizz 46 44 Fizz Buzz 38 Fizz 34 32 FizzBuzz 28 26 Fizz 22 Buzz Fizz 16 14 Fizz Buzz 8 Fizz 4 2
 */
