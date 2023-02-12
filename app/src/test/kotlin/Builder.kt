import com.example.annotations.User
import org.assertj.core.api.Assertions
import org.junit.Test

class Component private constructor(builder: Builder) {
    var globalParam1: String? = null
    var globalParam2: Int? = null
    var globalParam3: Boolean? = null

    class Builder {
        private var localParam1: String? = null
        private var localParam2: Int? = null
        private var localParam3: Boolean? = null

        fun setParam1(param1: String) = apply { this.localParam1 = param1 }
        fun setParam2(param2: Int) = apply { this.localParam2 = param2 }
        fun setParam3(param3: Boolean) = apply { this.localParam3 = param3 }
        fun build() = Component(this)

        fun getParam1() = localParam1
        fun getParam2() = localParam2
        fun getParam3() = localParam3
    }

    init {
        globalParam1 = builder.getParam1()
        globalParam2 = builder.getParam2()
        globalParam3 = builder.getParam3()
    }
}

class ComponentTest {
    @Test
    fun builderTest() {
        val component = Component.Builder()
            .setParam1("Param 1")
            .setParam3(true)
            .build()
        println(component.globalParam1)
        println(component.globalParam2)
        println(component.globalParam3)

        Assertions.assertThat(component.globalParam1).isEqualTo("Param 1")
        Assertions.assertThat(component.globalParam2).isEqualTo(null)
        Assertions.assertThat(component.globalParam3).isEqualTo(true)

        val i = User("", "")
    }
}