package languageConstructs

import kotlin.js.JsName
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

@Ignore
class TypeCheckSmartCasting {

  @Test
  @JsName("JsTest1")
  fun `Type Checking`() {
    val obj1: Any? = "A"
    val check1 = if (obj1 is String) "string" else null
    assertEquals("string", check1)

    val obj2: Any = 'A'
    val check2 = if (obj2 !is String) "char" else null
    assertEquals("char", check2)

    val obj3: Any = 'A'
    val check3 = if (!(obj3 is String)) "char" else null
    assertEquals("char", check3)
  }

  @Test
  @JsName("JsTest2")
  fun `Smart Casting in loop with when`() {
    //smart casts work for if and when-expressions and while-loops
    val anyList: List<Any> = listOf("A", 1, IntArray(3){it})
    var result = ""
    for (value in anyList) {
      val type = when (value) {
        is String -> value.length
        is Int -> value + 1
        is IntArray -> value.sum()
        else -> "Unknown"
      }
      result += type
    }
    assertEquals("123", result)
  }

  @Test
  @JsName("JsTest3")
  fun `Type Checking multiplatform problems`() {
    val obj: Any = 0.1
    val check = if (obj is Int) obj else null
//    assertEquals(null, check) //jvm
//    assertEquals(0.1, check) //js

    val obj2: Any = 0.1f
    val check2 = if (obj2 is Float) obj2 else null
    assertEquals(0.1f, check2)
  }

  @Test
  @JsName("JsTest4")
  fun `Smart Casts in Classes`() {
    open class Vehicle {
      open fun printDetails():String = "vehicle"
    }

    class Car : Vehicle() {
      override fun printDetails() = "car"
    }

    class Bike : Vehicle() {
      override fun printDetails() = "bike"
    }

    val obj1: Any = Car()
    val vehicleCheck1 = if (obj1 is Vehicle) obj1.printDetails() else null
    assertEquals("car", vehicleCheck1)
    val carCheck1 = if (obj1 is Car) obj1.printDetails() else null
    assertEquals("car", carCheck1)
    val bikeCheck1 = if (obj1 is Bike) obj1.printDetails() else null
    assertEquals(null, bikeCheck1)

    val obj2: Any = Bike()
    val vehicleCheck2 = if (obj2 is Vehicle) obj2.printDetails() else null
    assertEquals("bike", vehicleCheck2)
    val carCheck2 = if (obj2 is Car) obj2.printDetails() else null
    assertEquals(null, carCheck2)
    val bikeCheck2 = if (obj2 is Bike) obj2.printDetails() else null
    assertEquals("bike", bikeCheck2)
  }

  @Test
  @JsName("JsTest5")
  fun `"Unsafe" cast operator`() {
    val obj: Any = "A"
    val str: String = obj as String
    assertEquals(1, str.length)

    val obj2: Any? = null
    assertFails { obj2 as String } //ClassCastException
  }

  @Test
  @JsName("JsTest6")
  fun `"Safe" (nullable) cast operator (CastOrNull)`() {
    val obj: Any? = null
    val str: String? = obj as? String
    assertEquals(null, str?.length)
  }

  inline fun <reified T> genericCastOrNull(anything: Any):T? {
    return anything as? T
  }

  @Test
  @JsName("JsTest9")
  fun `"GenericCastOrNull`() {
    val obj: Any = 1
    assertEquals(null, genericCastOrNull<String>(obj))
  }

  @Test
  @JsName("JsTest7")
  fun `Type erasure and generic type checks`() {
  //instances of generic types hold no information about their actual type arguments.
  // For example, List<Foo> is erased to just List<*>
    val anyList: List<String> = arrayListOf("A", "B", "C")
    fun handleStrings(list: List<*>):Any? {
      // The items are typed as `Any?`
      return if (list is ArrayList) list.first() else null
    }
    assertEquals("A", handleStrings(anyList))

    //can make an is-check or a cast that involves the non-generic part of the type.
    // Note that angle brackets are omitted in this case
    //jvm only
    fun handleStrings2(list: List<String>):String {
      // `list` is smart-cast to `ArrayList<String>`
      return if (list is ArrayList) list.first() else "null"
    }
    assertEquals("A", handleStrings2(anyList))
  }

  @Test
  @JsName("JsTest8")
  fun `Unchecked casts`() {
    val arr: List<*> = arrayListOf("A", "B")
    fun doSomething(arr: List<*>): List<String> {
      //You can remove warnings if you are sure of the type of the argument.
      @Suppress("UNCHECKED_CAST")
      return arr as List<String>
    }
    assertEquals(1, doSomething(arr).first().length)
  }


}