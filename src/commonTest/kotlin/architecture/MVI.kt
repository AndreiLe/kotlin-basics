package architecture

import kotlin.js.JsName
import kotlin.properties.Delegates
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

data class MVIStateData(
  val currentValue: String = "0",
  val msgValue: String = ""
)

sealed class MVIState {
  class Content(val stateData: MVIStateData) : MVIState()
  abstract class Effect : MVIState()
  object InvalidNumberError : Effect()
  object InvalidMsgError : Effect()
}

sealed class MVIAction {
  class AddValue(val value: String?) : MVIAction()
  class SaveMsg(val value: String?) : MVIAction()
}

interface IMVIIntent {
  fun click(value: String?)
  fun save(value: String?)
}

class MVIIntent(private val emit: (MVIAction) -> Unit) : IMVIIntent {
  override fun click(value: String?) = emit(MVIAction.AddValue(value))
  override fun save(value: String?) = emit(MVIAction.SaveMsg(value))
}

class MVIViewModel {
  private val _state = ObservableData<MVIState?>(null)
  val state: ObservableData<MVIState?> = _state
  private val _effect = ObservableEvent<MVIState?>(null)
  val effect: ObservableEvent<MVIState?> = _effect

  fun takeAction(action: MVIAction) {
    when (action) {
      is MVIAction.AddValue -> handleAddValueAction(action.value)
      is MVIAction.SaveMsg -> handleSaveMsgAction(action.value)
    }
  }

  private fun handleAddValueAction(addValue: String?) {
    val newState: MVIState = when (val processValue = addValue?.toIntOrNull()) {
      null -> MVIState.InvalidNumberError
      else -> {
        val currentNumber = currentStateData.currentValue.toIntOrNull() ?: 0
        val newStateData = currentStateData.copy(currentValue = (currentNumber + processValue).toString())
        MVIState.Content(newStateData)
      }
    }
    update(newState)
  }

  private fun handleSaveMsgAction(value: String?) {
    val newState: MVIState = when (value) {
      null -> MVIState.InvalidMsgError
      else -> {
        val newStateData = currentStateData.copy(msgValue = value)
        MVIState.Content(newStateData)
      }
    }
    update(newState)
  }

  private fun update(newState: MVIState) {
    when (newState) {
      is MVIState.Effect -> _effect.notifyObserver(newState)
      is MVIState.Content -> _state.notifyObserver(newState)
    }
  }

  private val currentStateData: MVIStateData
    get() {
      val data = _state.value?.let {
        return if (it is MVIState.Content) {
          it.stateData
        } else {
          MVIStateData()
        }
      }
      return data ?: MVIStateData()
    }
}

class MVIActivityBinding(private val intent: IMVIIntent) : IMVIIntent by intent {
  lateinit var state: MVIStateData
}

class MVIActivity {
  val binding: MVIActivityBinding
  var action = "msg: "

  init {
    val viewModel = MVIViewModel()
    val intent = MVIIntent(viewModel::takeAction)
    binding = MVIActivityBinding(intent)
    observeData(binding, viewModel)
  }

  private fun observeData(binding: MVIActivityBinding, viewModel: MVIViewModel) {
    val stateObserver = Observer<MVIState?> {
      it ?: return@Observer
      when (it) {
        is MVIState.InvalidNumberError -> {
          makeMsgText("InvalidNumberError")
        }
        is MVIState.InvalidMsgError -> {
          makeMsgText("InvalidMsgError")
        }
        is MVIState.Content -> {
          binding.state = it.stateData
          makeMsgText("")
        }
        else -> {
          makeMsgText("")
        }
      }
    }
    viewModel.effect.addObserver(stateObserver)
    viewModel.state.addObserver(stateObserver)
  }

  private fun makeMsgText(text: String) {
    action = "msg: $text"
  }
}

@Ignore
class MVI {

  @Test
  @JsName("JsTest1")
  fun `test`() {
    val activity = MVIActivity()
    val binding = activity.binding

    binding.click(null)
    assertEquals("msg: InvalidNumberError", activity.action)

    binding.click("1")
    assertEquals(MVIStateData("1", ""), binding.state)
    assertEquals("msg: ", activity.action)

    binding.click("2")
    assertEquals(MVIStateData("3", ""), binding.state)
    assertEquals("msg: ", activity.action)

    binding.click(null)
    assertEquals(MVIStateData("3", ""), binding.state)
    assertEquals("msg: InvalidNumberError", activity.action)

    binding.save(null)
    assertEquals(MVIStateData("3", ""), binding.state)
    assertEquals("msg: InvalidMsgError", activity.action)

    binding.save("test")
    assertEquals(MVIStateData("3", "test"), binding.state)
    assertEquals("msg: ", activity.action)
  }

}

//Utils classes

class Observer<T>(val callBack: (T) -> Unit) {
  fun handleEvent(oldValue: T?, newValue: T) {
    if (oldValue !== newValue) this.callBack(newValue)
  }
}

open class ObservableData<T>(initialValue: T) {
  val observers = mutableListOf<Observer<T>>()

  open var value: T by Delegates.observable(initialValue) { _, old, new ->
    this.observers.forEach { it.handleEvent(old, new) }
  }

  fun addObserver(observer: Observer<T>) {
    this.observers.add(observer)
  }

  fun notifyObserver(new: T) {
    this.value = new
  }
}

class ObservableEvent<T>(initialValue: T) : ObservableData<T>(initialValue) {
  override var value: T = initialValue
    set(value) {
      this.observers.forEach { it.handleEvent(null, value) }
      field = value
    }

}