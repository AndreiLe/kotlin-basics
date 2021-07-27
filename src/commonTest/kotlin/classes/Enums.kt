package classes

import kotlin.js.JsName
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

enum class SimpleEnum {
  SILVER, GOLD, PLATINUM
}

enum class EnumConstans(val color: String) {
  SILVER("gray"),
  GOLD("yellow"),
  PLATINUM("black")
}

enum class EnumAsAnonymousClass {
  SILVER {
    override fun calculateCashbackPercent() = 0.25f
  },
  GOLD {
    override fun calculateCashbackPercent() = 0.5f
  },
  PLATINUM {
    override fun calculateCashbackPercent() = 0.75f
  };

  abstract fun calculateCashbackPercent(): Float
}

interface ICalculateCashbackPercent {
  fun calculateCashbackPercent(): Float
}

enum class EnumImplementingInterface : ICalculateCashbackPercent {
  SILVER {
    override fun calculateCashbackPercent() = 0.25f
  },
  GOLD {
    override fun calculateCashbackPercent() = 0.5f
  },
  PLATINUM {
    override fun calculateCashbackPercent() = 0.75f
  };
}

enum class EnumWithStaticMethod {
  SILVER, GOLD, PLATINUM;

  companion object {
    fun getFirstChar(name: EnumWithStaticMethod) = name.toString().first().toLowerCase()
  }
}

@Ignore
class Enums {

  @Test
  @JsName("JsTest1")
  fun `How use simple enum`() {
    assertEquals("SILVER", SimpleEnum.SILVER.toString())
    assertEquals("SILVER", SimpleEnum.valueOf("SILVER").toString())
    assertEquals("SILVER, GOLD, PLATINUM", SimpleEnum.values().joinToString())
    assertEquals(SimpleEnum.SILVER, SimpleEnum.SILVER)
    assertNotEquals(SimpleEnum.SILVER, SimpleEnum.GOLD)
  }

  @Test
  @JsName("JsTest2")
  fun `How use enum constans`() {
    assertEquals("gray", EnumConstans.SILVER.color)
  }

  @Test
  @JsName("JsTest3")
  fun `How use enum as Anonymous Class`() {
    assertEquals(0.25f, EnumAsAnonymousClass.SILVER.calculateCashbackPercent())
  }

  @Test
  @JsName("JsTest4")
  fun `How use enum implementing interface`() {
    assertEquals(0.25f, EnumImplementingInterface.SILVER.calculateCashbackPercent())
  }

  @Test
  @JsName("JsTest5")
  fun `How use enum with static methods`() {
    assertEquals('s', EnumWithStaticMethod.getFirstChar(EnumWithStaticMethod.SILVER))
  }



}
