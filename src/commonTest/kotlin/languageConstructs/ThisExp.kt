package languageConstructs

import kotlin.js.JsName
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@Ignore
class ThisExp {

    @Test
    @JsName("JsTest")
    fun `In a member of a class`() {
      class Person(name: String, age:Int){
        val name = name
        var age = age.toString()
          get() =  "${this.name} age is ${field}"
          private set
        var address = ""
          get() = field
          set(value) {
            field = value
          }
        fun info():String{
          return this.name
        }
      }
      val person = Person("Jon Snow", 20)
      person.address = "Estonia"
      assertEquals("Jon Snow", person.info())
      assertEquals("Jon Snow", person.name)
      assertEquals("Estonia", person.address)
    }

    @Test
    @JsName("JsTest2")
    fun `In an extension function or a function literal with receiver`() {
      val extension: String.(Int) -> String = { this + it }
      assertEquals("string1", extension("string", 1))
    }

    @Test
    @JsName("JsTest3")
    fun `Qualified this`() {
      class OuterClass {
        inner class InnerClass{
          fun Int.intExtensionFunction(){
            val outerThis = this@OuterClass
            val innerThis = this@InnerClass
            val intExtThis = this@intExtensionFunction
            val currentThis = this

            // int receiver, which will be an int
            val funLiteral = lambda@ fun String.() {
              val funLiteralThis = this
              // funLiteral's receiver which will be a string
              assertEquals("String",funLiteralThis::class.simpleName )
            }

            val funLiteral2 = { string: String ->
              val funLiteral2This = this
              //since this lambada doesn't have any receiver,
              //this will be an int (the extension fun reference)
              assertEquals("Int",funLiteral2This::class.simpleName )
            }

            assertEquals("OuterClass",outerThis::class.simpleName )
            assertEquals("InnerClass",innerThis::class.simpleName )
            assertEquals("Int",intExtThis::class.simpleName )
            assertEquals("Int",currentThis::class.simpleName )

            funLiteral("test")
            funLiteral2("test2")
          }
          val init = 3.intExtensionFunction()
        }
      }
      OuterClass().InnerClass()
    }

    @Test
    @JsName("JsTest4")
    fun `Implicit this`() {
      fun printLine():String { return "Top-level function" }
      class A {
        fun printLine():String { return "Member function" }
        fun invokePrintLine(omitThis: Boolean = false):String {
           return if (omitThis) printLine() else this.printLine()
        }
      }
      assertEquals("Member function", A().invokePrintLine())
      assertEquals("Top-level function", A().invokePrintLine(omitThis = true))
    }

}