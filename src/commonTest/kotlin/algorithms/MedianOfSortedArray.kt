package algorithms

import kotlin.js.JsName
import kotlin.math.floor
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

@Ignore
class MedianOfSortedArray {

  @Test
  @JsName("JsTest1")
  fun `standart way`() {
    val sortedArray = IntArray(10){it+9}
    val low = 4
    val high = 9
    val median = low + (high-low)/2
    assertEquals(15, sortedArray[median])
  }

  @Test
  @JsName("JsTest2")
  fun `siple way`() {
    val sortedArray = IntArray(10){it+9}
    val low = 4
    val high = 9
    val median = (high+low)/2
    assertEquals(15, sortedArray[median])
  }

}
