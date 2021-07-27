package classes

import kotlin.js.JsName
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

@Ignore
class Object {
  @Test
  @JsName("JsTest1")
  fun `Creating anonymous objects from scratch`() {
    class ObjectJava {
      val hello = "Hello"
      val world = "World"

      override fun toString(): String {
        return hello + ' ' + world
      }
    }

    val objectKotlin = object {
      val hello = "Hello"
      val world = "World"

      // object expressions extend Any, so `override` is required on `toString()`
      override fun toString() = "$hello $world"
    }
    assertEquals("Hello World", objectKotlin.toString())
  }

  @Test
  @JsName("JsTest2")
  fun `Inheriting anonymous objects from supertypes`() {
    open class AnonymousB {
      open fun result(i: Int) = i
    }

    class B {
      fun foo(value: AnonymousB) = value.result(4)
    }
    assertEquals(16, B().foo(object : AnonymousB() {
      override fun result(i: Int) = i * i
    }))
  }

  interface B

  @Test
  @JsName("JsTest3")
  fun `Inheriting anonymous objects from supertypes with a constructor`() {
    open class A(x: Int) {
      open val y: Int = x
    }

    class JavaObjectab(x: Int) : A(x), B {
      override val y = 15
    }
    assertEquals(15, JavaObjectab(1).y)

    val ab: A = object : A(1), B {
      override val y = 15
    }
    assertEquals(15, ab.y)
  }

  @Test
  @JsName("JsTest4")
  fun `Using anonymous object as return`() {
    class C {
      //The anonymous objects can only be used as types only in local and private declarations.
      //Any as a return type of a public function or the type of a public property
      private fun getObject() = object {
        val x: String = "x"
      }

      fun printX() = getObject().x
    }
    assertEquals("x", C().printX())
  }

  interface A {
    fun funFromA(): String
  }

  @Test
  @JsName("JsTest5")
  fun `Using anonymous object as return and value types`() {
    class C {
      // The return type is Any. x is not accessible
      fun getObject(): Any = object {
        val x: String = "x"
        override fun toString(): String {
          return x
        }
      }

      // The return type is A; x is not accessible
      fun getObjectA(): A = object : A {
        override fun funFromA() = x
        val x: String = "x"
      }

      // The return type is B; funFromA() and x are not accessible
      fun getObjectB(): B = object : A, B { // explicit return type is required
        override fun funFromA() = x
        val x: String = "x"
        override fun toString(): String {
          return x
        }
      }
    }
    assertEquals("x", C().getObject().toString())
    assertEquals("x", C().getObjectA().funFromA())
    assertEquals("x", C().getObjectB().toString())
  }

  @Test
  @JsName("JsTest6")
  fun `Accessing variables from anonymous objects`() {
    open class AnonymousB {
      open fun result(i: Int) = i
    }

    class B {
      fun foo(value: AnonymousB) = value.result(4)
    }

    fun countClicks(parent: B): Int {
      val delta = 10

      return parent.foo(object : AnonymousB() {
        override fun result(i: Int): Int {
          return i + delta
        }
      })

    }

    assertEquals(14, countClicks(B()))
  }

  object SingletonKotlin {
    fun counter(): Int {
      return value++
    }

    var value: Int = 0
  }

  interface ISingletonKotlin {
    var value: Int
    fun counter(): Int
  }

  object SingletonTypeKotlin : ISingletonKotlin {
    override fun counter(): Int {
      return value++
    }

    override var value: Int = 0
  }

  @Test
  @JsName("JsTest7")
  fun `Object Singleton declarations`() {
    assertEquals(0, SingletonKotlin.counter())
    assertEquals(1, SingletonKotlin.counter())
    assertEquals(2, SingletonKotlin.counter())

    val singletonKotlin: ISingletonKotlin = SingletonTypeKotlin
    assertEquals(0, singletonKotlin.counter())
    assertEquals(1, singletonKotlin.counter())
    assertEquals(2, singletonKotlin.counter())
  }

  class MyClass1 {
    companion object Named {
      fun StaticFunctions(): MyClass1 = MyClass1()
      val VALUE = "x"
    }
  }

  class MyClass2 {
    companion object {
      fun StaticFunctions(): MyClass2 = MyClass2()
      val VALUE = "x"
    }
  }

  @Test
  @JsName("JsTest8")
  fun `Companion objects or Static`() {
    assertEquals("MyClass1", MyClass1.StaticFunctions()::class.simpleName)
    assertEquals("x", MyClass1.VALUE)
    assertEquals("x", MyClass1.Named.VALUE)

    assertEquals("MyClass2", MyClass2.StaticFunctions()::class.simpleName)
    assertEquals("x", MyClass2.VALUE)
    assertEquals("x", MyClass2.Companion.VALUE)
  }
}