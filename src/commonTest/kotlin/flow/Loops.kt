package flow

import kotlin.js.JsName
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

@Ignore
class Loops {

  @Test
  @JsName("JsTest1")
  fun `Repeat`() {
    var repeatResult = 0
    repeat(10) { index -> repeatResult = +index }
    assertEquals(9, repeatResult)
  }

  @Test
  @JsName("JsTest2")
  fun `For loop`() {
    val collection = IntArray(10) { it + 1 }

    var forResult = 0
    for (item in collection) forResult = +item
    assertEquals(10, forResult)

    var forResult2 = 0
    for (item in collection.indices) forResult2 = +item
    assertEquals(9, forResult2)

    var forResult3 = 0
    var forResult4 = 0
    for ((index, item) in collection.withIndex()) {
      forResult3 = +index;forResult4 = +item
    }
    assertEquals("9 - 10", "$forResult3 - $forResult4")

    var forResult5 = 0
    for (item in 1..10) forResult5 =+ item
    assertEquals(10, forResult5)

    var forResult6 = 0
    val count6 = 10
    for (item in 1..count6) forResult6 =+ item
    assertEquals(10, forResult6)
  }

  @Test
  @JsName("JsTest3")
  fun `While loop`() {
    var x= 10
    var whileResult = 0
    while (x > 0) {
      x--
      whileResult++
    }
    assertEquals(0, x)
    assertEquals(10, whileResult)

    var infineteWhileResult = 10
    while (true) {
      if (infineteWhileResult <= 0) break
      infineteWhileResult--
    }
    assertEquals(0, infineteWhileResult)
  }

  @Test
  @JsName("JsTest4")
  fun `Do while loop`() {
    var x= 10
    var doWhileResult = 0
    do {
      x--
      doWhileResult++
      var y = x
    } while (y > 0) // y is visible here!
    assertEquals(10, doWhileResult)
  }

  @Test
  @JsName("JsTest5")
  fun `return, break, continue in loop`() {
    fun foo(breakType:Int):String {
      var result = " -  - "
      forLoop1@ for (i1 in 1..10){
        forLoop2@ for (i2 in 1..10){
          forLoop3@ for (i3 in 1..10){
            result = "$i1 - $i2 - $i3"
            when(breakType){
              0 -> return "0 - 0 - 0"
              1 -> break@forLoop1
              2 -> break@forLoop2
              3 -> break@forLoop3
              4 -> continue@forLoop1
              5 -> continue@forLoop2
              6 -> continue@forLoop3
              7 -> continue
              8 -> break
            }
            if (breakType == 9){
              break
            }
          }
        }
      }
      return result
    }
    assertEquals("0 - 0 - 0", foo(0))
    assertEquals("1 - 1 - 1", foo(1))
    assertEquals("10 - 1 - 1", foo(2))
    assertEquals("10 - 10 - 1", foo(3))
    assertEquals("10 - 1 - 1", foo(4))
    assertEquals("10 - 10 - 1", foo(5))
    assertEquals("10 - 10 - 10", foo(6))
    assertEquals("10 - 10 - 10", foo(7))
//    Simple break works strange in when exp
//    assertEquals("10 - 10 - 1", foo(8)) //10 - 10 - 10 in js, 10 - 10 - 1 in jvm
//    Simple break works good in ifelse exp
    assertEquals("10 - 10 - 1", foo(9))
    assertEquals("10 - 10 - 10", foo(10))
  }

}
