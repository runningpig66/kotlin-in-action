package ch08;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author runningpig66
 * @date 2025/12/21 周日
 * @time 23:37
 * §8.2 内联函数：消除 lambda 带来的运行时开销 §8.2.5 使用内联 lambda 管理资源
 * P.224 代码清单 8.16 在 Java 中使用 try-with-resource 语句
 */
public class TryWithResource {
    static String readFirstLineFromFile(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();
        }
    }
}
