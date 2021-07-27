package languageConstructs

import classes.JavaUser
import kotlin.js.JsName
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.ulp
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

@Ignore
class Equality {

  @Test
  @JsName("JsTest1")
  fun `Structural equality`() {
    class SomeClass(val name: String = "Value") {
      override fun equals(var1: Any?): Boolean {
        if (this !== var1) return true
        if (var1 !is JavaUser) return false
        if (name != var1.name) return false
        return true
      }
    }

    val someClass = SomeClass()
    val someClass2 = SomeClass()
    assertEquals(true, someClass == someClass2)
    assertEquals(true, someClass.equals(someClass2))
  }

  @Test
  @JsName("JsTest2")
  fun `Floating point numbers equality`() {
    val a = .8
    val b = .8
    assertEquals(true, a == b)
    assertEquals(true, a.equals(b))
    assertEquals(0, a.compareTo(b)) // 0 if a == b

    val a2 = .1 + .1 + .1 + .1 + .1 + .1 + .1 + .1 //0.7999999999999999
    val b2 = .8 // 0.8
    assertEquals(false, a2 == b2)
    assertEquals(false, a2.equals(b2))
    assertEquals(-1, a2.compareTo(b2))// -1 if a < b

    fun Double.equalsDelta(other: Double) = abs(this - other) < 0.000001
    assertEquals(true, a2.equalsDelta(b2))

    //the ulp (unit in the last place) of this value.
    println(max(a2.ulp, b2.ulp) * 2)

    fun Double.equalsDeltaUlp(other: Double) = abs(this - other) < (max(this.ulp, other.ulp) * 2)
    assertEquals(true, a2.equalsDeltaUlp(b2))//a- b < 2.220446049250313e-16
  }

  @Test
  @JsName("JsTest3")
  fun `String Comparison`() {
    //Whenever we initialize a new String object using quotes, it’s automatically placed in the string pool.
    // Therefore, two equal strings created that way will always reference the same object
    val a = "str"
    val b = "str"
    val c = a
    assertEquals(true, a == b)
    assertEquals(true, a === b)
    assertEquals(true, a == c)
    assertEquals(true, a === c)

//    If we use a constructor to create a new String, we explicitly tell Kotlin we want a new object.
//     Consequently, a new String will be created and put on the heap
//    jvm only
//    val a2 = String(a.toCharArray())
//    assertEquals(true, a == a2)
//    assertEquals(false, a === a2)
  }

  @Test
  @JsName("JsTest4")
  fun `Primitive types  equality`() {
    //For values represented by primitive types at runtime (for example, Int ),
    // the === equality check is equivalent to the == check.
    val a = 200
    val b = 200
    val c = a
    assertEquals(true, a == b)
    assertEquals(true, a === b)
    assertEquals(true, a == c)
    assertEquals(true, a === c)
  }

  @Test
  @JsName("JsTest5")
  fun `Referential equality`() {
    class SomeClass(val name: String = "Value") {
      override fun equals(var1: Any?): Boolean {
        if (this !== var1) return true
        if (var1 !is JavaUser) return false
        if (name != var1.name) return false
        return true
      }
    }

    val someClass = SomeClass()
    val someClass2 = SomeClass()
    val someClass3 = someClass
    assertEquals(true, someClass == someClass2)
    assertEquals(false, someClass === someClass2)
    assertEquals(true, someClass === someClass3)
  }

  @Test
  @JsName("JsTest6")
  fun `Array equality`() {
    val arr1 = Array(5) { "str$it" }
    val arr2 = Array(5) { "str$it" }
    val arr3 = arr1

    //a.equals(b) will only return true if a and b reference the same array.
    assertEquals(false, arr1 == arr2)
    assertEquals(false, arr1 === arr2)
    assertEquals(true, arr1 contentEquals arr2)
    //contentDeepEquals returns true if the two specified arrays are deeply equal to one another,
    // i.e. contain the same number of the same elements in the same order.
    //for Multi-Dimensional Arrays
    assertEquals(true, arr1 contentDeepEquals arr2)
    assertEquals(true, arr1 == arr3)
    assertEquals(true, arr1 === arr3)
  }
}