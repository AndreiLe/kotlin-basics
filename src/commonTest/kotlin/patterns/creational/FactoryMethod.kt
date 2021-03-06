package patterns.creational

import kotlin.js.JsName
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

sealed class Country {
  object USA : Country() //Kotlin 1.0 could only be an inner class or object
}

object Spain : Country() //Kotlin 1.1 declared as top level class/object in the same file
class Greece(val someProperty: String) : Country()
data class Canada(val someProperty: String) : Country() //Kotlin 1.1 data class extends other class
//object Poland : Country()

class Currency(
  val code: String
)

object CurrencyFactory {

  fun currencyForCountry(country: Country): Currency =
    when (country) {
      is Greece -> Currency("EUR")
      is Spain -> Currency("EUR")
      is Country.USA -> Currency("USD")
      is Canada -> Currency("CAD")
    }  //try to add a new country Poland, it won't even compile without adding new branch to 'when'
}

@Ignore
class FactoryMethod {
    @Test
    @JsName("JsTest")
    fun `test pattern`() {
      val greeceCurrency = CurrencyFactory.currencyForCountry(Greece("")).code
      println("Greece currency: $greeceCurrency")

      val usaCurrency = CurrencyFactory.currencyForCountry(Country.USA).code
      println("USA currency: $usaCurrency")

      assertEquals("EUR", greeceCurrency)
      assertEquals("USD", usaCurrency)
    }
}