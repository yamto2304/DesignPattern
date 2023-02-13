import org.assertj.core.api.Assertions
import org.junit.Test

class AlertBox {
    var message: String? = null
    fun show() {
        println("AlertBox $this: $message")
    }
}

class Window {
    val box by lazy { AlertBox() }

    fun showMessage(message: String) {
        box.message = message
        box.show()
    }
}

class Window2 {
    lateinit var box: AlertBox

    fun showMessage(message: String) {
        box = AlertBox()
        box.message = message
        box.show()
    }
}

class WindowTest {
    @Test
    fun windowTest() {
        val window = Window()
        window.showMessage("Hello World")
        Assertions.assertThat(window.box).isNotNull

        val window2 = Window2()
        //window2.box chưa được khởi tạo do chưa gọi đến show mesge
//        println(window2.box)// -> fail TC
        window2.showMessage("This is window 2")
        Assertions.assertThat(window2.box).isNotNull
    }
}