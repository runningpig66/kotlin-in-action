package ch07.ex5_3_3_ImplementingDelegatedProperties2

import java.beans.PropertyChangeEvent
import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport
import kotlin.reflect.KProperty

/**
 * @author runningpig66
 * @date 2025/12/11 周四
 * @time 0:05
 * §7.5 重用属性访问的逻辑：委托属性 §7.5.3 实现委托属性
 * P.200 代码清单 7.22 ObservableProperty 作为属性委托
 * P.201 代码清单 7.23 使用委托属性来绑定更改通知
 *
 * 现在，你应该已经差不多理解了在 Kotlin 中，委托属性是如何工作的。你创建了一个保存属性值的类，并在修改属性时自动触发更改通知。
 * 你删除了重复的逻辑代码，但是需要相当多的样板代码来为每个属性创建 ObservableProperty 实例，并把 getter 和 setter 委托给它。
 * Kotlin 的委托属性功能可以让你摆脱这些样板代码。但是在此之前，你需要更改 ObservableProperty 方法的签名，来匹配 Kotlin 约定所需的方法。
 */
open class PropertyChangeAware {
    protected val changeSupport = PropertyChangeSupport(this)

    fun addPropertyChangeListener(listener: PropertyChangeListener) {
        changeSupport.addPropertyChangeListener(listener)
    }

    fun removePropertyChangeListener(listener: PropertyChangeListener) {
        changeSupport.removePropertyChangeListener(listener)
    }
}

// 与之前的版本相比，这次代码做了一些更改：
// • 现在，按照约定的需要，getValue 和 setValue 函数被标记了 operator。
// • 这些函数加了两个参数：一个用于接收属性的实例，用来设置或读取属性，另一个用于表示属性本身。这个属性类型为 KProperty。
//   我们将在 10.2 节中详细介绍它：现在，你就可以使用 KProperty.name 的方式来访问该属性的名称。
// • 把 name 属性从主构造方法中删除了，因为现在已经可以通过 KProperty 访问属性名称。
class ObservableProperty(var propValue: Int, val changeSupport: PropertyChangeSupport) {
    operator fun getValue(p: Person, prop: KProperty<*>): Int = propValue
    operator fun setValue(p: Person, prop: KProperty<*>, newValue: Int) {
        val oldValue = propValue
        propValue = newValue
        changeSupport.firePropertyChange(prop.name, oldValue, newValue)
    }
}

// TODO: 源码
// 通过关键字 by，Kotlin 编译器会自动执行之前版本的代码中手动完成的操作。
// 如果把这份代码与以前版本的 Person 类进行比较：使用委托属性时生成的代码非常类似。右边的对象被称为委托。
// Kotlin 会自动将委托存储在隐藏的属性中，并在访问或修改属性时调用委托的 getValue 和 setValue。
class Person(val name: String, age: Int, salary: Int) : PropertyChangeAware() {
    var age: Int by ObservableProperty(age, changeSupport)
    var salary: Int by ObservableProperty(salary, changeSupport)
}

fun main() {
    val p = Person("Dmitry", 34, 2000)
    p.addPropertyChangeListener { event: PropertyChangeEvent ->
        println(
            "Property ${event.propertyName} changed " +
                    "from ${event.oldValue} to ${event.newValue}"
        )
    }
    p.age = 35
    p.salary = 2100
}
/* Output:
Property age changed from 34 to 35
Property salary changed from 2000 to 2100
 */
