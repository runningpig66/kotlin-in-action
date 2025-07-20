package ch06;

import java.util.List;

// 代码清单 P.170
public class CollectionUtils {
    public static List<String> uppercaseAll(List<String> items) {
        for (int i = 0; i < items.size(); i++) {
            items.set(i, items.get(i).toUpperCase());
        }
//        items.replaceAll(String::toUpperCase);
        return items;
    }
}