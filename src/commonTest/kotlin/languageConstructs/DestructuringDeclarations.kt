package languageConstructs

import kotlin.js.JsName
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@Ignore
class DestructuringDeclarations {

    @Test
    @JsName("JsTest")
    fun `Returning Two Values from a data class`() {
      data class Data(val name:String,val age:Int)
      val person = Data("Jon Snow", 20)
      val(name, age) = person
      assertEquals("Jon Snow", name)
      assertEquals(20, age)
    }

    @Test
    @JsName("JsTest1")
    fun `Returning Two Values from a Function`() {
      data class Data(val name:String,val age:Int)
      fun person(name:String, age:Int): Data {
        return Data(name, age)
      }
      val(name, age) = person("Jon Snow", 20)
      assertEquals("Jon Snow", name)
      assertEquals(20, age)
    }

    @Test
    @JsName("JsTest2")
    fun `Returning Two Values from a custom class`() {
      class Person(val name: String, val age: Int) {
        operator fun component1() = name
        operator fun component2() = age
      }
      val(name, age) = Person("Jon Snow", 20)
      assertEquals("Jon Snow", name)
      assertEquals(20, age)
    }

    @Test
    @JsName("JsTest3")
    fun `Underscore for unused variables`() {
      data class Data(val name:String,val age:Int)
      val(_, age) = Data("Jon Snow", 20)
      assertEquals(20, age)
    }

    @Test
    @JsName("JsTest4")
    fun `Destructuring declarations syntax for lambda parameters`() {
//      We can also use the destructuring declarations syntax for lambda parameters, as long as it is a type with the appropriate componentN functions:
//      { a -> ... } // one parameter
//      { a, b -> ... } // two parameters
//      { (a, b) -> ... } // a destructured pair
//      { (a, b), c -> ... } // a destructured pair and another parameter
    }



}