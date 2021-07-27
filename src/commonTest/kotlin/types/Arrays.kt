package types

import kotlin.js.JsName
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

@Ignore
class Arrays {

  @Test
  @JsName("JsTest1")
  fun `Arrays creating`() {

    print("Array(4){ i -> i+1 } = ")
    println(Array(4) { i -> i + 1 }.joinToString())
    assertEquals(Array(4) { i -> i + 1 }.joinToString(), "1, 2, 3, 4")

    print("Array(4, { i -> i+1 }) = ")
    println(Array(4, { i -> i + 1 }).joinToString())
    assertEquals(Array(4, { i -> i + 1 }).joinToString(), "1, 2, 3, 4")

    print("Array<Int>(4, { it +1 }) = ")
    println(Array<Int>(4, { it + 1 }).joinToString())
    assertEquals(Array<Int>(4, { it + 1 }).joinToString(), "1, 2, 3, 4")

    print("Array<String>(4, { (it+1).toString() }) = ")
    println(Array<String>(4, { (it + 1).toString() }).joinToString())
    assertEquals(Array<String>(4, { (it + 1).toString() }).joinToString(), "1, 2, 3, 4")

    print("arrayOf(1,null,\"a\",false) = ")
    println(arrayOf(1, null, "a", false).joinToString())
    assertEquals(arrayOf(1, null, "a", false).joinToString(), "1, null, a, false")

    print("intArrayOf(1,2,3,4) = ")
    println(intArrayOf(1, 2, 3, 4).joinToString())
    assertEquals(intArrayOf(1, 2, 3, 4).joinToString(), "1, 2, 3, 4")

    print("arrayOfNulls<Int>(4) = ")
    println(arrayOfNulls<Int>(4).joinToString())
    assertEquals(arrayOfNulls<Int>(4).joinToString(), "null, null, null, null")

    print("IntArray(4) = ")
    println(IntArray(4).joinToString())
    assertEquals(IntArray(4).joinToString(), "0, 0, 0, 0")

    print("IntRange(1, 4) = ")
    println(IntRange(1, 4).joinToString())
    assertEquals(IntRange(1, 4).joinToString(), "1, 2, 3, 4")

    print("emptyArray<Int>(4) = ")
    println(emptyArray<Int>().joinToString())
    assertEquals(emptyArray<Int>().joinToString(), "")

  }

  @Test
  @JsName("JsTest2")
  fun `Arrays getters and setters`() {

    val arr = Array(4) { i -> i + 1 }
    print(" val arr =Array(4){ i -> i+1 } = ")
    println(arr.joinToString())
    assertEquals(arr.joinToString(), "1, 2, 3, 4")

    print("arr[0] = ")
    println(arr[0].toString())
    assertEquals(arr[0].toString(), "1")

    print("arr.set( arr.size - 1, 10) = ")
    arr.set(arr.size - 1, 10)
    println(arr.joinToString())
    assertEquals(arr.joinToString(), "1, 2, 3, 10")

    print("arr.get(0) = ")
    println(arr.get(0).toString())
    assertEquals(arr.get(0).toString(), "1")

    print("arr.first() = ")
    println(arr.first().toString())
    assertEquals(arr.first().toString(), "1")

    print("arr.last() = ")
    println(arr.last().toString())
    assertEquals(arr.last().toString(), "10")

    print("IntArray(4).singleOrNull() = ")
    println(IntArray(4).singleOrNull())
    assertEquals(IntArray(4).singleOrNull(), null)

    print("IntArray(1).singleOrNull() = ")
    println(IntArray(1).singleOrNull())
    assertEquals(IntArray(1).singleOrNull(), 0)
  }

  @Test
  @JsName("JsTest3")
  fun `Arrays Conversion`() {
//    Array offers toList() and toSet()
//    List offers toSet() and toTypedArray()
//    Set offers toList() and toTypedArray()

    print("IntArray(4) .toSet() = ")
    println(IntArray(4).toSet())
    assertEquals(IntArray(4).toSet().toString(), "[0]")

    print("IntArray(4) .toList() = ")
    println(IntArray(4).toList())
    assertEquals(IntArray(4).toList().toString(), "[0, 0, 0, 0]")
  }

  @Test
  @JsName("JsTest4")
  fun `Arrays sort`() {
    data class Digit(val char: Char, val num: Int)

    var arr = Array<Digit>(4) { Digit((it + 97).toChar(), 4 - it) }
    println(arr.joinToString())
    assertEquals(
      arr.joinToString(),
      "Digit(char=a, num=4), Digit(char=b, num=3), Digit(char=c, num=2), Digit(char=d, num=1)"
    )

    arr.sortWith(Comparator { a: Digit, b: Digit -> a.num - b.num })
    println(arr.joinToString())
    assertEquals(
      arr.joinToString(),
      "Digit(char=d, num=1), Digit(char=c, num=2), Digit(char=b, num=3), Digit(char=a, num=4)"
    )

    arr.sortBy { a -> a.char }
    println(arr.joinToString())
    assertEquals(
      arr.joinToString(),
      "Digit(char=a, num=4), Digit(char=b, num=3), Digit(char=c, num=2), Digit(char=d, num=1)"
    )
  }

  @Test
  @JsName("JsTest5")
  fun `Arrays and destructuring declarations`() {

    val (a, b, c, d, e) = arrayOf(1, 2, 3, 4, 5)
    println("val (a, b, c, d, e) = arrayOf(1, 2, 3, 4, 5)")
    assertEquals(e.toString(), "5")
  }

  @Test
  @JsName("JsTest6")
  fun `emptyArray()`() {
    //The array data is dumped and size is set to 0
    var array = arrayOf(1, 2, 3, 4, 5)
    array = emptyArray()
    assertEquals(0, array.size)

    //Return emptyArray
    val someInputArray: Array<String>? = null
    val result = someInputArray ?: emptyArray()
    assertEquals(0, result.size)

    assertEquals(true, arrayOf<String>().contentEquals(emptyArray()))
  }
}
