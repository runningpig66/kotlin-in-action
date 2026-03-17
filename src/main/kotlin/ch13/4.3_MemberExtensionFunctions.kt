package ch13.ex4_3_MemberExtensionFunctions

/**
 * @author runningpig66
 * @date 3月16日 周一
 * @time 20:11
 * 13.4.3 Member extension functions: Internal DSL for SQL
 * 13.4.3 成员扩展函数：SQL 的内部 DSL
 *
 * notes: 成员扩展函数与DSL作用域隔离.md
 */
// 模拟数据库的列
class Column<T>(val name: String, val type: String) {
    var isAutoIncrement = false
}

// 模拟数据库的表基类
open class Table {
    fun integer(name: String): Column<Int> = Column(name, "INT")
    fun varchar(name: String, length: Int): Column<String> = Column(name, "VARCHAR($length)")

    /* 成员扩展函数 (Member Extension Function)
     * 在底层 Java 中，它是一个普通的成员方法，调用它必须同时具备两个上下文对象：
     * 1. 分派接收者 (Dispatch Receiver) -> Table 的实例 (提供上下文环境)
     * 2. 扩展接收者 (Extension Receiver) -> Column<Int> 的实例 (提供操作对象)
     * 核心目的：实现严格的作用域隔离。强制规定“列的自增配置”只能在“定义表结构”的上下文中执行。
     */
    fun Column<Int>.autoIncrement(): Column<Int> {
        // 验证 1：可以访问扩展接收者 (Column) 的属性
        this.isAutoIncrement = true

        // 验证 2：可以跨越作用域，访问分派接收者 (Table) 的成员方法
        // varchar("description", 100)
        // this@Table.varchar("description", 100)

        return this
    }
}

// 业务代码：利用单例定义一张具体的数据库表
object Country : Table() {
    // 处于 Table 作用域内，成员扩展函数可被合法调用
    val id = integer("id").autoIncrement()
    val name = varchar("name", 50)
}

// 模拟业务层面的数据库查询操作，返回一个普通的 Column 对象
fun mockQueryColumnFromDb(columnName: String): Column<Int> {
    return Column(columnName, "INT")
}

fun main() {
    println("表 Country 初始化成功！")
    println("id 列名: ${Country.id.name}, 类型: ${Country.id.type}, 自增: ${Country.id.isAutoIncrement}")
    println("name 列名: ${Country.name.name}, 类型: ${Country.name.type}, 自增: ${Country.name.isAutoIncrement}")

    // 测试用例：验证作用域隔离机制与单例模式下的上下文泄露问题
    val ageColumn = mockQueryColumnFromDb("age")

    // 由于脱离了分派接收者 (Table) 的作用域，以下调用在常规编译阶段应抛出未解析引用 (Unresolved reference) 错误。
    // 该编译期限制正是内部 DSL 期望达到的安全边界，旨在防止开发者在非建表上下文（如数据查询结果）中误用架构级配置方法。

    // 隐式上下文注入缺陷：若开发者经 IDE 提示在文件顶部显式声明了 `import ...Country.autoIncrement`，
    // 编译器将强制绑定 Country 的全局单例实例作为隐式的分派接收者，从而绕过作用域检查导致编译通过。
    // 此现象充分暴露了依赖 object 单例来承载 DSL 成员扩展时，存在严格隔离性被破坏的潜在风险。

    // Table().integer("asdf").autoIncrement()
    // Column<Int>("int", "Int").autoIncrement()
    // ageColumn.autoIncrement()
}
/* Output:
表 Country 初始化成功！
id 列名: id, 类型: INT, 自增: true
name 列名: name, 类型: VARCHAR(50), 自增: false
 */
