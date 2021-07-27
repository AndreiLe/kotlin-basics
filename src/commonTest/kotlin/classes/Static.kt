package classes

import kotlin.js.JsName
import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

//The preferred way
fun staticMethod() = "static"

//The preferred way
const val staticField = "static"

@Ignore
class Static {
  class StaticClass {
    companion object {
      //Call from java - Static.Companion.staticVal
      const val staticVal = "static"

      //Call from java - Static.Companion.staticFun()
      fun staticFun() = "static"

      //Call from java - Static.staticFun()
      @JvmStatic
      fun staticFun2() = "static"

      //Call from java - Static.staticField, not open, override or const
      @JvmField
      var staticField = "static"

      //Call from java - Static.staticField2(), not const
      @JvmStatic
      val staticField2 = "static"
    }
  }

  companion object StaticNamed {
    fun staticFun() = "static"
    const val staticVal = "static"
  }

  @Test
  @JsName("JsTest1")
  fun `Define static functions`() {
    assertEquals("static", staticMethod())
    assertEquals("static", staticField)

    assertEquals("static", StaticClass.staticFun())
    assertEquals("static", StaticClass.Companion.staticFun())
    assertEquals("static", StaticClass.staticVal)

    assertEquals("static", staticFun())
    assertEquals("static", staticVal)
    assertEquals("static", StaticNamed.staticFun())
    assertEquals("static", StaticNamed.staticVal)
    assertEquals("static", Static.staticFun())
    assertEquals("static", Static.staticVal)
    assertEquals("static", Static.StaticNamed.staticFun())
    assertEquals("static", Static.StaticNamed.staticVal)
  }
}