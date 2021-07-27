package flow

import classes.Authorized
import classes.Unauthorized
import kotlin.js.JsName
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

enum class E { a, b, c }

@Ignore
class IfElseWhenExp {

  @Test
  @JsName("JsTest1")
  fun `How use IfExp`() {

    println("if() else ")
    var (a, b, c) = List(3) { it }
    if (b < c) a = c else a = b
    assertEquals(a, c)

    println("var = if() else")
    var (a1, b1, c1) = List(3) { it }
    a1 = if (b1 < c1) c1 else b1
    assertEquals(a1, c1)

  }

  @Test
  @JsName("JsTest2")
  fun `ifelseif Ladder`() {

    val number = 0

    val result = if (number > 0) {
      "+"
    } else if (number < 0) {
      "-"
    } else {
      "zero"
    }

    assertEquals("zero", result)

  }

  @Test
  @JsName("JsTest3")
  fun `When and enum`() {

    val v = E.a
    val result = when (v) {
      E.a -> "1"
      E.b -> "2"
      E.c -> "3"
      else -> "0"
    }
    assertEquals("1", result)

  }

  @Test
  @JsName("JsTest4")
  fun `When and in Int`() {

    val i = 5
    val result = when (i) {
      in 1..10 -> {
        println("in")
        "in"
      }
      !in 1..10 -> {
        println("!in")
        "!in"
      }
      else -> {
        println("else")
        "else"
      }
    }
    assertEquals("in", result)

  }

  @Test
  @JsName("JsTest5")
  fun `When like switch`() {

    val i = 1

    val result = when (i) {
      1 -> "x == 1"
      2 -> "x == 2"
      else -> "error"
    }
    assertEquals("x == 1", result)

    val result2 = when (i) {
      1, 2 -> "x == 1 or x == 2"
      else -> "error"
    }
    assertEquals("x == 1 or x == 2", result2)

  }

  @Test
  @JsName("JsTest6")
  fun `When as a replacement for an if- else if chain`() {

    val i = 1
    val j = 2

    fun Int.isOdd(): Boolean = this % 2 != 0
    fun Int.isEven(): Boolean = this % 2 == 0

    val result = when {
      i.isOdd() -> "odd"
      j.isEven() -> "even"
      else -> "error"
    }
    assertEquals("odd", result)

  }

  @Test
  @JsName("JsTest7")
  fun `when don't need else with sealed class`() {
    val sealedClassExample: classes.SealedClassExample = Authorized()
    val result = when (sealedClassExample) {
      is Unauthorized -> "Unauthorized"
      is Authorized -> "Authorized"
    }
    assertEquals("Authorized", result)
  }

  @Test
  @JsName("JsTest8")
  fun `Working with ifElse Ladder result`() {
    val value = 0
    val errorResult = if (value < 0) {
      "minus"
    } else if (value == 0) {
      "zero"
    } else if (value > 0) {
      "plus"
    } else {
      "error"
    }.let { "$it equals zero" }
    assertEquals("zero", errorResult)
    assertEquals("zero equals zero", errorResult.let { "$it equals zero" })

    val correctResult = (if (value < 0) {
      "minus"
    } else if (value == 0) {
      "zero"
    } else if (value > 0) {
      "plus"
    } else {
      "error"
    }).let { "$it equals zero" }
    assertEquals("zero equals zero", correctResult)
  }

}
