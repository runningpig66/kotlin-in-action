package ch04;

import ch04.Button1.State;
import ch04.Button1.View;
import org.jetbrains.annotations.NotNull;

/**
 * @author runningpig66
 * @date 2025/11/22 周六
 * @time 16:16
 * §4.1 定义类继承结构 §4.1.4 内部类和嵌套类：默认是嵌套类
 * P.77 代码清单 4.10 用带内部类的 Java 代码来实现 View
 */
public class Button implements View {
    @Override
    @NotNull
    public State getCurrentState() {
        return new ButtonState();
    }

    @Override
    public void restoreState(@NotNull State state) {
        View.super.restoreState(state);
    }

    // java.io.NotSerializableException: Button
    // 这个例子中的 ButtonState 类隐式地存储了它的外部 Button 类的引用。
    // 这就解释了为什么 ButtonState 不能被序列化：Button 不是可序列化的，并且它的引用破坏了 ButtonState 的序列化。
    // 要修复这个问题，你需要声明 ButtonState 类是 static 的。
    // 将一个嵌套类声明为 static 会从这个类中删除包围它的类的隐式引用。
    public class ButtonState implements State {
    }
}
