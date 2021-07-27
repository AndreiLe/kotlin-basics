package patterns.behavioral

import kotlin.js.JsName
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

class Printer(private val stringFormatterStrategy: (String) -> String) {
  fun printString(string: String): String {
    return stringFormatterStrategy(string)
  }
}
val lowerCaseFormatter = { it: String -> it.toLowerCase() }
val upperCaseFormatter = { it: String -> it.toUpperCase() }

@Ignore
class Strategy {
  @Test
  @JsName("JsTest")
  fun `test pattern`() {
    val inputString = "LOREM ipsum DOLOR sit amet"

    val lowerCasePrinter = Printer(lowerCaseFormatter)
    assertEquals("lorem ipsum dolor sit amet", lowerCasePrinter.printString(inputString))

    val upperCasePrinter = Printer(upperCaseFormatter)
    assertEquals("LOREM IPSUM DOLOR SIT AMET", upperCasePrinter.printString(inputString))

    val prefixPrinter = Printer { "Prefix: $it" }
    assertEquals("Prefix: LOREM ipsum DOLOR sit amet", prefixPrinter.printString(inputString))
  }
}