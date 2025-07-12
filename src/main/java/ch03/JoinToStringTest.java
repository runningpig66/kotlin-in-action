package ch03;

import strings.StringFunctions;

import java.util.Arrays;
import java.util.List;

import static strings._3_3_ExtensionsKt.lastChar;

public class JoinToStringTest {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3);
        System.out.println(StringFunctions.joinToString(list, ", ", "", ""));

        System.out.println(lastChar("Java"));
    }
}
/** Output:
 * 1, 2, 3
 * a
 */