package patterns.behavioral

import kotlin.js.JsName
import kotlin.properties.Delegates
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

interface IObserver {
  fun handleEvent(oldText: String, newText: String)
}

class Observer : IObserver {
  var text = ""
  override fun handleEvent(oldText: String, newText: String) {
    text = "Text is changed: $oldText -> $newText"
  }
}

interface IObservable {
  val observers: MutableList<IObserver>
  fun addObserver(observer: IObserver)
  fun removeObserver(observer: IObserver)
  fun notifyObserver(new:String)
}

class Observable : IObservable {
  override val observers = mutableListOf<IObserver>()
  var text: String by Delegates.observable("init") { _, old, new ->
    this.observers.forEach { it.handleEvent(old, new)}
    }
  override fun addObserver(observer: IObserver) {
    this.observers.add(observer)
  }
  override fun removeObserver(observer: IObserver) {
    this.observers.remove(observer)
  }
  override fun notifyObserver(new:String) {
    this.text = new
  }
}

@Ignore
class ObserverListener {

  @Test
  @JsName("JsTest")
  fun `test pattern`() {
    val observer = Observer()
    val observable = Observable()
    with(observable){
      addObserver(observer)
      notifyObserver("start")
      notifyObserver("end")
    }
    assertEquals("Text is changed: start -> end", observer.text)
  }

}