package ch04;

import ch04.Button1.State;
import ch04.Button1.View;
import org.jetbrains.annotations.NotNull;

// 代码清单 4.10 用带内部类的 Java 代码来实现 View
public class Button implements View {
    @Override
    public @NotNull State getCurrentState() {
        return new ButtonState();
    }

    @Override
    public void restoreState(@NotNull State state) {
    }

    public static class ButtonState implements State {
    }
}
