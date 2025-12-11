package ch07.ex5_3_2_ImplementingDelegatedProperties1

import java.beans.PropertyChangeEvent
import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport

/**
 * @author runningpig66
 * @date 2025/12/10 周三
 * @time 23:30
 * §7.5 重用属性访问的逻辑：委托属性 §7.5.3 实现委托属性
 * P.200 代码清单 7.21 通过辅助类来实现属性变化的通知
 *
 * 代码清单 7.20 的 setter 中有很多重复的代码。我们来尝试提取一个类，用来存储这个属性的值并发起通知。
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

class ObservableProperty(
    val propName: String, var propValue: Int,
    val changeSupport: PropertyChangeSupport
) {
    fun getValue(): Int = propValue
    fun setValue(newValue: Int) {
        val oldValue = propValue
        propValue = newValue
        changeSupport.firePropertyChange(propName, oldValue, newValue)
    }
}

class Person(val name: String, age: Int, salary: Int) : PropertyChangeAware() {
    private val _age = ObservableProperty("age", age, changeSupport)
    var age: Int
        get() = _age.getValue()
        set(value) {
            _age.setValue(value)
        }
    private val _salary = ObservableProperty("salary", salary, changeSupport)
    var salary: Int
        get() = _salary.getValue()
        set(value) {
            _salary.setValue(value)
        }
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
