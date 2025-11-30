 * §5.4 使用 Java 函数式接口 §5.4.2 SAM 构造方法：显式地把 lambda 转换成函数式接口
 * P.130 代码清单 5.15 使用 SAM 构造方法来重用 listener 实例

**书中段落：Lambda 和添加 / 移除监听器**
    **注意 lambda 内部没有匿名对象那样的 this: 没有办法引用到 lambda 转换成的匿名类实例。从编译器的角度来看，lambda 是一个代码块，不是一个对象，而且也不能把它当成对象引用。Lambda 中的 this 引用指向的是包围它的类。**
    **如果你的事件监听器在处理事件时还需要取消它自己，不能使用 lambda 这样做。这种情况使用实现了接口的匿名对象。在匿名对象内，this 关键字指向该对象实例，可以把它传给移除监听器的 API.**

------

对，你这个地方卡住的点，其实就是**“谁来在 *哪里* 把监听器移除”**。

书上这段话说的是一个**很具体的场景**，跟你现在这个「Button 里只有一个 listener 变量，外面手动置 null」的写法不是一回事。

------

## 1. 书上在说什么场景？

典型的 GUI / 事件 API 是这样的：

```kotlin
fun addListener(listener: OnClickListener)
fun removeListener(listener: OnClickListener)
```

也就是说：

- 按钮内部会维护一堆监听器列表；
- 你可以 `addListener(l1)`、`addListener(l2)`……加很多；
- 要移除时，必须把**同一个实例**再传回 `removeListener(listener)` 里。

现在有一种需求：
 **监听器在处理事件时，发现自己不用再监听了，要“自杀式注销自己”**。

用匿名对象就很好写（这就是书里说“用匿名对象”的原因）：

```kotlin
button.addListener(
    object : OnClickListener {
        override fun onClick(view: View) {
            // 干完自己的事
            println("clicked once, then unregister myself")

            // 这里的 this 就是“当前这个监听器对象”
            button.removeListener(this)
        }
    }
)
```

这里关键点：

- 匿名对象是一个真正的对象；
- 在它的 `onClick` 里，`this` 指向的就是**这个监听器实例本身**；
- 所以可以直接 `button.removeListener(this)`，把自己从按钮的监听列表里注销掉。

书上那句话的意思就是：

> 如果监听器要在回调里“自我移除”，不能用 lambda，要用匿名对象。
>  因为匿名对象里有指向自身的 `this`，lambda 里没有。

------

## 2. 为什么 lambda 做不到“在内部把自己移除”？

你现在的代码：

```kotlin
val listener2 = OnClickListener { view: View ->
    val text: String = when (view.id) { ... }
    println(text)
}
button.setOnClickListener(listener2)
```

**注意 lambda 里的 `this` 指向谁**：

- 在 *lambda* 代码块里：`this` 并不是“这段 lambda 对应的监听器对象”；
- 而是**外层的接收者（外层 class / 外层对象）**，也就是你定义这个 lambda 的那个类实例。

也就是说，在 lambda 里面你写：

```kotlin
this
```

**拿不到“当前监听器对象”的引用**，而是拿到外层类的 `this`。

底层确实会帮你生成一个实现了 `OnClickListener` 的匿名类实例，但：

- 这个实例对你是“透明”的；
- 你在 lambda 代码块里**没有任何关键字能指向这个实例本身**。

所以你没法在 lambda 里写出等价的：

```kotlin
button.removeListener(this)   // 这里的 this 不是监听器
```

------

## 3. 你现在为什么“感觉可以随便移除”？

你的 `Button` 目前是这样的：

```kotlin
class Button : View {
    private var listener: OnClickListener? = null

    fun setOnClickListener(listener: OnClickListener?) {
        this.listener = listener
    }

    fun onClick() {
        listener?.onClick(this)
    }
}
```

这个设计只有一个 listener 变量：

- 外面想注销，完全可以：`button.setOnClickListener(null)`；
- 所以你会觉得：

> 我不管是 lambda 还是匿名对象，都可以随便移除啊，
>  直接把 `Button` 里那个字段清空就完事了。

**这没问题，但这是“从外面移除监听器”，不是“监听器自己在回调里移除自己”。**

书上讨论的是**监听器自己在 onClick/onEvent 里调用 `removeListener(this)` 的模式**，这在有多个监听器、或者监听器生命周期复杂时非常常见。

------

## 4. 用 lambda 想实现“自我注销”要怎么写？

可以写，但就会显得很绕，需要多一层变量：

```kotlin
lateinit var listener: OnClickListener

listener = OnClickListener { view ->
    println("clicked once, then unregister")
    button.removeListener(listener)   // 注意这里用的是外层变量 listener
}

button.addListener(listener)
```

这里的 trick 是：

- 我们把监听器实例存在外层变量 `listener` 里；
- lambda 内部闭包捕获的是**外层变量 listener**，所以能拿到同一个实例；
- 但这就不是 `this` 了，而是靠外部变量“兜一圈”。

对比匿名对象版本：

```kotlin
button.addListener(object : OnClickListener {
    override fun onClick(view: View) {
        button.removeListener(this)
    }
})
```

匿名对象这边就很自然——这就是书上想强调的差别。

------

## 5. 回到你代码里的两个 listener

再对一遍你图里的两种写法：

```kotlin
// listener1：匿名对象
val listener1: OnClickListener = object : OnClickListener {
    override fun onClick(view: View) { ... }
}

// listener2：SAM + lambda
val listener2 = OnClickListener { view: View ->
    ...
}
```

两个都可以：

- **从外部**：通过 `button.listener = null` 或 `button.removeListener(listenerX)` 来注销；
- 但只有 `listener1` 在自己的 `onClick` 里能直接写 `button.removeListener(this)`。

用 `listener2` 想做同样事，就得写成前面那种 `lateinit var listener` 的闭包写法。

------

## 6. 小结一句话版

- **你说的“button 里把引用置 null 就行了”是“外部控制监听器生命周期”，当然没问题。**
- 书上说的“不能用 lambda”指的是另一种模式：**监听器在自己的回调里，通过 `this` 把自己从事件源上摘掉**。
- 匿名对象有自己的 `this`，lambda 没有，所以这个场景下要用匿名对象。

如果你愿意，我可以帮你在这段代码下面加一小块对比示例：
 “匿名对象在回调中自注销 vs lambda 在回调中自注销（闭包写法）”，放进你当前的 Kotlin 笔记里，后面复习一眼就能想起来区别
