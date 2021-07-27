package classes

import kotlin.js.JsName
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

class JavaUser(val name: String, val designation: String?) {

  operator fun component1() = name

  operator fun component2() = designation

  fun copy(name: String = this.name, designation: String? = this.designation) = JavaUser(name, designation)

  override fun toString() = "JavaUser(name=" + name + ", designation=" + designation + ")"

  override fun hashCode(): Int {
    val var1 = name.hashCode() * 31
    var result = var1 + (designation?.hashCode() ?: 0)
    //result = var1 + param3
    //...
    return result
  }

  override fun equals(var1: Any?): Boolean {
    if (this !== var1) return true
    if (var1 !is JavaUser) return false
    if (name != var1.name) return false
    if (designation != var1.designation) return false
    return true
  }
}

//Kotlin
data class KotlinUser(val name: String, val designation: String?)

@Ignore
class DataClass {

  @Test
  @JsName("JsTest1")
  fun `Java vs Kotlin data class`() {
    assertEquals(
      "JavaUser(name=John, designation=driver)",
      JavaUser("John", "driver").toString()
    )

    assertEquals(
      "KotlinUser(name=John, designation=driver)",
      KotlinUser("John", "driver").toString()
    )

    assertEquals(
      JavaUser("John", "driver").hashCode(),
      KotlinUser("John", "driver").hashCode()
    )

    assertEquals(
      true,
      JavaUser("John", "driver") == JavaUser("John", "driver")
    )

    assertEquals(
      true,
      KotlinUser("John", "driver") == KotlinUser("John", "driver")
    )

    val (javaName, javaDesignation) = JavaUser("John", "driver")
    val (kotlinName, kotlinDesignation) = KotlinUser("John", "driver")
    assertEquals("$javaName + $javaDesignation", "$kotlinName + $kotlinDesignation")

    val javaUser = JavaUser("John", "driver")
    assertEquals(
      true,
      javaUser == javaUser.copy()
    )

    val kotlinUser = KotlinUser("John", "driver")
    assertEquals(
      true,
      kotlinUser == kotlinUser.copy()
    )
  }

  @Test
  @JsName("JsTest2")
  fun `Properties declared in the class body`() {
    data class Person(val name: String) {
      var age: Int = 0
    }
    val data = Person("John")
    data.age = 30
    assertEquals("Person(name=John)", data.toString())
  }

  @Test
  @JsName("JsTest3")
  fun `Standard data classes`() {
    val currency = "USA" to "Dollar"
    assertEquals("(USA, Dollar)", currency.toString())

    val cat = "Cat"
    val pair = cat.to(7)
    assertEquals("(Cat, 7)", pair.toString())

    val coordinates3D = Triple(2, 3, 1)
    assertEquals("(2, 3, 1)", coordinates3D.toString())
  }

}