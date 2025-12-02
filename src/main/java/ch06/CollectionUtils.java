package ch06;

import java.util.List;

/**
 * @author runningpig66
 * @date 2025/12/2 周二
 * @time 17:16
 * §6.3 集合与数组 §6.3.3 Kotlin 集合和 Java
 * P.170 代码清单
 */
public class CollectionUtils {
    public static List<String> uppercaseAll(List<String> items) {
        for (int i = 0; i < items.size(); i++) {
            items.set(i, items.get(i).toUpperCase());
        }
        return items;
    }
}
