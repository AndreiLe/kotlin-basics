package functions

import kotlin.js.JsName
import kotlin.reflect.KClass
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

@Ignore
class InlineFunctions {
//  Local inline functions are not yet supported in inline functions
//  IDEA Tools → Kotlin → Show Kotlin Bytecode
//  https://javap.yawk.at/ to view byte code online. Set "Kotlin" as the input language.

  @Test
  @JsName("JsTest")
  fun `ordinary`() {
    fun ordinaryFunction(block: () -> String) = block()
    val ordinaryFunctionResult = ordinaryFunction { "ordinaryFunction" }
    assertEquals("ordinaryFunction", ordinaryFunctionResult)

    //compiled version
    class CompiledOrdinaryFunction {
      fun invoke() = "ordinaryFunction"
    }

    val compiledOrdinaryFunctionResult = CompiledOrdinaryFunction().invoke()
    assertEquals("ordinaryFunction", compiledOrdinaryFunctionResult)
  }

  private inline fun inlineFunction(block: () -> String) = block()

  @Test
  @JsName("JsTest1")
  fun `inline`() {
    val inlineFunctionResult = inlineFunction { "inlineFunction" }
    assertEquals("inlineFunction", inlineFunctionResult)

    //compiled version
    val compiledInlineFunctionResult = "inlineFunction"
    assertEquals("inlineFunction", compiledInlineFunctionResult)
  }

  private inline fun noinlineFunction(block: () -> String, noinline block2: () -> String) = block() + block2()

  @Test
  @JsName("JsTest2")
  fun `noinline`() {
    val noinlineFunctionResult = noinlineFunction({ "inlineFunction" }, { "noinlineFunction" })
    assertEquals("inlineFunctionnoinlineFunction", noinlineFunctionResult)

    //compiled version
    class CompiledNoinlineFunction {
      fun invoke() = "noinlineFunction"
    }

    val compiledNoinlineFunctionResult = "inlineFunction" + CompiledNoinlineFunction().invoke()
    assertEquals("inlineFunctionnoinlineFunction", compiledNoinlineFunctionResult)
  }

  @Test
  @JsName("JsTest3")
  fun `Non-local returns`() {
    fun ordinaryFunction(block: () -> String) = block()
    fun foo(): String {
      return@foo ordinaryFunction {
        //return@foo // ERROR: cannot make `foo` return here
        return@ordinaryFunction "ordinaryFunction"
      }
    }
    assertEquals("ordinaryFunction", foo())

    fun inlineFoo(): String {
      return@inlineFoo inlineFunction {
        return@inlineFoo "inlineFoo"
      }
    }
    assertEquals("inlineFoo", inlineFoo())
  }

  private inline fun <reified T> inlineSimpleNameOf() = T::class.simpleName

  @Test
  @JsName("JsTest4")
  fun `Reified type parameters`() {
    assertEquals("InlineFunctions", inlineSimpleNameOf<InlineFunctions>())

    //without inline and reified
    fun <T : Any> simpleNameOf(clazz: KClass<T>) = clazz.simpleName
    assertEquals("InlineFunctions", simpleNameOf(InlineFunctions::class))
  }

  @Test
  @JsName("JsTest5")
  fun `Inline properties`() {
    class InlineProperties {
      val stuff = mutableMapOf<String, String?>("something" to "This is the 'something' value")
      inline var something: String?
        get() = stuff["something"]
        set(value) {
          stuff["something"] = value
        }

      fun test() {
        assertEquals("This is the 'something' value", something)
        something = "This is different"
        assertEquals("This is different", something)
      }
    }
    InlineProperties().test()

    //compiled version
    class CompiledInlineProperties {
      val stuff = mutableMapOf<String, String?>("something" to "This is the 'something' value")
      fun getSomething(): String? = stuff["something"]
      fun setSomething(value: String): Unit {
        stuff["something"] = value
      }

      fun test() {
        assertEquals("This is the 'something' value", stuff["something"])
        stuff["something"] = "This is different"
        assertEquals("This is different", stuff["something"])
      }
    }
    CompiledInlineProperties().test()

  }

}
