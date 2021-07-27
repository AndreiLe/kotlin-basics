package types

import kotlin.js.JsName
import kotlin.math.PI
import kotlin.math.round
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

@Ignore
class Numbers {
//  https://kotlinlang.org/docs/basic-types.html
//  https://kotlinlang.ru/docs/reference/basic-types.html
//  integral types:
//  Byte (8-bit representation) –128 до 127
//  Short (16 bits) –32768 до 32767
//  Int (32 bits) -2,147,483,648 (-2 31) до	2,147,483,647 (2 31- 1)
//  Long (64 bits) -9,223,372,036,854,775,808 (-2 63) до	9,223,372,036,854,775,807 (2 63- 1)

//  floating-point types:
//  Float (32 bits)
//  Double (64 bits)


  @Test
  @JsName("JsTest0")
  fun `Abstract class Number`() {
    val number: Number = 7f / 2f
    var result: Int = when (number) {
      is Double -> number.toInt()
      is Float -> number.toInt()
      else -> 0
    }
    assertEquals(3, result)
  }


  @Test
  @JsName("JsTest")
  fun `Numeric types`() {

    val num: Short = 1
    println(num::class.toString())
    assertEquals("Short", num::class.simpleName)

    val num2: Byte = 1
    println(num2::class.toString())
    assertEquals("Byte", num2::class.simpleName)

    println(1234::class.toString())
    assertEquals("Int", 1234::class.simpleName)

    val num3 = 1234
    assertEquals("Int", num3::class.simpleName)

    println(1234L::class.toString())
    assertEquals("Long", 1234L::class.simpleName)

    val num4: Long = 1234
    assertEquals("Long", num4::class.simpleName)

    val num5 = 2147483648
    assertEquals("Long", num5::class.simpleName)

    println(3.14159::class.toString())
    assertEquals("Double", 3.14159::class.simpleName)

    println(3.14159f::class.toString())
    assertEquals("Float", 3.14159f::class.simpleName)
  }

  @Test
  @JsName("JsTest1")
  fun `Numeric with underscore`() {
    println("1_234 or 1_2_3_4 or 1__2___3_4 = ")
    println(" ${1_234} js and jvm")
    val number = 1_234
    print(" $number only jvm")
    assertEquals(1234, 1_234)
    assertEquals(1234, 1_2_3_4)
    assertEquals(1234, 1__2___3_4)
  }

  @Test
  @JsName("JsTest2")
  fun `Numeric in bynary`() {
    print("0b10110100 = ")
    println(0b10110100)
    assertEquals(0b10110100, 180)
  }

  @Test
  @JsName("JsTest3")
  fun `Numeric in hexadecimal`() {
    print("0xFFA4C639 = ")
    println(0xFFA4C639)
    assertEquals(0xFFA4C639, 4288988729)
  }

  @Test
  @JsName("JsTest4")
  fun `Floating point numbers`() {
    println("[significand][base]")
    println("PI.toFloat() = 3.1415927f only for jmv")
    println("PI.toDouble() = 3.141592653589793 jvm and js")
//    assertEquals(3.1415927f, PI.toFloat())
    assertEquals(3.141592653589793, PI)
  }

  @Test
  @JsName("JsTest5")
  fun `String to Number`() {
    println("1".toIntOrNull())
    assertEquals("1".toIntOrNull(), 1)

    println("1.1".toDoubleOrNull())
    assertEquals("1.1".toDoubleOrNull(), 1.1)

    println(".1".toDoubleOrNull())
    assertEquals(".1".toDoubleOrNull(), 0.1)

    println("1a".toIntOrNull())
    assertEquals("1a".toIntOrNull(), null)

    println("a1".toDoubleOrNull())
    assertEquals("a1".toDoubleOrNull(), null)
  }

  @Test
  @JsName("JsTest6")
  fun `Double format`() {

    val number: Double = 0.5555555
    val number3digits = round(number * 1000.0) / 1000.0
    val number2digits = round(number3digits * 100.0) / 100.0
    val solution = round(number2digits * 10.0) / 10.0

    println(number)
    println(number3digits)
    println(number2digits)
    println(solution)

    assertEquals(number, 0.5555555)
    assertEquals(number3digits, 0.556)
    assertEquals(number2digits, 0.56)
    assertEquals(solution, 0.6)

  }

}
