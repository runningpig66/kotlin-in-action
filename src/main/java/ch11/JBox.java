package ch11;

import org.jetbrains.annotations.NotNull;

/**
 * @author runningpig66
 * @date 2026/1/14 周三
 * @time 22:09
 * 11.1 Creating types with type arguments: Generic type parameters
 * 11.1 使用类型实参创建类型：泛型类型参数
 * 11.1.4 Excluding nullable type arguments by explicitly marking type parameters as non-null
 * 11.1.4 通过显式将类型参数标记为非空来排除可空类型实参
 */
public interface JBox<T> {
    /**
     * Puts a non-null value into the box.
     */
    void put(@NotNull T t);

    /**
     * Puts a value into the box if it is not null,
     * doesn't do anything for null values.
     */
    void putIfNotNull(T t);
}
