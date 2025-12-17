package ch08.ex1_5_2_ReturningFunctionsFromFunctions1

/**
 * @author runningpig66
 * @date 2025/12/16 周二
 * @time 22:28
 * §8.1 声明高阶函数 §8.1.5 返回函数的函数
 * P.215 代码清单 8.7 在 UI 代码中定义一个返回函数的函数
 *
 * 来看另一个返回函数的函数非常实用的例子。假设你正在开发一个带 GUI 的联系人管理应用，你需要通过 UI 的状态来决定显示哪一个联系人。
 * 例如，可以在 UI 上输入一个字符串，然后只显示那些姓名以这个字符串开头的联系人；还可以隐藏没有电话号码的联系人。
 * 我们用 ContactListFilters 这个类来保存这些选项的状态。
 *
 * 为了让展示联系人列表的逻辑代码和输入过滤条件的 UI 代码解耦，可以定义一个函数来创建一个判断式，
 * 用它来过滤联系人列表。判断式检查前缀，如果有需要也会检查电话号码是否存在。
 */
data class Person(
    val firstName: String,
    val lastName: String,
    val phoneNumber: String?
)

class ContactListFilters {
    var prefix: String = ""
    var onlyWithPhoneNumber: Boolean = false

    // 声明一个返回函数的函数
    fun getPredicate(): (Person) -> Boolean {
        val startsWithPrefix: (Person) -> Boolean = { p: Person ->
            p.firstName.startsWith(prefix) || p.lastName.startsWith(prefix)
        }
        if (!onlyWithPhoneNumber) {
            // 返回一个函数类型的变量
            return startsWithPrefix
        }
        // 从这个函数返回一个 lambda
        return { startsWithPrefix(it) && it.phoneNumber != null }
    }
}

fun main() {
    val contacts = listOf(
        Person("Dmitry", "Jemerov", "123-4567"),
        Person("Svetlana", "Isakova", null)
    )
    val contactListFilters = ContactListFilters()
    with(contactListFilters) {
        this.prefix = "Dm"
        this.onlyWithPhoneNumber = true
    }
    // 将 getPredicate 返回的函数作为参数传递给 "filter" 函数
    println(contacts.filter(contactListFilters.getPredicate()))
}
/* Output:
[Person(firstName=Dmitry, lastName=Jemerov, phoneNumber=123-4567)]
 */
