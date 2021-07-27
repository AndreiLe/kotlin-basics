package patterns.structural

import kotlin.js.JsName
import kotlin.math.round
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

interface Startable {
  fun start():String
}
class Starter: Startable {
  override fun start():String = "started"
}
class GasolineCar: Startable {
  val starter:Starter = Starter()
  override fun start(): String = starter.start() //Delegation
}
class GasolineCarKotlin( private val starter: Starter ) : Startable by starter //Delegation

@Ignore
class Delegation {

  @Test
  @JsName("JsTest")
  fun `test pattern`() {
    assertEquals("started", Starter().start())
    assertEquals("started", GasolineCar().start())
    assertEquals("started", GasolineCarKotlin(Starter()).start())
  }
}
