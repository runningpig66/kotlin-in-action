package ch04.ex2_3_2_ImplementingPropertiesDeclaredInInterfaces1

// 代码清单 P.86
interface User {
    val email: String
    val nickname: String
        // 属性没有支持字段
        get() = email.substringBefore('@')
}