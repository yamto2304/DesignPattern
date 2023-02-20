import org.junit.Test

interface Image {
    fun display()
}

class RealImage(private val fileName: String): Image {
    override fun display() {
        println("RealImage: Displaying: $fileName")
    }

    private fun loadFromDisk(fileName: String) {
        println("RealImage: Loading: $fileName")
    }

    init {
        loadFromDisk(fileName)
    }
}

class ProxyImage(private val fileName: String): Image {
    private var realImage: RealImage? = null
    override fun display() {
        println("ProxyImage: Displaying: $fileName")
        if (realImage == null) {
            realImage = RealImage(fileName)
        }
        realImage!!.display()
    }
}

class ProxyTest {
    @Test
    fun testProxy() {
        val image = ProxyImage("test.jpg")
        image.display()
        println("---------------------------------")
        image.display()
    }
}
