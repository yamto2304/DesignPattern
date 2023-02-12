import org.assertj.core.api.Assertions
import org.junit.Test

object NetworkDriver {
    init {
        println("Init : $this")
    }

    fun log(): NetworkDriver = apply { println("Network driver log: $this") }
}

class SingletonTest {
    @Test
    fun testSingleton() {
        println("Start")
        val networkDriver1 = NetworkDriver.log()
        val networkDriver2 = NetworkDriver.log()

        Assertions.assertThat(networkDriver1).isSameAs(NetworkDriver)
        Assertions.assertThat(networkDriver2).isSameAs(NetworkDriver)
    }
}