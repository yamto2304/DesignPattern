package behavioural

import org.assertj.core.api.Assertions
import org.junit.Test

interface HandlerChain {
    fun addHeader(inputHeader: String): String
}

class AuthenticationHeader(private val token: String?, var next: HandlerChain? = null) : HandlerChain {
    override fun addHeader(inputHeader: String): String {
        return "$inputHeader\nAuthorization: $token"
            .let { next?.addHeader(it) ?: it }
    }
}

class ContentTypeHeader(private val contentType: String, var next: HandlerChain? = null) : HandlerChain {
    override fun addHeader(inputHeader: String): String {
        return "$inputHeader\nContentType: $contentType"
            .let { next?.addHeader(it) ?: it }
    }
}

class BodyPayloadHeader(private val body: String, var next: HandlerChain? = null) : HandlerChain {
    override fun addHeader(inputHeader: String): String {
        return "$inputHeader\n$body"
            .let { next?.addHeader(it) ?: it }
    }
}

class ChainOfResponsibilityTest {
    @Test
    fun testChainOfResponsibility() {
        val authenticationHeader = AuthenticationHeader("123456")
        val contentTypeHeader = ContentTypeHeader("json")
        val bodyPayloadHeader = BodyPayloadHeader("Body: {\"username\" = \"john\"}")

        authenticationHeader.next = contentTypeHeader
        contentTypeHeader.next = bodyPayloadHeader

        val messageWithAuthentication = authenticationHeader.addHeader("Header with authed")
        println("Auth: $messageWithAuthentication")

        println("---------------------------------------------------")
        val messageWithoutAuthentication = contentTypeHeader.addHeader("Header without authed")
        println(messageWithoutAuthentication)

        Assertions.assertThat(messageWithAuthentication).isEqualTo(
            """
                Header with authed
                Authorization: 123456
                ContentType: json
                Body: {"username" = "john"}
            """.trimIndent()
        )

        Assertions.assertThat(messageWithoutAuthentication).isEqualTo(
            """
                Header without authed
                ContentType: json
                Body: {"username" = "john"}
            """.trimIndent()
        )
    }
}