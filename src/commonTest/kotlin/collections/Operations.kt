package collections

import kotlin.collections.List
import kotlin.js.JsName
import kotlin.test.*

@Ignore
class Operations {

  @Test
  @JsName("JsTest1")
  fun `Eager lists and lazy sequences operations`(){
    var textL = ""
    val l = listOf(1, 2, 3).filter { print("$it "); textL += it; it >= 2 }
    print(" before list sum ")
    textL += " before list sum ";
    println(l.sum())
    assertEquals("123 before list sum ", textL)

    var textS = ""
    val s = sequenceOf(1, 2, 3).filter { print("$it "); textS += it; it >= 2 }
    print(" after sequence sum ")
    textS += " after sequence sum ";
    println(s.sum())
    assertEquals(" after sequence sum 123", textS)
  }

}
