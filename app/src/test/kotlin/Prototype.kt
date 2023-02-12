import org.assertj.core.api.Assertions
import org.junit.Test

abstract class Shape: Cloneable {
    val id: String? = null
    var type: String? = null

    abstract fun draw()

    public override fun clone(): Any {
        var clone: Any? = null

        try {
            clone = super.clone()
        } catch (e: CloneNotSupportedException) {
            e.printStackTrace()
        }
        return clone!!
    }
}

class Rectangle: Shape() {
    override fun draw() {
        println("Inside Rectangle::draw() method")
    }

    init {
        type = "Rectangle"
    }
}

class Square: Shape() {
    override fun draw() {
        println("Inside Square::draw() method")
    }
    init {
        type = "Square"
    }
}

class Circle: Shape() {
    override fun draw() {
        println("Inside Circle::draw() method")
    }
    init {
        type = "Circle"
    }
}

object ShapeCache {
    private val shapeMap = hashMapOf<String?, Shape>()
    fun loadCache() {
        val circle = Circle()
        val square = Square()
        val rectangle = Rectangle()

        shapeMap.put("1", circle)
        shapeMap.put("2", square)
        shapeMap.put("3", rectangle)
    }

    fun getShape(shapeId: String): Shape {
        val cachedShape = shapeMap.get(shapeId)
        return cachedShape?.clone() as Shape
    }
}

class PrototypeTest {
    @Test
    fun testPrototype() {
        ShapeCache.loadCache()
        val cloneShape1 = ShapeCache.getShape("1")
        val cloneShape2 = ShapeCache.getShape("2")
        val cloneShape3 = ShapeCache.getShape("3")

        cloneShape1.draw()
        cloneShape2.draw()
        cloneShape3.draw()

        Assertions.assertThat(cloneShape1.type).isEqualTo("Circle")
        Assertions.assertThat(cloneShape2.type).isEqualTo("Square")
        Assertions.assertThat(cloneShape3.type).isEqualTo("Rectangle")
    }
}