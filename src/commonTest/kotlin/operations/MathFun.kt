package operations

import kotlin.js.JsName
import kotlin.math.*
import kotlin.test.*

@Ignore
class MathFun {

  @Test
  @JsName("JsTest1")
  fun `Math function pow`(){
    println("b.pow(0.0) is 1.0")
    assertEquals(10f.pow(0f), 1f)

    println("b.pow(1.0) == b")
    assertEquals(10f.pow(1f), 10f)

    println("b.pow(NaN) is NaN")
    assertEquals(10f.pow(Float.NaN), Float.NaN)

    println("NaN.pow(x) is NaN for x != 0.0")
    assertEquals(Float.NaN.pow(1f), Float.NaN)

    println("b.pow(Inf) is NaN for abs(b) == 1.0")
    assertEquals(1f.pow(Float.POSITIVE_INFINITY), Float.NaN)

    println("b.pow(x) is NaN for b < 0 and x is finite and not an integer")
    assertEquals((-10f).pow(10.5f), Float.NaN)
  }

  @Test
  @JsName("JsTest2")
  fun `Math function sign`(){
    println("sign: -1.0 if the value is negative")
    assertEquals(sign(-10.0), -1.0)

    println("sign: zero if the value is zero")
    assertEquals(sign(0.0), 0.0)

    println("sign: 1.0 if the value is positive")
    assertEquals(sign(10.0), 1.0)

    println("sign(NaN) is NaN")
    assertEquals(sign(Double.NaN), Double.NaN)
  }

  @Test
  @JsName("JsTest3")
  fun `Math function abs`(){
    println("abs: -a == a")
    assertEquals(abs(-10.0), 10.0)

    println("abs: a == a")
    assertEquals(abs(10.0), 10.0)

    println("abs: NaN == NaN")
    assertEquals(abs(Double.NaN), Double.NaN)

    println("abs: MIN_VALUE == MIN_VALUE")
    assertEquals(abs(Int.MIN_VALUE), Int.MIN_VALUE)
  }

  @Test
  @JsName("JsTest4")
  fun `Math function ceil`(){
    println("ceil: 10.1 == 11.0")
    assertEquals(ceil(10.1), 11.0)

    println("ceil: 10.9 == 11.0")
    assertEquals(ceil(10.9), 11.0)

    println("ceil: NaN == NaN")
    assertEquals(ceil(Float.NaN), Float.NaN)
  }

  @Test
  @JsName("JsTest5")
  fun `Math function floor`(){
    println("floor: 10.1 == 10.0")
    assertEquals(floor(10.1), 10.0)

    println("floor: 10.9 == 10.0")
    assertEquals(floor(10.9), 10.0)

    println("floor: NaN == NaN")
    assertEquals(floor(Float.NaN), Float.NaN)
  }

  @Test
  @JsName("JsTest6")
  fun `Math function round`(){
    println("round: 10.5 == 10.0")
    assertEquals(round(10.5), 10.0)

    println("round: 10.6 == 11.0")
    assertEquals(round(10.6), 11.0)

    println("roundToInt: 10.4 == 10.0")
    assertEquals(10.4.roundToInt(), 10.0)

    println("roundToInt: 10.5 == 11.0")
    assertEquals(10.5.roundToInt(), 11.0)

    println("roundToLong: 10.4 == 10L")
    assertEquals(10.4.roundToLong(), 10L)

    println("roundToLong: 10.5 == 11L")
    assertEquals(10.5.roundToLong(), 11L)

    println("round: NaN == NaN")
    assertEquals(round(Float.NaN), Float.NaN)


  }


}
