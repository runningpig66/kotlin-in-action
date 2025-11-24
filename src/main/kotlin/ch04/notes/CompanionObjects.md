[TOC]

## （伴生）对象字节码对比

### 伴生对象

```kotlin
class Person private constructor(val name: String) {
    companion object {
        const val DEFAULT_AGE = 0
        fun fromName(name: String) = Person(name)
    }
}
```

```java
public final class Person {
   public static final Companion Companion = new Companion();
   private final String name;
    // 把伴生对象里的 const val 提升成外围类的静态常量，方便 JVM / Java 使用。
   public static final int DEFAULT_AGE = 0;

   private Person(String name) {
      this.name = name;
   }

   public final String getName() {
      return this.name;
   }

   public static final class Companion {
      private Companion() {
      }

      public final Person fromName(String name) {
         return new Person(name);
      }
   }
}
```

### 顶层对象声明

```kotlin
object Person {
    const val NAME: String = "Bob"
    val age: Int = 0
    var address: String = "ITABASHI"
}
```

```java
public final class Person {
   public static final Person INSTANCE = new Person();
   public static final String NAME = "Bob";
   private static final int age;
   private static String address = "ITABASHI";

   private Person() {
   }

   public final int getAge() {
      return age;
   }

   public final String getAddress() {
      return address;
   }

   public final void setAddress(@NotNull String var1) {
      address = var1;
   }
}
```

### 嵌套对象声明

```kotlin
class Person {
    object NameComparator {
        const val NAME: String = "Bob"
        val age: Int = 0
        var address: String = "ITABASHI"
    }
}
```

```java
public final class Person {
   public static final class NameComparator {
      public static final NameComparator INSTANCE = new NameComparator();
      public static final String NAME = "Bob";
      private static final int age;
      private static String address = "ITABASHI";

      private NameComparator() {
      }

      public final int getAge() {
         return age;
      }

      public final String getAddress() {
         return address;
      }

      public final void setAddress(@NotNull String var1) {
         address = var1;
      }
   }
}
```

### 伴生对象扩展

```kotlin
class Person(val firstName: String, val lastName: String) {
    // 声明一个空的伴生对象
    companion object;
    override fun toString(): String {
        return "Person(firstName='$firstName', lastName='$lastName')"
    }
}

// 声明一个扩展函数
fun Person.Companion.fromJSON(json: String): Person {
    val jsonList = json.split("'")
    val firstName = jsonList[1]
    val lastName = jsonList[3]
    return Person(firstName, lastName)
}

fun main() {
    val person = Person.fromJSON("{first_name: 'Fight', last_name: 'Night'}")
    val person1 = Person.Companion.fromJSON("{first_name: 'Fight', last_name: 'Night'}")
    println(person)
    println(person1)
}
/* Output:
Person(firstName='Fight', lastName='Night')
Person(firstName='Fight', lastName='Night')
 */
```

```java
public final class Person {
   public static final Companion Companion = new Companion();
   private final String firstName;
   private final String lastName;

   public Person(String firstName, String lastName) {
      this.firstName = firstName;
      this.lastName = lastName;
   }

   public final String getFirstName() {
      return this.firstName;
   }

   public final String getLastName() {
      return this.lastName;
   }

   public String toString() {
      return "Person(firstName='" + this.firstName + "', lastName='" + this.lastName + "')";
   }
   public static final class Companion {
      private Companion() {
      }
   }
}
// _4_4_3_CompanionObjects3Kt.java
package ch04.ex4_3_CompanionObjects3;

public final class _4_4_3_CompanionObjects3Kt {
   public static final Person fromJSON(Person.Companion $this$fromJSON, String json) {
      CharSequence var10000 = (CharSequence)json;
      String firstName = new String[]{"'"};
      List jsonList = StringsKt.split$default(var10000, firstName, false, 0, 6, (Object)null);
      firstName = (String)jsonList.get(1);
      String lastName = (String)jsonList.get(3);
      return new Person(firstName, lastName);
   }

   public static final void main() {
      Person person = fromJSON(Person.Companion, "{first_name: 'Fight', last_name: 'Night'}");
      Person person1 = fromJSON(Person.Companion, "{first_name: 'Fight', last_name: 'Night'}");
      System.out.println(person);
      System.out.println(person1);
   }
}
```
