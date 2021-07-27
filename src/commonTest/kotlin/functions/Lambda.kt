package functions

import kotlin.js.JsName
import kotlin.math.pow
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

typealias ReturnLambdaType = (Int) -> (Int) -> Unit

@Ignore
class Lambda {
  @Test
  @JsName("JsTest1")
  fun `Create simple lambda and returns from lambda`() {

    val square: (Int) -> Int = {number -> number * number }
    assertEquals(9, square(3))
    assertEquals(9, square.invoke(3))

    val square1: (Int) -> Int = { it * it }
    assertEquals(9, square1(3))

    val square2 = { number: Int -> number * number }
    assertEquals(9, square2(3))

    val square3 = { number: Int ->
      val square:Int = number * number
      square
    }
    assertEquals(9, square3(3))

    val square4 = squarelambda4@{ number: Int ->
      val square = number * number
      if (square == number.toDouble().pow(2).toInt()) {
        return@squarelambda4 square
      }
      return@squarelambda4 -1
    }
    assertEquals(9, square4(3))

    val square5 = { number: Int ->
      val square = number * number
      val result = if (square == number.toDouble().pow(2).toInt()) {
        square
      } else {
        -1
      }
      result
    }
    assertEquals(9, square5(3))

    val square6 = { number: Int ->
      val square = number * number
      if (square == number.toDouble().pow(2).toInt()) {
        square
      } else {
        -1
      }
    }
    assertEquals(9, square6(3))

    val square7 = { number: Int ->
      val square = number * number
      when (square == number.toDouble().pow(2).toInt()) {
        true -> square
        else -> -1
      }
    }
    assertEquals(9, square7(3))
  }

  @Test
  @JsName("JsTest2")
  fun `Lambda class declaration`() {

    val that1: (Int) -> Int = { number: Int -> number }
    val that2 = { number: Int -> number }
    val that3: (Int) -> Int = { it }
    assertEquals(3, that1(3))
    assertEquals(3, that2(3))
    assertEquals(3, that3(3))

    val more: (String, Int) -> String = { str, int -> str + int }
    assertEquals("string1", more("string", 1))

    val noReturn: (Int) -> Unit = { num -> println(num) }
    assertEquals(Unit, noReturn(1))

    val moreExtension: String.(Int) -> String = { this + it }
    assertEquals("string1", moreExtension("string", 1))

    val returnLambda:(Int) -> ((Int) -> Unit) = {number:Int -> {number2:Int -> println(number + number2)}}
    assertEquals(Unit, returnLambda(1)(2))

    val returnLambda2:(Int) -> (Int) -> Unit = {number -> {number2 -> println(number + number2)}}
    assertEquals(Unit, returnLambda2(1)(2))

    val returnLambda3:ReturnLambdaType = {number -> {number2 -> println(number + number2)}}
    assertEquals(Unit, returnLambda2(1)(2))
  }

  @Test
  @JsName("JsTest3")
  fun `Lambda as class extension`() {

    val repeatFun: String.(Int) -> String = { this.repeat(it) }
    val repeatFun2: String.(Int) -> String = { times -> this.repeat(times) }
    val repeatFun3: String.(Int) -> String = { times: Int -> this.repeat(times) }
    val repeatFun4: (String, Int) -> String = { str, times -> str.repeat(times) }

    val twoParameters: (String, Int) -> String = repeatFun
    val twoParameters2: String.(Int) -> String = repeatFun4

    fun runTransformation1(f: (String, Int) -> String): String {
      return f("hello", 3)
    }
    fun runTransformation2(f: String.(Int) -> String): String {
      return f("hello", 3)
    }

    assertEquals("hellohellohello", repeatFun("hello", 3))
    assertEquals("hellohellohello", repeatFun2("hello", 3))
    assertEquals("hellohellohello", repeatFun3("hello", 3))
    assertEquals("hellohellohello", repeatFun4("hello", 3))

    assertEquals("hellohellohello", twoParameters("hello", 3))
    assertEquals("hellohellohello", twoParameters2("hello", 3))

    assertEquals("hellohellohello", runTransformation1(repeatFun))
    assertEquals("hellohellohello", runTransformation1(repeatFun4))
    assertEquals("hellohellohello", runTransformation1(twoParameters))
    assertEquals("hellohellohello", runTransformation1(twoParameters2))
    assertEquals("hellohellohello", runTransformation1{ str, times -> str.repeat(times) })
//    assertEquals("hellohellohello", runTransformation1{ this.repeat(it)}) //compile error, but repeatFun, twoParameters2 work

    assertEquals("hellohellohello", runTransformation2(repeatFun))
    assertEquals("hellohellohello", runTransformation2(repeatFun4))
    assertEquals("hellohellohello", runTransformation2(twoParameters))
    assertEquals("hellohellohello", runTransformation2(twoParameters2))
    assertEquals("hellohellohello", runTransformation2{ this.repeat(it)})
    assertEquals("hellohellohello", runTransformation2{ times:Int -> this.repeat(times) })
//    assertEquals("hellohellohello", runTransformation2{ str, times -> str.repeat(times) }) //compile error, but repeatFun4, twoParameters work

  }

  @Test
  @JsName("JsTest4")
  fun `it in lambda`() {

    val arr = IntArray(4) { it }
    assertEquals("0, 1, 2, 3", arr.joinToString())

    val arr2 = arr.map { it + 1 }
    assertEquals("1, 2, 3, 4", arr2.joinToString())

    val square: (Int) -> Int = { it * it }
    assertEquals(9, square(3))

  }

  @Test
  @JsName("JsTest5")
  fun `Implementing Lambdas`() {

    val invokeLambda: (Int, (Int) -> Boolean) -> Boolean = { number: Int, lambda: (Int) -> Boolean ->
      lambda(number)
    }
    val lambda1: (Int) -> Boolean = { number: Int -> number > 0 }
    val lambda2: (Int) -> Boolean = { number -> number > 0 }
    val lambda3: (Int) -> Boolean = { it > 0 }
    val lambda4 = { number: Int -> number > 0 }


    assertEquals(true, invokeLambda(2, { it > 0 }))
    assertEquals(false, invokeLambda(-2, { it > 0 }))

    assertEquals(true, invokeLambda(2) { it > 0 })
    assertEquals(false, invokeLambda(-2) { it > 0 })

    assertEquals(true, invokeLambda(2, { number: Int -> number > 0 }))
    assertEquals(false, invokeLambda(-2, { number: Int -> number > 0 }))

    assertEquals(true, invokeLambda(2, lambda1))
    assertEquals(false, invokeLambda(-2, lambda1))

    assertEquals(true, invokeLambda(2, lambda4))
    assertEquals(false, invokeLambda(-2, lambda4))
  }


}
