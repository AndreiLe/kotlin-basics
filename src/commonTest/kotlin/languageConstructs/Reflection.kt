package languageConstructs

import flow.E
import kotlin.js.JsName
import kotlin.reflect.KClass
import kotlin.reflect.cast
import kotlin.reflect.safeCast
import kotlin.reflect.typeOf
import kotlin.test.*

@Ignore
class Reflection {
//  The most Reflection API not supported yet in js

  @Test
  @JsName("JsTest1")
  fun `Kotlin Class References`() {
//    val someClass: Class<MyType>
//    val kotlinClass: KClass<MyType> = someClass.kotlin
    val listClass: KClass<List<*>> = List::class
    assertEquals("List", listClass.simpleName)

    val name = "Baeldung"
    val stringClass: KClass<out String> = name::class
    assertEquals("String", stringClass.simpleName)

    val stringClass2 = String::class
    assertEquals("String", stringClass2.simpleName)
  }

  @Test
  @JsName("JsTest2")
  fun `Callable Reference`() {
    val str = "Hello"
    val lengthMethod = str::length
    assertEquals(5, lengthMethod())
  }

  @Test
  @JsName("JsTest3")
  fun `extension functions`() {
    val s = "true"
    val b = true

    assertEquals("String", s::class.simpleName)
    assertEquals("String", String::class.simpleName)

    assertEquals(false, String::class.isInstance(b))
    assertEquals(true, String::class.isInstance(s))

    assertEquals("true", String::class.cast(s) )
    assertEquals(null, String::class.safeCast(b) )
    assertFails { String::class.cast(b) }
  }

  @Test
  @JsName("JsTest4")
  fun `Constructor references`() {
    class Foo
    fun function(factory: () -> Foo):Foo {
      return factory()
    }
    assertEquals("Foo", function(::Foo)::class.simpleName)
  }

  @Test
  @JsName("JsTest5")
  fun `Bound constructor references`() {
    class Outer {
      inner class Inner
    }
    val o = Outer()
    val boundInnerCtor = o::Inner
    assertEquals("Inner", boundInnerCtor()::class.simpleName)
  }

}
