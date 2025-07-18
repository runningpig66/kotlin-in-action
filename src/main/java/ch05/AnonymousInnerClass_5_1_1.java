package ch05;

interface View {
}

// 代码清单 5.1 用匿名内部类实现监听器
interface OnClickListener {
    void onClick(View v);
}

class Button {
    void setOnClickListener(OnClickListener onClickListener) {
    }
}

class AnonymousInnerClass_5_1_1 {
    public static void main(String[] args) {
        Button button = new Button();
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                /* 点击后执行的动作 */
            }
        });

        Button button1 = new Button();
        button1.setOnClickListener(v -> {
            /* 点击后执行的动作 */
        });
    }
}