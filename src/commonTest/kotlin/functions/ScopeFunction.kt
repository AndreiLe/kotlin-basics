package functions

import kotlin.js.JsName
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

@Ignore
class ScopeFunction {

  @Test
  @JsName("JsTest1")
  fun `let`() {
//    Function - let,
//    Object Reference - it,
//    Return value -  Lambda result,
//    Is extension function - Yes
    val resultWithOutLet = "str".toUpperCase()
    assertEquals("STR", resultWithOutLet)

    val resultWithLet = "str".let { it.toUpperCase() }
    assertEquals("STR", resultWithLet)

    val nullPossible: String? = null
    nullPossible?.let {
      it.toUpperCase()
      it.toLowerCase()
      it.toUpperCase()
    }
    assertEquals(null, nullPossible)

    nullPossible?.toUpperCase()
    nullPossible?.toLowerCase()
    nullPossible?.toUpperCase()
    assertEquals(null, nullPossible)
  }

  @Test
  @JsName("JsTest2")
  fun `with`() {
//    Function - with,
//    Object Reference -  this,
//    Return value -  Lambda result,
//    Is extension function - No: takes the context object as an argument.
    val numbers = mutableListOf("one", "two", "three")
    val firstAndLast = with(numbers) {
      "The first element is ${first()}," +
          " the last element is ${last()}"
    }
    assertEquals("The first element is one, the last element is three", firstAndLast)

    val numbers2 = mutableListOf("one", "two", "three")
    val firstAndLast2 = with(numbers2) {
      "'with' is called with argument $this" +
          " It contains $size elements"
    }
    assertEquals("'with' is called with argument [one, two, three] It contains 3 elements", firstAndLast2)
  }

  @Test
  @JsName("JsTest3")
  fun `run`() {
//    Function - run,
//    Object Reference - this,
//    Return value -  Lambda result,
//    Is extension function - Yes
    val numbers = mutableListOf("one", "two", "three")
    val firstAndLast = with(numbers) {
      "The first element is ${first()}," +
          " the last element is ${last()}"
    }
    assertEquals("The first element is one, the last element is three", firstAndLast)

    val numbers2 = mutableListOf("one", "two", "three")
    val firstAndLast2 = numbers2.run {
      "The first element is ${first()}," +
          " the last element is ${last()}"
    }
    assertEquals("The first element is one, the last element is three", firstAndLast2)

//    Function - run,
//    Object Reference - -,
//    Return value - Lambda result,
//    Is extension function - No: called without the context object
    val firstAndLast3 = run {
      val first = "one"
      val last = "three"
      "The first element is $first," +
          " the last element is $last"
    }
    assertEquals("The first element is one, the last element is three", firstAndLast3)
  }

  @Test
  @JsName("JsTest4")
  fun `apply`() {
//    Function - apply,
//    Object Reference - this,
//    Return value -  Context object,
//    Is extension function -  Yes
    data class Person(var name: String, var age: Int = 0, var city: String = "")

    val adam = Person("Adam").apply {
      age = 32
      city = "London"
    }
    assertEquals("Person(name=Adam, age=32, city=London)", adam.toString())

  }

  @Test
  @JsName("JsTest5")
  fun `also`() {
//    Function - also,
//    Object Reference -  it,
//    Return value -  Context object,
//    Is extension function -  Yes
    data class Person(var name: String, var age: Int = 0, var city: String = "")

    val adam = Person("Adam").also {
      it.age = 32
      it.city = "London"
    }
    assertEquals("Person(name=Adam, age=32, city=London)", adam.toString())
  }

  @Test
  @JsName("JsTest6")
  fun `takeIf and takeUnless`() {
    val number = 60
    val evenOrNull = number.takeIf { it % 2 == 0 }
    val oddOrNull = number.takeUnless { it % 2 == 0 }
    assertEquals(60, evenOrNull)
    assertEquals(null, oddOrNull)

    val zero = 0
    val result1 = zero.takeIf { it > 0 }?.let { it + 10 }
    assertEquals(null, result1)

    val ten = 10
    val result2 = ten.takeIf { it > 0 }?.let { it + 10 }
    assertEquals(20, result2)
  }

}