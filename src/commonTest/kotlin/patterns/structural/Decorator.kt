package patterns.structural

import kotlin.js.JsName
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

interface CoffeeMachine {
  fun makeSmallCoffee():String
  fun makeLargeCoffee():String
}

class NormalCoffeeMachine : CoffeeMachine {
  override fun makeSmallCoffee():String = "Normal: Making small coffee"
  override fun makeLargeCoffee():String = "Normal: Making large coffee"
}

//Decorator:
class EnhancedCoffeeMachine(private val coffeeMachine: CoffeeMachine) : CoffeeMachine by coffeeMachine {
  // overriding behaviour
  override fun makeLargeCoffee():String = "Enhanced: Making large coffee"
  // extended behaviour
  fun makeCoffeeWithMilk():String {
    coffeeMachine.makeSmallCoffee()
    addMilk()
    return "Enhanced: Making coffee with milk"
  }
  private fun addMilk():String = "Enhanced: Adding milk"
}

@Ignore
class Decorator {
    @Test
    @JsName("JsTest")
    fun `test pattern`() {
      val normalMachine = NormalCoffeeMachine()
      val enhancedMachine = EnhancedCoffeeMachine(normalMachine)

      // non-overridden behaviour
      assertEquals("Normal: Making small coffee", enhancedMachine.makeSmallCoffee())
      // overridden behaviour
      assertEquals("Enhanced: Making large coffee", enhancedMachine.makeLargeCoffee())
      // extended behaviour
      assertEquals("Enhanced: Making coffee with milk", enhancedMachine.makeCoffeeWithMilk())
    }
}