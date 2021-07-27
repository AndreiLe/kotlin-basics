package helloWorld

import kotlin.js.JsName
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertTrue

@Ignore
class HelloWorldTest {

  @Test
  @JsName("JsTest")
  //build/reports/tests/jsBrowserTest/index.html
  fun `Hello world, always returns the true`() {
    assertTrue(true)
  }

}
