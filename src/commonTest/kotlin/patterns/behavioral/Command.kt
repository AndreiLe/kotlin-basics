package patterns.behavioral

import kotlin.js.JsName
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

interface OrderCommand {
  fun execute():String
}

class OrderAddCommand(private val id: Long) : OrderCommand {
  override fun execute() = "Adding: $id. "
}

class OrderPayCommand(private val id: Long) : OrderCommand {
  override fun execute() = "Paying: $id. "
}

class CommandProcessor {
  private val queue = ArrayList<OrderCommand>()
  var result = ""
    private set

  fun addToQueue(orderCommand: OrderCommand): CommandProcessor =
    apply {
      queue.add(orderCommand)
    }

  fun processCommands() =
    apply {
      queue.forEach { this.result += it.execute() }
      queue.clear()
    }
}

@Ignore
class Command {
    @Test
    @JsName("JsTest")
    fun `test pattern`() {
      val commandProcessor = CommandProcessor()
      assertEquals("", commandProcessor.result)

      commandProcessor
        .addToQueue(OrderAddCommand(1L))
        .addToQueue(OrderAddCommand(2L))
        .addToQueue(OrderPayCommand(2L))
        .addToQueue(OrderPayCommand(1L))
        .processCommands()

      assertEquals("Adding: 1. Adding: 2. Paying: 2. Paying: 1. ", commandProcessor.result)
    }
}