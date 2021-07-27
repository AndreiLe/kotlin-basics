package patterns.behavioral

import kotlin.js.JsName
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

data class MementoClass(val state: String)
class Originator(var state: String) {
  fun createMemento() = MementoClass(state)
  fun restore(memento: MementoClass) { state = memento.state }
}
class CareTaker {
  private val mementoList = ArrayList<MementoClass>()
  fun saveState(state: MementoClass) = mementoList.add(state)
  fun restore(index: Int) = mementoList[index]
}

@Ignore
class Memento {

    @Test
    @JsName("JsTest1")
    fun `binarySearch`() {
      val originator = Originator("initial state")
      val careTaker = CareTaker()
      careTaker.saveState(originator.createMemento())

      originator.state = "State #1"
      originator.state = "State #2"
      careTaker.saveState(originator.createMemento())

      originator.state = "State #3"
      println("Current State: " + originator.state)
      assertEquals("State #3", originator.state)

      originator.restore(careTaker.restore(1))
      println("Second saved state: " + originator.state)
      assertEquals("State #2", originator.state)

      originator.restore(careTaker.restore(0))
      println("First saved state: " + originator.state)
      assertEquals("initial state", originator.state)
    }
}