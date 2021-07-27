package patterns.creational

import kotlin.js.JsName
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

object PrinterDriver {
  init {
    println("Initializing with object: $this")
  }

  fun print(): PrinterDriver =
    apply { println("Printing with object: $this") }
}

@Ignore
class Singleton {
    @Test
    @JsName("JsTest")
    fun `test pattern`() {
      println("Start")
      val printerFirst = PrinterDriver.print()
      val printerSecond = PrinterDriver.print()

      assertEquals(PrinterDriver, printerFirst)
      assertEquals(PrinterDriver, printerSecond)
    }
}