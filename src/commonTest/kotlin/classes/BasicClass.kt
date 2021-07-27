package classes

import kotlin.js.JsName
import kotlin.test.Test
import kotlin.test.assertEquals

//@Ignore
class BasicClass {

  @Test
  @JsName("JsTest1")
  fun `Creating a Class and Instances of a Class`() {
    class BasicClass

    val instance = BasicClass()

    class BasicClassFactory {
      fun BasicClass() = BasicClass()
    }

    val instance2 = BasicClassFactory().BasicClass()
    assertEquals(instance::class, instance2::class)
  }

  @Test
  @JsName("JsTest2")
  fun `Primary and Secondary Constructor`() {
    class Person constructor(firstName: String) {
      var greeting: String

      init {
        greeting = "Hello $firstName!"
      }

      fun hello() = this.greeting
    }

    class Person2(firstName: String = "John") {
      val greeting = "Hello $firstName!"
      fun hello() = this.greeting
    }

    class Person3 {
      val greeting: String

      constructor(firstName: String) {
        greeting = "Hi $firstName!"
      }

      constructor(firstName: String, say: String) {
        greeting = "$say $firstName!"
      }

      fun hello() = this.greeting
    }

    class Person4 {
      var greeting: String

      constructor() : this("John", "Hello")

      constructor(firstName: String) : this(firstName, "Hello")

      constructor(firstName: String, say: String) {
        greeting = "$say $firstName!"
        println("constructor")
      }

      fun hello() = this.greeting
    }
    assertEquals(Person("John").hello(), Person2().hello())
    assertEquals(Person("John").hello(), Person3("John", "Hello").hello())
    assertEquals(Person("John").hello(), Person4().hello())
  }

  @Test
  @JsName("JsTest3")
  fun `Class init order`() {
    class InitOrder(name: String) {

      val firstProperty = "First property: $name".also(::println)

      init {
        println("First initializer block that prints ${name}")
      }

      val secondProperty = "Second property: ${name.length}".also(::println)

      init {
        println("Second initializer block that prints ${name.length}")
      }
    }
    InitOrder("John")

    open class InitOrderWithSecondaryConstructor {

      constructor() : this("John") {
        println("First constructor")
      }

      constructor(firstName: String) {
        println("Second constructor")
      }

      open val firstProperty = "First property".also(::println)

      init {
        println("First initializer")
      }

      open val secondProperty = "Second property".also(::println)

      init {
        println("Second initializer")
      }
    }
//    InitOrderWithSecondaryConstructor()

    class InitOrderWithInheritance : InitOrderWithSecondaryConstructor {

      constructor() : this("John") {
        println("First constructor inheritance")
      }

      constructor(firstName: String) {
        println("Second constructor inheritance")
      }

      override val firstProperty = "First property inheritance".also(::println)

      init {
        println("First initializer inheritance")
      }

      override val secondProperty = "Second property inheritance".also(::println)

      init {
        println("Second initializer inheritance")
      }
    }
    InitOrderWithInheritance()
  }

}