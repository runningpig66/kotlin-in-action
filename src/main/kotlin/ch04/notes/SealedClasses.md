**Kotlin 1.5+ 放宽了密封类定义规则：**
**“子类位置”规则已经更新（同 module + 同 package）；**
**现在有 sealed interface，适合用来做 UI 事件、结果类型等根接口。**

## 给你系统梳理一遍 Kotlin 的“密封类 / 密封接口”

下面这部分可以直接当你“密封类”这一节的笔记。

### 1. 密封的核心直觉：**“有限且已知的一族子类型”**

`sealed` 的目的就是：**限制继承层级**。

- 普通 `open` / `interface`：任何地方都可以随便继承；
- `final`：谁都不能继承；
- `sealed`：**只有“被允许的那几个”可以继承**，并且这几个在编译期就确定了。

典型用途：表示一组**状态 / 结果**，比如：

```kotlin
sealed class Result
data class Success(val data: String) : Result()
data class Failure(val error: Throwable) : Result()
object Loading : Result()
```

这样，`Result` 的所有真实类型只有 `Success`、`Failure`、`Loading` 这几个——不会突然冒出第 4 个子类，因为编译器不让你在别的地方继承。

------

### 2. 语法 & 规则（包含新版 Kotlin 的变化）

#### 2.1 声明方式

```kotlin
sealed class Expr

sealed interface UiEvent
```

注意：

- `sealed class` **隐式是 abstract**，不能直接实例化。
- 子类可以是 `class` / `data class` / `object` 等。

#### 2.2 子类必须放在哪？

这是跟书上不一样的地方，重点记一下。

- **早期 Kotlin（书里的版本）**：
   子类必须**定义在同一个 `.kt` 文件**里，通常是密封类里面的嵌套类 / 同文件顶层类。

- **现在的 Kotlin（1.5+）**：
   子类只要满足两个条件就行：

  1. 和密封类 / 密封接口在**同一个 module**；
  2. 在**同一个 package**。

  也就是说，你可以拆到多个文件里，只要还在**同一个包、同一个模块**就行：

  ```kotlin
  // file: result/Result.kt
  package result
  sealed interface Result
  
  // file: result/Success.kt
  package result
  data class Success(val data: String): Result
  
  // file: result/Failure.kt
  package result
  data class Failure(val error: Throwable): Result
  ```

这块就是你如果严格按书上的老规则写，代码当然没错，但实际上现在编译器比书上说的“放得更宽”。

------

### 3. 和 `enum` / 抽象类 的对比

#### 3.1 vs `enum class`

`enum` 也表示有限集合，那为什么还要 `sealed`？

- `enum`：
  - 每个枚举值是**单例**（只有一个 `RED`）。
  - 每个枚举常量不能再派生新的子类。
  - 虽然可以给枚举加字段，但一般是**极其简单的状态**。
- `sealed class` / `sealed interface`：
  - 每个子类可以有**自己的字段、自己的结构**，也可以有很多实例。
  - 可以是 `data class`，天然适合携带数据。

比如表达一个“算术表达式语法树”：

```kotlin
sealed interface Expr
data class Num(val value: Int) : Expr
data class Sum(val left: Expr, val right: Expr) : Expr
data class Neg(val expr: Expr) : Expr
```

这种结构用 `enum` 很难写清楚，用 `sealed` 非常自然。

**记忆点**：

> 只需要“几种标签”？用 `enum`。
>  需要“几种不同形状的数据结构”？用 `sealed`。

#### 3.2 vs 抽象类 / 接口

- 抽象类 / 接口：**对外开放**，别人可以随便实现；
- 密封类 / 密封接口：**只允许“白名单里的那几个”实现**。

`sealed` 的感觉是：

> 在继承上，“介于 `open` 和 `final` 之间”。

------

### 4. `when` 的穷尽性检查（密封类最大的实用价值）

当你对一个 `sealed` 类型做 `when` 时，编译器知道所有可能的子类，所以可以帮你检查**是否枚举完所有情况**：

```kotlin
fun handle(result: Result): String =
    when (result) {
        is Success -> "OK: ${result.data}"
        is Failure -> "Error: ${result.error.message}"
        Loading    -> "Loading..."
        // 没有 else，编译器仍然认为是完整的
    }
```

如果以后你又加了一个 `object Canceled : Result()`，`when` 会**立刻报错**，提醒你这里还没处理 `Canceled`——这就是用 `sealed` 的巨大好处。

------

### 5. 密封接口（回答你之前的问题）

> 可以声明一个 `sealed interface` 吗？

可以，而且是现在常见的写法，官方文档也推荐在很多场景用密封接口来做“根类型”。

什么时候用 `sealed interface` 而不是 `sealed class`？

- **想让实现类继续继承别的类 / 实现别的接口；**
- **根类型本身不需要状态，只是一个“标签类型”。**

示例：

```kotlin
sealed interface UiEvent

data class Click(val x: Int, val y: Int) : UiEvent
data class TextChanged(val text: String) : UiEvent
object Submit : UiEvent
```

如果你想在根类型里**共享代码或状态**，那就用 `sealed class`：

```kotlin
sealed class NetworkState(open val time: Long)

data class Connected(override val time: Long) : NetworkState(time)
data class Disconnected(override val time: Long, val reason: String) : NetworkState(time)
```

------

### 6. 使用密封类 / 接口时可以注意的点

给你几个 checklist，跟你现在的理解对一下：

1. **只在真的“有限集合”场景用 sealed**
    比如：网络状态、解析结果、UI 事件、表达式 AST、后台任务状态。

2. **配合 `when` 写业务逻辑**
    不要在各个子类里 `if (this is XXX)` 乱判断，集中用一个 `when` 来“模式匹配”。

3. **模块划分要想清楚**
    因为“所有子类必须在同 module 同 package”，所以 sealed 的根类型一般放在一个“核心模块/包”里，避免以后想拆模块被规则卡住。

4. **记得书上那条“子类必须写在一个文件里”的说法已过期**
    你可以在笔记里标一句：

   > *KIA 旧规则：子类必须在同一个文件里；
   >  现行规则：同 module + 同 package 即可。*
