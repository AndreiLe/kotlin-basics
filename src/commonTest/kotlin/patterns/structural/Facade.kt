package patterns.structural

import kotlin.js.JsName
import kotlin.test.Ignore
import kotlin.test.Test


class ComplexSystemStore(private val filePath: String) {

  private val cache: HashMap<String, String>

  init {
    println("Reading data from file: $filePath")
    cache = HashMap()
    //read properties from file and put to cache
  }

  fun store(key: String, payload: String) {
    cache[key] = payload
  }

  fun read(key: String): String = cache[key] ?: ""

  fun commit() = println("Storing cached data: $cache to file: $filePath")
}

data class User(val login: String)

//Facade:
class UserRepository {

  private val systemPreferences = ComplexSystemStore("/data/default.prefs")

  fun save(user: User) {
    systemPreferences.store("USER_KEY", user.login)
    systemPreferences.commit()
  }

  fun findFirst(): User = User(systemPreferences.read("USER_KEY"))
}

@Ignore
class Facade {
    @Test
    @JsName("JsTest")
    fun `test pattern`() {
      val userRepository = UserRepository()
      val user = User("dbacinski")
      userRepository.save(user)
      val resultUser = userRepository.findFirst()
      println("Found stored user: $resultUser")
    }
}