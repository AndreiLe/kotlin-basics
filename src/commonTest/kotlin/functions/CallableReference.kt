package functions

import kotlin.js.JsName
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

interface Hello {
  fun say(): String
}

@Ignore
class CallableReference {
  @Test
  @JsName("JsTest1")
  fun `Function references in Unit-returning functions`() {
    fun foo(f: () -> Unit) {/* empty body */}
    fun returnsInt(): Int = 42
    assertEquals(Unit, foo { returnsInt() }) // this was the only way to do it before 1.4
    assertEquals(Unit, foo(::returnsInt)) // starting from 1.4, this also works
  }

  @Test
  @JsName("JsTest2")
  fun `References to functions with default argument values`() {

    fun foo(i: Int = 0): String = "$i!"
    fun apply(func: () -> String): String = func()
    assertEquals("0!", apply(::foo))

    fun foo1(i: Int): String = "$i!"
    fun apply1(func: (Int) -> String): String = func(0)
    assertEquals("0!", apply1(::foo1))

    fun foo2(i: Int = 0): String = "$i!"
    val apply2: (() -> String) -> String = { it() }
    assertEquals("0!", apply2(::foo2))

    val apply3: ((Int) -> String) -> String = { it(0) }
    assertEquals("0!", apply3 { "$it!" })

    val foo4: (Int) -> String = { "$it!" }
    val apply4: ((Int) -> String) -> String = { it(0) }
    assertEquals("0!", apply4(foo4))

    val foo5 = fun(i: Int): String = "$i!"
    val apply5: ((Int) -> String) -> String = { it(0) }
    assertEquals("0!", apply5(foo5))

  }

  @Test
  @JsName("JsTest3")
  fun `References that adapt based on the number of arguments in a function`() {
    fun foo(x: String, vararg y: Int): String = x + y.sum()
    fun use0(f: (String) -> String): String = f("0 = ")
    fun use1(f: (String, Int) -> String): String = f("1 = ", 1)
    fun use2(f: (String, Int, Int) -> String): String = f("2 = ", 1, 2)
    assertEquals("0 = 0", use0(::foo))
    assertEquals("1 = 1", use1(::foo))
    assertEquals("2 = 3", use2(::foo))
  }

  @Test
  @JsName("JsTest4")
  fun `Suspend conversion on callable references`() {
    fun call() {}
    fun takeSuspend(f: suspend () -> Unit) {}
    takeSuspend { call() } // OK before 1.4
    takeSuspend(::call) // In Kotlin 1.4, it also works
  }

  @Test
  @JsName("JsTest5")
  fun `Method References`() {
    val reference = Double::isFinite
    fun invokeLambda(number: Double, lambda: (Double) -> Boolean): Boolean {
      return lambda(number)
    }
    assertEquals(true, invokeLambda(4.1, reference))
    assertEquals(false, invokeLambda(Double.POSITIVE_INFINITY, reference))
  }

  @Test
  @JsName("JsTest6")
  fun `Object Expression`() {
    val helloWorld = object : Hello {
      val hello = "Hello"
      val world = "World"
      override fun say() = "$hello $world"
    }
    val performEvent = { callback: Hello -> callback.say() }
    assertEquals("Hello World", performEvent(helloWorld))

  }


}
