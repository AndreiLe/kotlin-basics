package functions

import kotlin.js.JsName
import kotlin.math.pow
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

@Ignore
class Functions {
  @Test
  @JsName("JsTest")
  fun `Function declaration`() {
//    Functions with block body must always specify return types explicitly
    fun double(x: Int): Int {
      return 2 * x
    }
    assertEquals(4, double(2))

    fun double2(x: Int): Int = 2 * x
    assertEquals(4, double2(2))
  }

  @Test
  @JsName("JsTest1")
  fun `Function usage`() {
    fun double(x: Int): Int = 2 * x

    val result = double(2)
    assertEquals(4, result)
  }

  @Test
  @JsName("JsTest2")
  fun `Parameters`() {
    fun powerOf(
      number: Float,
      exponent: Int, // trailing comma
    ): Float {
      return number.pow(exponent)
    }

    assertEquals(81f, powerOf(3f, 4))
  }

  @Test
  @JsName("JsTest3")
  fun `Default arguments`() {
    fun send(
      name: String,
      subject: String = "Greeting:",
      message: String = "Hello!",
    ): String {
      return "$name $subject $message"
    }
    assertEquals("John Greeting: Hello!", send("John"))

    fun callingNamedArgument(
      bar: Int = 10,
      baz: Int,
    ): Int = bar + baz
    assertEquals(20, callingNamedArgument(baz = 10))// The default value bar = 0 is used

    open class A {
      open fun foo(i: Int = 10) = i
    }

    class B : A() {
      override fun foo(i: Int) = i // No default value is allowed
    }
    assertEquals(10, A().foo())
    assertEquals(10, B().foo())

    fun LambdaAfterDefaultParams(
      bar: Int = 0,
      baz: Int = 1,
      qux: () -> String,
    ) = qux() + bar.toString() + baz.toString()
    assertEquals(
      "hello01", LambdaAfterDefaultParams(
        0,
        1,
        { "hello" },
      )
    )
    assertEquals("hello01", LambdaAfterDefaultParams(0, 1) { "hello" })
    assertEquals("hello01", LambdaAfterDefaultParams(baz = 1) { "hello" })
    assertEquals("hello01", LambdaAfterDefaultParams(qux = { "hello" }))
    assertEquals("hello01", LambdaAfterDefaultParams { "hello" })
  }

  @Test
  @JsName("JsTest4")
  fun `Named arguments`() {
//    On the JVM: You can't use the named argument syntax when calling Java functions because Java
//    bytecode does not always preserve names of function parameters.

    fun reformat(
      str: String,
      normalizeCase: Boolean = true,
      upperCaseFirstLetter: Boolean = true,
      divideByCamelHumps: Boolean = false,
      wordSeparator: Char = ' ',
    ) = str + wordSeparator + normalizeCase + wordSeparator + upperCaseFirstLetter + wordSeparator + divideByCamelHumps
    assertEquals("str true true false", reformat("str"))
    assertEquals(
      "str_false_false_true", reformat(
        "str",
        false,
        upperCaseFirstLetter = false,
        divideByCamelHumps = true,
        '_'
      )
    )
    assertEquals("str_true_false_false", reformat("str", upperCaseFirstLetter = false, wordSeparator = '_'))
  }

  @Test
  @JsName("JsTest5")
  fun `Unit-returning functions`() {
    var outerVariable = false
    fun changeOuterVariable(name: String?): Unit {
      outerVariable = name != null
// `return Unit` or `return` is optional
    }
    assertEquals(false, outerVariable)
    assertEquals(Unit, changeOuterVariable("name"))
    assertEquals(true, outerVariable)
  }

  @Test
  @JsName("JsTest6")
  fun `Variable number of arguments (Varargs)`() {
    fun <T> asList(vararg ts: T): List<T> {
      val result = ArrayList<T>()
      for (t in ts) // ts is an Array
        result.add(t)
      return result
    }
    assertEquals("1, 2, 3", asList(1, 2, 3).joinToString())
    val a = arrayOf(1, 2, 3)
    assertEquals("1, 2, 3", asList(*a).joinToString())
  }
}