package functions

import kotlin.js.JsName
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

@Ignore
class Closures {

    @Test
    @JsName("JsTest")
    fun `Create closure`() {
      fun getCounter(): ()->Int {
        var counter = 0
        fun closure():Int {
          return counter++
        }
        return ::closure
      }
      val counter = getCounter()
      assertEquals(0, counter())
      assertEquals(1, counter())
      assertEquals(2, counter())

      fun getCounter2(): ()->Int {
        var counter = 0
        return { counter++ }
      }
      val counter2 = getCounter2()
      assertEquals(0, counter2())
      assertEquals(1, counter2())
      assertEquals(2, counter2())

      class GetCounter3{
        var counter = 0
        fun get():Int {
          return this.counter++
        }
      }
      val counter3 = GetCounter3()::get
      assertEquals(0, counter3())
      assertEquals(1, counter3())
      val counter4 = GetCounter3()::get
      assertEquals(0, counter4())
      assertEquals(1, counter4())
      val counter5 = GetCounter3()
      assertEquals(0, counter5.get())
      assertEquals(1, counter5.get())

    }

    @Test
    @JsName("JsTest1")
    fun `Closure in loop`() {
      val withoutScopeFunction = arrayOfNulls<(()->Int)?>(5)
      val withScopeFunction = arrayOfNulls<(()->Int)?>(5)
      var counter = 0
      repeat(5) {
        withoutScopeFunction[it] = fun() = counter
        val value = counter //save the value of the counter in the closure
        withScopeFunction[it] = fun() = value
        counter++
      }
      var withoutScopeSum = 0
      withoutScopeFunction.forEach { withoutScopeSum += it?.invoke()?:0; println(it?.invoke()?:0) }//5,5,5,5,5
      var withScopeScopeSum = 0
      withScopeFunction.forEach { withScopeScopeSum += it?.invoke()?:0; println(it?.invoke()?:0) }//0,1,2,3,4

      assertEquals(25, withoutScopeSum)
      assertEquals(10, withScopeScopeSum)

    }
}