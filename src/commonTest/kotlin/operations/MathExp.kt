package operations

import kotlin.js.JsName
import kotlin.test.*

@Ignore
class MathExp {
//  + for addition
//  - for subtraction
//  * for multiplication
//  / for division
//  % for the remainder after division (“modulo”)


  @Test
  @JsName("JsTest1")
  fun `Order of operations`(){
    println("1+2*3 = 7")
    assertEquals(7,1+2*3)

    println("(1+2)*3 = 9")
    assertEquals(9,(1+2)*3)

    println("61%2*3 = 3")
    assertEquals(3,61%2*3)

    println("61%(2*3) = 1")
    assertEquals(1,61%(2*3))

  }

  @Test
  @JsName("JsTest2")
  fun `Operations and type cast`(){
    println("(28.0 % 10.0).toInt() = 8")
    assertEquals(8,(28.0 % 10.0).toInt())

    println("\"2\" + 2 = \"22\"")
    assertEquals("22","2" + 2)

    println("2 + 2 = 4")
    assertEquals(4,2 + 2)
  }

  @Test
  @JsName("JsTest3")
  fun `Arithmetic operations on byte`(){
    println("Bytes: 126 + 1 = 127")
    println("Bytes: 126 + 2 = -128")
    assertEquals(127,(126 + 1).toByte())
    assertEquals(-128,(126 + 2).toByte())
  }

  @Test
  @JsName("JsTest4")
  fun `Arithmetic operations and assign`(){
//    Add and assign: +=
//    Subtract and assign: -=
//    Multiply and assign: *=
//    Divide and assign: /=
    var num = 0
    num =+ 1
    num =+ 1
    num =+ 1
    println("a =+ 1 == a = 1")
    assertEquals(1,num)

    num += 1
    num += 1
    num += 1
    println("0 += 1 = 1")
    assertEquals(4,num)
  }

  @Test
  @JsName("JsTest5")
  fun `Floating point numbers NaN and INFINITY`(){
    println("1/0 = 0 only js in jvm ArithmeticException")
    println("0f/0f is Float.NaN")
    println("0f/0f == 0f/0f is false")
    println("(0f/0f).isNaN() is true")
    println("1.0f / 0.0f is POSITIVE_INFINITY")
    println("-0.0f == 0.0f, -0.0 == 0.0, -0 == 0")
//    assertEquals(0,1/0)
    assertEquals(Float.NaN,0f/0f)
    assertEquals(Float.NaN,Float.NEGATIVE_INFINITY / Float.POSITIVE_INFINITY)
    assertEquals(false,0f/0f == 0f/0f)
    assertEquals(true,(0f/0f).isNaN() )
    assertEquals(Float.POSITIVE_INFINITY,1.0f / 0.0f )
    assertEquals(true,-0.0f == 0.0f && -0.0 == 0.0 && -0 == 0 )
  }

}
