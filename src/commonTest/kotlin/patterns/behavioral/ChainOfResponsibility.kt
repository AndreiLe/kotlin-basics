package patterns.behavioral

import kotlin.js.JsName
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

interface HeadersChain {
  fun addHeader(inputHeader: String): String
}

class AuthenticationHeader(val token: String?, var next: HeadersChain? = null) : HeadersChain {
  override fun addHeader(inputHeader: String): String {
    token ?: throw IllegalStateException("Token should be not null")
    return inputHeader + "Authorization: Bearer $token\n"
      .let { next?.addHeader(it) ?: it }
  }
}

class ContentTypeHeader(val contentType: String, var next: HeadersChain? = null) : HeadersChain {
  override fun addHeader(inputHeader: String): String =
    inputHeader + "ContentType: $contentType\n"
      .let { next?.addHeader(it) ?: it }
}

class BodyPayload(val body: String, var next: HeadersChain? = null) : HeadersChain {
  override fun addHeader(inputHeader: String): String =
    inputHeader + "$body"
      .let { next?.addHeader(it) ?: it }
}

@Ignore
class ChainOfResponsibility {

  @Test
  @JsName("JsTest")
  fun `test pattern`() {
    //create chain elements
    val authenticationHeader = AuthenticationHeader("123456")
    val contentTypeHeader = ContentTypeHeader("json")
    val messageBody = BodyPayload("Body:\n{\n\"username\"=\"dbacinski\"\n}")

    //construct chain
    authenticationHeader.next = contentTypeHeader
    contentTypeHeader.next = messageBody

    //execute chain authenticationHeader -> contentTypeHeader -> messageBody
    val messageWithAuthentication = authenticationHeader.addHeader("Headers with Authentication:\n")
    println(messageWithAuthentication)
    assertEquals(
      """
      Headers with Authentication:
      Authorization: Bearer 123456
      ContentType: json
      Body:
      {
      "username"="dbacinski"
      }""".trimIndent(), messageWithAuthentication
    )

    //execute chain messageWithoutAuth -> contentTypeHeader -> messageBody
    val messageWithoutAuth = contentTypeHeader.addHeader("Headers:\n")
    println(messageWithoutAuth)
    assertEquals(
      """
      Headers:
      ContentType: json
      Body:
      {
      "username"="dbacinski"
      }""".trimIndent(), messageWithoutAuth
    )
  }
}