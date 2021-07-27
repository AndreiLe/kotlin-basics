package functions

import kotlin.js.JsName
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

@Ignore
class InfixNotation {

  @Test
  @JsName("JsTest")
  fun `Common Standard Library Infix Functions`() {
// until and step are infix functions
    var forLoopTemp1 = 0
    forLoop1@ for (i in 0 until 100 step 2) forLoopTemp1 += i
    assertEquals(2450, forLoopTemp1)
// equivalent to:
    var forLoopTemp2 = 0
    forLoop2@ for (i in 0.until(100).step(2)) forLoopTemp2 += i
    assertEquals(2450, forLoopTemp2)

// downTo is an infix function
    var forLoopTemp3 = 0
    forLoop3@ for (i in 100 downTo 0) forLoopTemp3 += i
    assertEquals(5050, forLoopTemp3)

// shl, and, xor are infix functions
    val i = (0x65acf9 shl 6) and 0x55 xor 0x80
    assertEquals(192, i)
  }

  infix fun Int.add(b: Int): Int = this + b
  private infix fun add(b: Int): Int = 10 + b

  @Test
  @JsName("JsTest1")
  fun `Writing Custom Infix Methods`() {
    assertEquals(30, 10.add(20))
    assertEquals(30, 10 add 20) // infix call

    val i1 = InfixNotation()
    assertEquals(30, i1.add(20))
    assertEquals(30, i1 add 20) // infix call
  }
}