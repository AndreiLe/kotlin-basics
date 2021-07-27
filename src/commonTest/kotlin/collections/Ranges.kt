package collections

import kotlin.js.JsName
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@Ignore
class Ranges {

  @Test
  @JsName("JsTest1")
  fun `Ranges and Progressions creating`() {
    println("IntRange(0, 3) = 0..3")
    assertEquals(IntRange(0, 3).toString(), "0..3")

    println("0..3 = 0..3")
    assertEquals((0..3).toString(), "0..3")

    println("0 until 4 = 0..3")
    assertEquals((0 until 4).toString(), "0..3")

    println("IntArray(4).indices = 0..3")
    assertEquals(IntArray(4).indices.toString(), "0..3")

    println("arrayOf(0,0,0,0).indices = 0..3")
    assertEquals(arrayOf(0, 0, 0, 0).indices.toString(), "0..3")

    println("(0..3).toList() = [0, 1, 2, 3]")
    assertEquals((0..3).toList().toString(), "[0, 1, 2, 3]")

    println("(0..3 step 1).toList() = [0, 1, 2, 3]")
    assertEquals((0..3 step 1).toList().toString(), "[0, 1, 2, 3]")

    println("(3 downTo 0 step 1).toList() = [3, 2, 1, 0]")
    assertEquals((3 downTo 0 step 1).toList().toString(), "[3, 2, 1, 0]")

    println("(0..3 step 1) = IntProgression")
    val p = (0..1 step 1)
    assertTrue(
      "class IntProgression" in p::class.toString() || //js
          "class kotlin.ranges.IntProgression" in p::class.toString() || //jvm with kotlin-reflect lib
          "intProgression (Kotlin reflection is not available)" in p::class.toString() // jvm without lib
    )

    println("0..3 = IntRange")
    assertTrue(
      "class IntRange" in (0..3)::class.toString() || //js
          "class kotlin.ranges.IntRange" in (0..3)::class.toString() || //jvm with kotlin-reflect lib
          "intRange (intProgression reflection is not available)" in (0..3)::class.toString() // jvm without lib
    )
  }

  @Test
  @JsName("JsTest2")
  fun `Custom Range`() {

    class Version(val major: Int, val minor: Int) : Comparable<Version> {
      override fun compareTo(other: Version): Int {
        if (this.major != other.major) {
          return this.major - other.major
        }
        return this.minor - other.minor
      }
    }

    val versionRange = Version(1, 11)..Version(1, 30)
    println("Version(1, 11)..Version(1, 30) = versionRange")
    println("Version(0, 9) in versionRange = false")
    assertEquals(Version(0, 9) in versionRange, false)
    println("Version(1, 20) in versionRange = true")
    assertEquals(Version(1, 20) in versionRange, true)

  }

  @Test
  @JsName("JsTest3")
  fun `Create Sequences`() {
//    Sequences is executed lazily when possible

    val s = listOf(0, 1, 2, 3).asSequence()
    print("listOf(0, 1, 2, 3).asSequence() is Final sequence")
    println(s.take(5).toList().toString())
    assertEquals(s.take(5).toList().toString(), "[0, 1, 2, 3]")

    val s1 = generateSequence(0) { it + 1 } // `it` is the previous element
    print("generateSequence(0) { it + 1 } is Infinite sequence")
    println(s1.take(5).toList().toString())
    assertEquals(s1.take(5).toList().toString(), "[0, 1, 2, 3, 4]")
  }

  @Test
  @JsName("JsTest4")
  fun `Create ChunkedSequences`() {
//  Sequences is executed lazily when possible

    val words = "one two three four five six seven eight nine ten".split(' ')
    println(words.chunked(3)) // [[one, two, three], [four, five, six], [seven, eight, nine], [ten]]
    assertEquals(words.chunked(3).toString(), "[[one, two, three], [four, five, six], [seven, eight, nine], [ten]]")

    val codonTable = mapOf("ATT" to "Isoleucine", "CAA" to "Glutamine", "CGC" to "Arginine", "GGC" to "Glycine")
    val dnaFragment = "ATTCGCGGCCGCCAACGG"
    val proteins =
    dnaFragment.chunkedSequence(3) { codon: CharSequence -> codonTable[codon.toString()] ?: error("Unknown codon") }
//  sequence is evaluated lazily, so that unknown codon is not reached
    println(proteins.take(5).toList()) // [Isoleucine, Arginine, Glycine, Arginine, Glutamine]
    assertEquals(proteins.take(5).toList().toString(), "[Isoleucine, Arginine, Glycine, Arginine, Glutamine]")
  }
}
