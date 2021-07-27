package types

import kotlin.js.JsName
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sign
import kotlin.test.*

@Ignore
class Booleans {

  @Test
  @JsName("JsTest1")
  fun `Boolean operators`(){
    println("1 < 2 = true")
    assertEquals(1 < 2,true)

    println("3 > 2 = true")
    assertEquals(3 > 2,true)

    println("1 != 2 = true")
    assertEquals(1 != 2,true)

    println("!(1 == 2) = true")
    assertEquals(!(1 == 2),true)

    println("1 >= 1 = true")
    assertEquals(1 >= 1,true)

    println("1 <= 1 = true")
    assertEquals(1 <= 1,true)

    println("1 == 1 = true")
    assertEquals(1 == 1,true)

  }

  @Test
  @JsName("JsTest2")
  fun `Boolean logic`(){

    println("true || false is true")
    assertEquals(true || false,true)

    println("1 < 2 || 1 > 2, is true")
    assertEquals(1 < 2 || 1 > 2,true)

    println("true && false is false")
    assertEquals(true && false,false)

    println("1 < 2 && 1 > 2, is false")
    assertEquals(1 < 2 && 1 > 2,false)

    println("1 > 2 && 1 < 2 || 1 < 2, is true")
    assertEquals(1 > 2 && 1 < 2 || 1 < 2,true)

    println("1 > 2 && (1 < 2 || 1 < 2), is false")
    assertEquals(1 > 2 && (1 < 2 || 1 < 2),false)

    val i = 1
    println("(i in 1..4) == (1 <= i && i <= 4)")
    assertEquals(i in 1..4, 1 <= i && i <= 4)

  }

}
