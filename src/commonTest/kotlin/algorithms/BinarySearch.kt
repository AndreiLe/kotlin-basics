package algorithms

import kotlin.js.JsName
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

@Ignore
class BinarySearch {

  @Test
  @JsName("JsTest1")
  fun `binarySearch`() {

    fun foo(searchValue: Int): Boolean {
      val sortedArray = IntArray(10) { it }
      var lowerBound = 0
      var upperBound = sortedArray.size - 1
      while (true) {
        if (upperBound < lowerBound) return false
        val midPoint = (lowerBound + upperBound) / 2
        lowerBound = if (sortedArray[midPoint] < searchValue) midPoint + 1 else lowerBound
        upperBound = if (sortedArray[midPoint] > searchValue) midPoint - 1 else upperBound
        if (sortedArray[midPoint] == searchValue) return true
      }
    }

    assertEquals(true, foo(3))
    assertEquals(true, foo(9))
    assertEquals(false, foo(10))
    assertEquals(false, foo(11))
  }

}
