package ch07.LazyEmails

/**
 * @author runningpig66
 * @date 2025/12/9 周二
 * @time 16:19
 * §7.5 重用属性访问的逻辑：委托属性 §7.5.2 使用委托属性：惰性初始化和 "by lazy()"
 * P.197 代码清单 7.17 使用支持属性来实现惰性初始化
 *
 * 惰性初始化是一种常见的模式，直到在第一次访问该属性的时候，才根据需要创建对象的一部分。
 * 当初始化过程消耗大量资源并且在使用对象时并不总是需要数据时，这个非常有用。
 *
 * 下面展示了如何使用额外的 _emails 属性来实现惰性加载，在没有加载之前为 null，然后加载为邮件列表。
 * 这里使用了所谓的支持属性技术。你有一个属性 _emails 用来存储这个值，而另一个 emails 用来提供对属性的读取访问。
 * 你需要使用两个属性，因为属性具有不同的类型：_emails 可以为空，而 emails 为非空。这种技术经常会使用到，值得熟练掌握。
 *
 * 但这个代码有点啰嗦：要是有几个惰性属性那得有多长。而且，它并不总是正常运行：这个实现不是线程安全的。Kotlin 提供了更好的解决方案。
 */
class Email { /*...*/ }

fun loadEmails(person: Person): List<Email> {
    println("Load emails for ${person.name}")
    return listOf(/*...*/)
}

class Person(val name: String) {
    private var _emails: List<Email>? = null // “emails” 属性用来保存数据，关联委托
    val emails: List<Email>
        get() {
            if (_emails == null) {
                _emails = loadEmails(this) // 访问时加载邮件
            }
            return _emails!! // 如果已经加载，就直接返回
        }
}

fun main() {
    val p = Person("Alice")
    p.emails // 第一次访问会加载邮件
    p.emails
}
/* Output:
Load emails for Alice
 */
