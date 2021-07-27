package patterns.behavioral

import kotlin.js.JsName
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

sealed class AuthorizationState
object Unauthorized : AuthorizationState()
class Authorized(val userName: String) : AuthorizationState()

class AuthorizationPresenter {
  private var state: AuthorizationState = Unauthorized
  val isAuthorized: Boolean
    get() = when (state) {
      is Authorized -> true
      is Unauthorized -> false
    }
  val userName: String
    get() {
      return when (val state = this.state) { //val enables smart casting of state
        is Authorized -> state.userName
        is Unauthorized -> "Unknown"
      }
    }

  fun loginUser(userName: String) {
    state = Authorized(userName)
  }

  fun logoutUser() {
    state = Unauthorized
  }

  override fun toString() = "User '$userName' is logged in: $isAuthorized"
}

@Ignore
class State {
    @Test
    @JsName("JsTest")
    fun `test pattern`() {
      val authorizationPresenter = AuthorizationPresenter()

      authorizationPresenter.loginUser("admin")
      println(authorizationPresenter)
      assertEquals(true, authorizationPresenter.isAuthorized)
      assertEquals("admin", authorizationPresenter.userName)

      authorizationPresenter.logoutUser()
      println(authorizationPresenter)
      assertEquals(false, authorizationPresenter.isAuthorized)
      assertEquals("Unknown", authorizationPresenter.userName)
    }
}