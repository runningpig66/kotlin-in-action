package ch04.ex2_1_InitializingClasses

/**
 * @author runningpig66
 * @date 2025/11/22 周六
 * @time 20:32
 * §4.2 声明一个带非默认构造方法或属性的类 §4.2.1 初始化类：主构造方法和初始化语句块
 * P.81 & 82 代码清单
 */
class User0(_nickname: String) { // 带一个参数的主构造方法
    val nickname: String

    init { // 初始化语句块，适合做“复杂初始化”
        nickname = _nickname
    }
}

class User1(_nickname: String) {
    val nickname = _nickname // 直接用参数来初始化属
}

// "val" 意味着相应的属性会用构造方法的参数来初始化
class User(val nickname: String, val isSubscribed: Boolean = true) // 为构造方法参数提供一个默认值

fun main() {
    val alice = User("Alice") // 为 isSubscribed 参数使用默认值 "true"
    println(alice.isSubscribed)
    val bob = User("Bob", false) // 可以按照声明顺序写明所有的参数
    println(bob.isSubscribed)
    val carol = User("Carol", isSubscribed = false) // 可以显式地为某些构造方法参数标明名称
    println(carol.isSubscribed)
}
/* Output:
true
false
false
 */
