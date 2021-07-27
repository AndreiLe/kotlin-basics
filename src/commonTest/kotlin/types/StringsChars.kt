package types

import kotlin.js.JsName
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sign
import kotlin.test.*

@Ignore
class StringsChars {

  @Test
  @JsName("JsTest1")
  fun `Characters`(){
    val c = '1'
    print("in single quotes is ")
    println(c::class.toString())
    assertTrue(
      "class BoxedChar" in c::class.toString() || //js
          "class kotlin.Char" in c::class.toString() || //jvm with kotlin-reflect lib
          "char (Kotlin reflection is not available)" in c::class.toString() // jvm without reflect lib
    )

    val s = "1"
    print("in double quotes is ")
    println(s::class.toString())
    assertEquals("String", s::class.simpleName)

    print("'\\u0031' = ")
    println('\u0031')
    assertEquals("1",'\u0031'.toString())
    assertEquals('1','\u0031')

    println("\"ABCDEF\".get(\"ABCDEF\".length-1) = 'F'")
    assertEquals("ABCDEF".get("ABCDEF".length-1), 'F')

    println("\"ABCDEF\".toCharArray().concatToString() is \"ABCDEF\"")
    assertEquals("ABCDEF".toCharArray().concatToString(), "ABCDEF")

    println("CharArray(7){ ('A' + it).toChar() }.concatToString() is \"ABCDEF\"")
    assertEquals(CharArray(6){ ('A' + it).toChar() }.concatToString(), "ABCDEF")
  }

  @Test
  @JsName("JsTest2")
  fun `Concatenation`(){

    print("new: \"a\" + \"b\" = ")
    println("a" + "b")
    assertEquals("ab", "a" + "b")
    assertEquals("ab", "a" + 'b')
    assertEquals("a1", "a" + 1)

    var s = "a"
    s += "b"
    print("replace: \"a\" += \"b\" = ")
    println(s)
    assertEquals("ab", s)
    s += 'c'
    assertEquals("abc", s)
    s += 1
    assertEquals("abc1", s)

    var s2 = "a"
    print("new: \"a\".plus(\"b\")  = ")
    println(s2.plus("b"))
    assertEquals("ab", s2.plus("b"))
    assertEquals("a", s2)
 }

  @Test
  @JsName("JsTest3")
  fun `String literals`(){
//    \t for tab
//    \b for backspace
//    \n for newline
//    \r for “carriage return” (if you are under 50 years old, ask your grandparents
//    what a “carriage” was with respect to a “typewriter”)
//    \' for a single quote
//    \" for a double quote
//    \$ for a dollar sign
//    \\ for a backslash

    println("string")
    assertEquals("string", "string")

    val s = "string"
    println(s)
    assertEquals(s, "string")

    val s1 = """
  string
    string
    string
"""
    println(s)
    assertEquals(s1, """
  string
    string
    string
""")

    val s2 = """string
                |string
                |string
""".trimMargin()
    println(s2)
    assertEquals(s2, "string\nstring\nstring")

 }

  @Test
  @JsName("JsTest4")
  fun `String templates`(){

    val i = 10
    println("i = $i")
    assertEquals("i = $i", "i = 10")

    val s = "abc"
    println("$s.length is ${s.length}")
    assertEquals("$s.length is ${s.length}", "abc.length is 3")

    val s2 = 3
    println("abc.length is ${s2}")
    assertEquals("abc.length is ${s2}", "abc.length is 3")

  }

  @Test
  @JsName("JsTest5")
  fun `String Equality`(){

    var s = "string"
    print("s.toUpperCase().toLowerCase() == s is ")
    println(s.toUpperCase().toLowerCase() == s)
    assertEquals(s.toUpperCase().toLowerCase() == s, true)

    print("s.toUpperCase().toLowerCase().equals(s) is ")
    println(s.toUpperCase().toLowerCase().equals(s))
    assertEquals(s.toUpperCase().toLowerCase().equals(s), true)

    print("s.toUpperCase().toLowerCase() === s is ")
    println(s.toUpperCase().toLowerCase() === s)
//    assertEquals(s.toUpperCase().toLowerCase() === s, false)
  }

  @Test
  @JsName("JsTest6")
  fun `String substring`(){

    val s = "Hello World!"

    println("str.substring(start, end)")
    assertEquals(s.substring(0 until s.indexOf(" ")),"Hello")

    println("str.split(delimiters)[0]")
    assertEquals(s.split(" ")[0],"Hello")

    println("s.replace(\"str\", \"\")")
    assertEquals(s.replace(" World!", ""),"Hello")

    println("Regex(pattern).find(\"str\")?.groups?.first()?.value")
    assertEquals(Regex("[A-Z,a-z]+").find(s)?.groups?.first()?.value,"Hello")

  }



}
