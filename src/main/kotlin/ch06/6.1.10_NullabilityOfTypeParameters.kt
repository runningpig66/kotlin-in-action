package ch06.ex1_10_NullabilityOfTypeParameters

/**
 * @author runningpig66
 * @date 2025/12/1 周一
 * @time 17:27
 * §6.1 可空性 §6.1.10 类型参数的可空性
 * P.153 代码清单 6.13 处理可空的类型参数
 *
 * Kotlin 中所有泛型类和泛型函数的类型参数默认都是可空的。任何类型，包括可空类型在内，都可以替换类型参数。
 * 这种情况下，使用类型参数作为类型的声明都允许为 null, 尽管类型参数 T 并没有用问号结尾。
 * 注意必须使用问号结尾来标记类型为可空的，没有问号就是非空的。类型参数是这个规则唯一的例外。
 *
 * [1] 处书中错误注释：“因为 "t" 可能为 null, 所以必须使用安全调用”这里描述不准确。
 *
 * 当写作： println(t?.hashCode()) 的时候，
 * 字节码： System.out.println(t != null ? t.hashCode() : null); （输出 null）
 *
 * 当写作： println(t.hashCode()) 的时候，
 * 字节码： int var1 = t != null ? t.hashCode() : 0; System.out.println(var1);（输出 0）
 *
 * 编译器对 hashCode、toString、equals 做了安全优化，避免抛出 NullPointerException。
 * 对于除这三个以外的任何方法，T 可能为 null 时不使用 ?. 仍然会有 NPE 风险。
 * notes: NullabilityOfTypeParameters.md
 */

/* 对应字节码：
    public static final void printHashCode(Object t) {
       System.out.println(t != null ? t.hashCode() : null);
    }
 */
fun <T> printHashCode(t: T) {
    // [1] 书中错误注释：因为 "t" 可能为 null, 所以必须使用安全调用
    println(t?.hashCode())
}

/* 对应字节码：
    public static final void printHashCode1(Object t) {
       int var1 = t != null ? t.hashCode() : 0;
       System.out.println(var1);
    }
 */
fun <T> printHashCode1(t: T) {
    println(t.hashCode())
}

fun main() {
    printHashCode(null) // "T" 被推导成 "Any?"
    printHashCode1(null)
}
/* Output:
null
0
 */
