package operations

import kotlin.js.JsName
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.test.*

@Ignore
class BitwiseOperations {
//  Available for Int and Long only!
//  shl(bits) – signed shift left [<<]
//  shr(bits) – signed shift right [>>]
//  ushr(bits) – unsigned shift right [>>>]
//  and(bits) – bitwise and [&]
//  or(bits) – bitwise or [|]
//  xor(bits) – bitwise xor [^]
//  inv() – bitwise inversion [<<]


  @Test
  @JsName("JsTest1")
  fun `Whole numbers`(){
    println("Multiplication by 2: n shl 1 == n * 2")
    assertEquals(20 shl 1,20*2)

    println("Division by 2: n shr 1 == n / 2")
    assertEquals(20 shr 1,20/2)

    println("Multiplication by the m-th power of 2: 2 shl 3 == 2.0.pow(4.0)")
    assertEquals(2 shl 3, 2.0.pow(4.0).toInt())

    println("Division by m-th power 2: 16 shr 3 == 16 / 8")
    assertEquals(16 shr 3, 16 / 8)

    println("Equality check: (a xor b) == 0; // a == b")
    assertEquals(10 xor 10, 0)

    println("Parity check: (n and 1) == 0")
    assertEquals(10 and 1, 0)
    assertEquals(13 and 1, 1)

    println("Checking for the same sign: (x xor y) >= 0")
    assertEquals((10 xor 10)>= 0, true)
    assertEquals((10 xor -10)>= 0, false)

//    Fast color change from R5G5B5 to R8G8B8
//    R8 = (R5 shl 3) or (R5 shr 2)
//    G8 = (R5 shl 3) or (R5 shr 2)
//    B8 = (R5 shl 3) or (R5 shr 2)
  }

}
