package patterns.structural

import kotlin.js.JsName
import kotlin.test.Ignore
import kotlin.test.Test


interface File {
  fun read(name: String)
}

class NormalFile : File {
  override fun read(name: String) = println("Reading file: $name")
}

//Proxy:
class SecuredFile(private val normalFile: File) : File {
  var password: String = ""

  override fun read(name: String) {
    if (password == "secret") {
      println("Password is correct: $password")
      normalFile.read(name)
    } else {
      println("Incorrect password. Access denied!")
    }
  }
}

@Ignore
class ProtectionProxy {
    @Test
    @JsName("JsTest")
    fun `test pattern`() {
      val securedFile = SecuredFile(NormalFile())

      with(securedFile) {
        read("readme.md")
        password = "secret"
        read("readme.md")
      }
    }
}