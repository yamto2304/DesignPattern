import org.assertj.core.api.Assertions
import org.junit.Test

interface CoffeeMachine {
    fun makeSmallCoffee()
    fun makeLargeCoffee()
}

class NormalCoffeeMachine : CoffeeMachine {
    override fun makeSmallCoffee() {
        println("Normal Coffee machine: Making small coffee")
    }

    override fun makeLargeCoffee() {
        println("Normal Coffee machine: Making large coffee")
    }
}

//Decorator
class EnhancedCoffeeMachine(private val coffeeMachine: CoffeeMachine) :
    CoffeeMachine by coffeeMachine {
    //Overriding behaviour
    override fun makeLargeCoffee() {
        println("Enhanced Coffee machine: Making large coffee")
    }

    //Extending behaviour
    fun makeMilkCoffee() {
        println("Enhanced Coffee machine: Making milk coffee")
        coffeeMachine.makeSmallCoffee()
        println("Enhanced Coffee machine: Adding milk")
    }
}

class DecoratorTest {
    @Test
    fun testDecorator() {
        val normalMachine = NormalCoffeeMachine()
        val enhancedCoffeeMachine = EnhancedCoffeeMachine(normalMachine)
        enhancedCoffeeMachine.makeSmallCoffee()
        println("------------------------------")
        enhancedCoffeeMachine.makeMilkCoffee()
        println("------------------------------")
        enhancedCoffeeMachine.makeLargeCoffee()
        println("------------------------------")
//        Assertions
    }
}