package types

import kotlin.js.JsName
import kotlin.test.*

@Ignore
class AnyUnitNothing {

  @Test
  @JsName("JsTest1")
  fun `How get class Unit`(){
    val u = print("")
    print("Print result is ")
    println(u::class.toString())
    assertEquals("Unit", u::class.simpleName)
  }

  @Test
  @JsName("JsTest2")
  fun `How get class Any`(){

    val any: Any = Any()

    print("any.equals(Any()) = ")
    println(any.equals(Any()))
    assertEquals(false,any.equals(Any()))

    print("any.hashCode() = ")
    println(any.hashCode())
    assertEquals(any.hashCode() == Any().hashCode(), false)
    assertEquals(any.hashCode() == any.hashCode(), true)

    print("any.toString() = ")
    println(any.toString())

  }

  @Test
  @JsName("JsTest3")
  fun `How get class Nothing`(){

    println("TODO() = Exception")
    assertFails { TODO() }

    fun iWillAlwaysThrowException() : Nothing =  throw Exception("Unnecessary Exception")
    println("iWillAlwaysThrowException() = Exception")
    assertFails { iWillAlwaysThrowException() }
    assertFailsWith<Exception>("Unnecessary Exception"){iWillAlwaysThrowException() }

  }

}
