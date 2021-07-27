package patterns.behavioral

import kotlin.js.JsName
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

class ChatUser(private val mediator: ChatMediator, private val name: String) {
  var message = ""
  fun send(msg: String) {
    this.message = "$name: Sending Message= $msg"
    println(this.message)
    this.mediator.sendMessage(msg, this)
  }
  fun receive(msg: String) {
    this.message = "$name: Message received: $msg"
    println(this.message)
  }
}

class ChatMediator {
  private val users: MutableList<ChatUser> = ArrayList()
  fun sendMessage(msg: String, user: ChatUser) {
    this.users.filter { it != user }.forEach { it.receive(msg) }
  }
  fun addUser(user: ChatUser): ChatMediator = apply { this.users.add(user) }
}

@Ignore
class Mediator {

  @Test
  @JsName("JsTest")
  fun `test pattern`() {
    val mediator = ChatMediator()
    val john = ChatUser(mediator, "John")
    val alice = ChatUser(mediator, "Alice")
    val bob = ChatUser(mediator, "Bob")

    mediator.addUser(alice).addUser(bob).addUser(john)
    john.send("Hi everyone!")
    assertEquals("John: Sending Message= Hi everyone!", john.message)
    assertEquals("Alice: Message received: Hi everyone!", alice.message)
    assertEquals("Bob: Message received: Hi everyone!", bob.message)
  }
}