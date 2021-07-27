package classes

import kotlin.js.JsName
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

sealed class SealedClassExample
class Unauthorized : SealedClassExample()
class Authorized : SealedClassExample()

@Ignore
class SealedClass {

  @Test
  @JsName("JsTest1")
  fun `Outer sealed class`() {
    val sealedClassExample: SealedClassExample = Authorized()
    //when don't need else with sealed class
    val result = when (sealedClassExample) {
      is Unauthorized -> "Unauthorized"
      is Authorized -> "Authorized"
    }
    assertEquals("Authorized", result)
  }

  sealed class State {
    data class Content(var data: String = "content") : State()
    abstract class Effect : State()
    object Error : State.Effect()
    object Ready : State.Effect()
  }

  @Test
  @JsName("JsTest2")
  fun `Inner sealed class`() {
    val result = when (val state:State = State.Content()) {
      is State.Content -> state.data
      is State.Error  -> "Error"
      is State.Ready  -> "Ready"
      else -> "else"
    }
    assertEquals("content", result)
  }

}
