package types

import kotlin.js.JsName
import kotlin.test.*

@Ignore
class `null` {

  @Test
  @JsName("JsTest1")
  fun `How catch null`(){

    println("if s = null: ")
    var s: String? = null
    println(s?.isEmpty().toString())
    assertEquals(s?.isEmpty(), null)

    println(s?.isNullOrEmpty().toString())
    assertEquals(s?.isNullOrEmpty(), null)

    println(s?.isEmpty() ?: true)
    assertEquals(s?.isEmpty() ?: true, true)

    print("The best way = if(s?.isEmpty() ?: true) true else false = ")
    println(if(s?.isEmpty() ?: true) true else false)
    assertEquals(if(s?.isEmpty() ?: true) true else false, true)

    println(s.isNullOrEmpty().toString())
    assertEquals(s.isNullOrEmpty(), true)

    println(if(s.isNullOrEmpty()) true else false)
    assertEquals(if(s.isNullOrEmpty()) true else false, true)

    println("if s = \"\": ")
    s = ""
    println(s?.isEmpty().toString())
    assertEquals(s?.isEmpty(), true)

    println(s?.isEmpty() ?: true)
    assertEquals(s?.isEmpty() ?: true, true)

    println(if(s?.isEmpty() ?: true) true else false)
    assertEquals(if(s?.isEmpty() ?: true) true else false, true)

    println(s.isNullOrEmpty().toString())
    assertEquals(s.isNullOrEmpty(), true)

    println(if(s.isNullOrEmpty()) true else false)
    assertEquals(if(s.isNullOrEmpty()) true else false, true)

    println("if s = \"1\": ")
    s = "1"
    println(s?.isEmpty().toString())
    assertEquals(s?.isEmpty(), false)

    println(s?.isEmpty() ?: true)
    assertEquals(s?.isEmpty() ?: true, false)

    println(if(s?.isEmpty() ?: true) true else false)
    assertEquals(if(s?.isEmpty() ?: true) true else false, false)

    println(s.isNullOrEmpty().toString())
    assertEquals(s.isNullOrEmpty(), false)

    println(if(s.isNullOrEmpty()) true else false)
    assertEquals(if(s.isNullOrEmpty()) true else false, false)

  }

}
