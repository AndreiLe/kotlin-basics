package collections

import kotlin.collections.List
import kotlin.js.JsName
import kotlin.test.*

@Ignore
class List {

  @Test
  @JsName("JsTest1")
  fun `List creating`(){
    println("listOf('a', 0, 1, false) = [a, 0, 1, false]")
    assertEquals(listOf('a', 0, 1, false).toString(), "[a, 0, 1, false]")

    println("List(4){it} = [0, 1, 2, 3]")
    assertEquals(List(4){it}.toString(), "[0, 1, 2, 3]")

    println("(0..3).toList() = [0, 1, 2, 3]")
    assertEquals((0..3).toList().toString(), "[0, 1, 2, 3]")

    println("generateSequence(0) { it + 1 }.take(4).toList() = [0, 1, 2, 3]")
    assertEquals(generateSequence(0) { it + 1 }.take(4).toList().toString(), "[0, 1, 2, 3]")

    println("\"0 1 2 3\".split(\" \") = [0, 1, 2, 3]")
    assertEquals("0 1 2 3".split(" ").toString(), "[0, 1, 2, 3]")

    println("\"0123\".toList() = [0, 1, 2, 3]")
    assertEquals("0123".toList().toString(), "[0, 1, 2, 3]")
  }

  @Test
  @JsName("JsTest2")
  fun `Typed list creating `(){
    println("listOf<Int>(0, 1, 2, 3) = [0, 1, 2, 3]")
    assertEquals(listOf<Int>(0, 1, 2, 3).toString(), "[0, 1, 2, 3]")

    println("List<Int>(4){it} = [0, 1, 2, 3]")
    assertEquals(List<Int>(4){it}.toString(), "[0, 1, 2, 3]")

    val l: List<Int> = (0..3).toList()
    println("val l:List<Int> = (0..3).toList() is [0, 1, 2, 3]")
    assertEquals(l.toString(), "[0, 1, 2, 3]")
  }

  @Test
  @JsName("JsTest3")
  fun `List and destructuring declarations`(){
    val (a, b, c, d, e) = List(5){it}
    println("val (a, b, c, d, e) = List(5){it}")
    assertEquals(e.toString(), "4")
  }

  @Test
  @JsName("JsTest4")
  fun `List to MutableList`(){
//    List and MutableList is java.util.List in runtime
    val readonly = listOf(1, 2, 3)

//    Dont use List is MutableList
//    Works in js but error in jvm
//    if (readonly is MutableList)
//      readonly.add(4)

    var mutable = readonly.toMutableList()
    mutable.add(4)
    assertEquals("[1, 2, 3]", readonly.toString())
    assertEquals("[1, 2, 3, 4]", mutable.toString())
  }

  @Test
  @JsName("JsTest5")
  fun `emptyList()`() {
    //Default params
    data class Person(val friends: List<Int> = emptyList())

    val immutableList = listOf<String>()
    assertEquals(0, immutableList.size)
    assertEquals(true, immutableList.isEmpty())

    val emptyList = emptyList<String>()
    assertEquals(0, emptyList.size)
    assertEquals(true, emptyList.isEmpty())

    assertEquals(true, immutableList == emptyList)
  }


}
