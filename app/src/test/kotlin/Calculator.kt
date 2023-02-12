import org.assertj.core.api.Assertions
import org.junit.Test

class Calculator {
    fun sum(a: Int, b: Int) = a + b
}

class CalculatorTest {
    @Test
    fun testSum() {
        val calc = Calculator()
        Assertions.assertThat(calc.sum(3, 5)).isEqualTo(8)
        val person = MyPerson("Nguyen Van A", 23, "Quang Nam")
        val value = person.apply{
            name = "Tran Van C"
            address = "Da Nang"
        }
        println(value)
        println(person)

    }
}

class MyPerson(var name : String, var age: Int, var address: String) {
    override
    fun toString(): String {
        return "Name: ${name}, age: ${age}, address: ${address}"
    }
}

