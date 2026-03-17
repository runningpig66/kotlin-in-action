package ch13.ex2_InvokeConventionInDSLs

/**
 * @author runningpig66
 * @date 3月16日 周一
 * @time 1:48
 * Listing 13.17 Using invoke to support flexible DSL syntax
 * 清单 13.17 使用 ` invoke ` 来支持灵活的 DSL 语法
 */
class DependencyHandler {
    fun implementation(coordinate: String) { // 定义常规命令 API
        println("Added dependency on $coordinate")
    }

    operator fun invoke(body: DependencyHandler.() -> Unit) { // 定义 invoke 以支持 DSL API
        body() // this 表达式成为 body 函数的接收者：this.body()。
    }
}

fun main() {
    val dependencies = DependencyHandler()
    dependencies.implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
    dependencies {
        implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.5.0")
    }
}
/* Output:
Added dependency on org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0
Added dependency on org.jetbrains.kotlinx:kotlinx-datetime:0.5.0
 */
