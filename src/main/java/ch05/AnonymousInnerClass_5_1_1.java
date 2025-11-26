package ch05;

/**
 * @author runningpig66
 * @date 2025/11/25 周二
 * @time 14:47
 * §5.1 Lambda 表达式和成员引用 §5.1.1 Lambda 简介：作为函数参数的代码块
 * P.106 代码清单 5.1 用匿名内部类实现监听器
 */
interface View {
}

@FunctionalInterface
interface OnClickListener {
    void onClick(View view);
}

class Button implements View {
    private OnClickListener listener;

    public OnClickListener getListener() {
        return listener;
    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    public void onClick(View v) {
        listener.onClick(v);
    }
}

public class AnonymousInnerClass_5_1_1 {
    public static void main(String[] args) {
        // 匿名内部类
        Button button1 = new Button();
        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("button1 clicked");
            }
        });
        button1.onClick(button1);

        // Java 中的 lambda 表达式
        Button button2 = new Button();
        button2.setOnClickListener(view -> {
            System.out.println("button2 clicked");
        });
        button2.onClick(button2);

        // Java 中的 lambda 表达式。保存 lambda
        OnClickListener listener = v -> System.out.println("button3 clicked");
        Button button3 = new Button();
        button3.setOnClickListener(listener);
        listener.onClick(button3);
    }
}
/* Output:
button1 clicked
button2 clicked
button3 clicked
 */
