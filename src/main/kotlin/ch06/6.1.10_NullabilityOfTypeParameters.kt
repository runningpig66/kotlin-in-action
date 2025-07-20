package ch06.ex1_10_NullabilityOfTypeParameters

// 代码清单 6.13 处理可空的类型参数
fun <T> printHashCode(t: T) {
    // 字节码：System.out.println(t != null ? t.hashCode() : null);
    println(t?.hashCode())
    // 字节码：int var1 = t != null ? t.hashCode() : 0;
    //        System.out.println(var1);
    // 编译器对 hashCode、toString、equals 做了安全优化，避免抛出 NullPointerException。
    println(t.hashCode())
}

fun main() {
    printHashCode(null)
}
/** Output:
 * null
 * 0
 */