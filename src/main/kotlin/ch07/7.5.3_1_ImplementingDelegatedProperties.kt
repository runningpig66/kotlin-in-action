package ch07.ImplementingDelegatedProperties

import java.beans.PropertyChangeEvent
import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport

/**
 * @author runningpig66
 * @date 2025/12/10 周三
 * @time 17:11
 * §7.5 重用属性访问的逻辑：委托属性 §7.5.3 实现委托属性
 * P.198 代码清单 7.19 使用 PropertyChangeSupport 的工具类
 * P.199 代码清单 7.20 手工实现属性修改的通知
 *
 * 要了解委托属性的实现方式，让我们来看另一个例子：当一个对象的属性更改时通知监听器。
 * 这在许多不同的情况下都很有用：例如，当对象显示在 UI 时，你希望在对象变化时 UI 能自动刷新。
 * Java 具有用于此类通知的标准机制：PropertyChangeSupport 和 PropertyChangeEvent 类。
 * 让我们看看在 Kotlin 中在不使用委托属性的情况下，该如何使用它们，然后我们再将代码重构为用委托属性的方式。
 *
 * PropertyChangeSupport 类维护了一个监听器列表，并向它们发送 PropertyChangeEvent 事件。
 * 要使用它，你通常需要把这个类的一个实例存储为 bean 类的一个字段，并将属性更改的处理委托给它。
 *
 * 为了避免要在每个类中去添加这个字段，你需要创建一个小的工具类，
 * 用来存储 PropertyChangeSupport 的实例并监听属性更改。之后，你的类会继承这个工具类，以访问 changeSupport。
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
    var age: Int = age
        set(newValue) {
            // "field" 标识符允许你访问属性背后的支持字段
            val oldValue = field
            field = newValue
            // 当属性变化时，通知监听器
            changeSupport.firePropertyChange("age", oldValue, newValue)
        }
    var salary: Int = salary
        set(newValue) {
            val oldValue = field
            field = newValue
            changeSupport.firePropertyChange("salary", oldValue, newValue)
        }
}

fun main() {
    val p = Person("Dmitry", 34, 2000)
    // 关联监听器，用于监听属性修改
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
