package patterns.creational

import kotlin.js.JsName
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals


// Let's assume that Dialog class is provided by external library.
// We have only access to Dialog public interface which cannot be changed.

class Dialog {
  private var dialog = "\n"
  fun setTitle(text: String) {
    this.dialog += "setting title text $text\n"
  }

  fun setTitleColor(color: String) {
    this.dialog += "setting title color $color\n"
  }

  fun setMessage(text: String) {
    this.dialog += "setting message $text\n"
  }

  fun setMessageColor(color: String) {
    this.dialog += "setting message color $color"
  }

  fun show() = "showing dialog $this"
  override fun toString(): String {
    return dialog
  }
}

// Builder
class DialogBuilder() {

  constructor(init: DialogBuilder.() -> Unit) : this() {
    init()
  }

  private var titleHolder: TextView? = null
  private var messageHolder: TextView? = null
  fun title(attributes: TextView.() -> Unit) {
    titleHolder = TextView().apply { attributes() }
  }

  fun message(attributes: TextView.() -> Unit) {
    messageHolder = TextView().apply { attributes() }
  }

  fun build(): Dialog {
    val dialog = Dialog()
    titleHolder?.apply {
      dialog.setTitle(text)
      dialog.setTitleColor(color)
    }
    messageHolder?.apply {
      dialog.setMessage(text)
      dialog.setMessageColor(color)
    }
    return dialog
  }

  class TextView {
    var text: String = ""
    var color: String = "#00000"
  }
}

//Function that creates dialog builder and builds Dialog
fun dialog(init: DialogBuilder.() -> Unit): Dialog = DialogBuilder(init).build()

@Ignore
class BuilderAssembler {

  @Test
  @JsName("JsTest")
  fun `test pattern`() {
    val dialog: Dialog =
      dialog {
        title {
          text = "Dialog Title"
        }
        message {
          text = "Dialog Message"
          color = "#333333"
        }
      }

    println(dialog.show())

    assertEquals(
      """showing dialog 
setting title text Dialog Title
setting title color #00000
setting message Dialog Message
setting message color #333333""", dialog.show()
    )
  }
}