import org.assertj.core.api.Assertions
import org.junit.Test

sealed class Country {
    object Canada : Country()
}

object Spain : Country()
class Greece(val someProperty: String = EMPTY_STRING) : Country()

data class USA(val someProperty: String = EMPTY_STRING) : Country()

class Currency(val code: String = EMPTY_STRING)

object CurrencyFactory {
    fun currencyForCountry(country: Country): Currency =
        when (country) {
            is Spain -> Currency("EUR")
            is Greece -> Currency("EUR")
            is USA -> Currency("USD")
            is Country.Canada -> Currency("CAD")
        }
}

class FactoryMethodTest {
    @Test
    fun currencyTest() {
        val greekCurrency: String = CurrencyFactory.currencyForCountry(Greece("123")).code
        println("Greek currency: $greekCurrency")

        val usaCurrency: String = CurrencyFactory.currencyForCountry(USA()).code
        println("USA currency: $usaCurrency")

        val canadaCurrency: String = CurrencyFactory.currencyForCountry(Country.Canada).code
        val spainCurrency: String = CurrencyFactory.currencyForCountry(Spain).code

        Assertions.assertThat(greekCurrency).isEqualTo("EUR")
        Assertions.assertThat(usaCurrency).isEqualTo("USD")
        Assertions.assertThat(canadaCurrency).isEqualTo("CAD")
        Assertions.assertThat(spainCurrency).isEqualTo("EUR")
    }
}

private const val EMPTY_STRING = ""