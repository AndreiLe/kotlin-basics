package patterns.structural

import kotlin.js.JsName
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

open class Equipment(
  open val price: Int,
  val name: String
)

open class CompositeClass(name: String) : Equipment(0, name) {
  private val equipments = ArrayList<Equipment>()
  override val price: Int
    get() = equipments.map { it.price }.sum()

  fun add(equipment: Equipment) =
    apply { equipments.add(equipment) }
}

class PersonalComputer : CompositeClass("PC")
class Processor : Equipment(1070, "Processor")
class HardDrive : Equipment(250, "Hard Drive")
class Memory : Equipment(280, "Memory")

@Ignore
class Composite {
  @Test
  @JsName("JsTest")
  fun `test pattern`() {
    val pc = PersonalComputer()
      .add(Processor())
      .add(HardDrive())
      .add(Memory())

    println(pc.price)

    assertEquals("PC", pc.name)
    assertEquals(1600, pc.price)
  }
}