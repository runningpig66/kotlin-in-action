package ch07.ex5_2_2_LazyInitialization1

/**
 * @author runningpig66
 * @date 2025/12/9 周二
 * @time 16:38
 * §7.5 重用属性访问的逻辑：委托属性 §7.5.2 使用委托属性：惰性初始化和 "by lazy()"
 * P.198 代码清单 7.18 用委托属性来实现惰性初始化
 *
 * 使用委托属性会让代码变得简单得多，可以封装用于存储值的支持属性和确保该值只被初始化一次的逻辑。在这里可以使用标准库函数 lazy 返回的委托。
 *
 * lazy 函数返回一个对象，该对象具有一个名为 getValue 且签名正确的方法，因此可以把它与 by 关键字一起使用来创建一个委托属性。
 * lazy 的参数是一个 lambda，可以调用它来初始化这个值。默认情况下，lazy 函数是线程安全的，
 * 如果需要，可以设置其他选项来告诉它要使用哪个锁，或者完全避开同步，如果该类永远不会在多线程环境中使用。
 *
 * public actual fun <T> lazy(initializer: () -> T): Lazy<T>
 * public actual fun <T> lazy(mode: LazyThreadSafetyMode, initializer: () -> T): Lazy<T>
 * public actual fun <T> lazy(lock: Any?, initializer: () -> T): Lazy<T>
 */
class Email { /*...*/ }

fun loadEmails(person: Person): List<Email> {
    println("Load emails for ${person.name}")
    return listOf(/*...*/)
}

//class Person(val name: String) {
//    private var _emails: List<Email>? = null // “emails” 属性用来保存数据，关联委托
//    val emails: List<Email>
//        get() {
//            if (_emails == null) {
//                _emails = loadEmails(this) // 访问时加载邮件
//            }
//            return _emails!! // 如果已经加载，就直接返回
//        }
//}

class Person(val name: String) {
    val emails: List<Email> by lazy { loadEmails(this) }
}

fun main() {
    val p = Person("Alice")
    p.emails
    p.emails
}
/* Output:
Load emails for Alice
 */
