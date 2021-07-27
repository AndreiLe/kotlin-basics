package patterns.creational

import kotlin.js.JsName
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

//Based on: http://stackoverflow.com/a/13030163/361832

interface Plant
class OrangePlant : Plant
class ApplePlant : Plant

abstract class PlantFactory {
  abstract fun makePlant(): Plant
  companion object {
    inline fun <reified T : Plant> createFactory(): PlantFactory =
      when (T::class) {
        OrangePlant::class -> OrangeFactory()
        ApplePlant::class -> AppleFactory()
        else -> throw IllegalArgumentException()
      }
  }
}

class AppleFactory : PlantFactory() {
  override fun makePlant(): Plant = ApplePlant()
}

class OrangeFactory : PlantFactory() {
  override fun makePlant(): Plant = OrangePlant()
}

@Ignore
class AbstractFactory {

    @Test
    @JsName("JsTest")
    fun `test pattern`() {
      val plantFactory = PlantFactory.createFactory<OrangePlant>()
      val plant = plantFactory.makePlant()
      println("Created plant: $plant")
      println("Created plant: ${plant::class.simpleName}")

      assertEquals("OrangePlant", plant::class.simpleName)
    }
}