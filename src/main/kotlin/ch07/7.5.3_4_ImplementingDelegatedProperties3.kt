package ch07.ex5_3_4_ImplementingDelegatedProperties3

import java.beans.PropertyChangeEvent
import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

/**
 * @author runningpig66
 * @date 2025/12/11 周四
 * @time 0:56
 * §7.5 重用属性访问的逻辑：委托属性 §7.5.3 实现委托属性
 * P.201 代码清单 7.24 使用 Delegates.observable 来实现属性修改的通知
 *
 * 你不用手动去实现可观察的属性逻辑。可以使用 Kotlin 标准库，它已经包含了类似于 ObservableProperty 的类。
 * 标准库类和这里使用的 PropertyChangeSupport 类没有耦合，因此你需要传递一个 lambda，来告诉它如何通知属性值的更改。
 *
 * by 右边的表达式不一定是新创建的实例，也可以是函数调用、另一个属性或任何其他表达式，
 * 只要这个表达式的值，是能够被编译器用正确的参数类型来调用 getValue 和 setValue 的对象。
 * 与其他约定一样，getValue 和 setValue 可以是对象自己声明的方法或扩展函数。
 *
 * Delegates.observable 函数可以用来添加属性更改的观察者。
 * public final inline fun <T> observable(
 *     initialValue: T,
 *     crossinline onChange: (KProperty<*>, T, T) -> Unit
 * ): ReadWriteProperty<Any?, T>
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

class Person(val name: String, age: Int, salary: Int) : PropertyChangeAware() {
    private val observer = { prop: KProperty<*>, oldValue: Int, newValue: Int ->
        changeSupport.firePropertyChange(prop.name, oldValue, newValue)
    }
    var age: Int by Delegates.observable(age, observer)
    var salary: Int by Delegates.observable(salary, observer)
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
