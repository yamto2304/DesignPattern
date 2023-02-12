import org.assertj.core.api.Assertions
import org.junit.Test

class ScopeFunctionAssignment {
}

data class Animal(
    var name: String? = null,
    var dangerousDegree: String? = null,
    var yearOld: String? = null,
    var isPoisonous: Boolean? = null
) {
    private var cName: String? = name
    private var cIndex: String? = dangerousDegree
    private var cYearOld: String? = yearOld
    fun died() = println("$cName died")
    fun eat(eatenAnimal: String) = print("$cName eat $eatenAnimal")
}

class ScopeFunctionAssignmentTest {
    @Test
    fun performLetOperation() {

        Assertions.assertThat("1").isEqualTo("The name of the Person is: Abcd")
    }

    @Test
    fun finalTest() {
        val maxNumber = DEFAULT_LIST_ANIMAL.filter {
            it?.name == DEFAULT_LIST_INDEX.max().toString()
        }.firstOrNull()?.name
        println(maxNumber)
        Assertions.assertThat(maxNumber).isEqualTo("6")

    }
}

private val DEFAULT_LIST_INDEX = listOf(0, 1, 2, 3, 4, 5)
private val DEFAULT_LIST_ANIMAL = mutableListOf(
    Animal("Lion", "0", "15", true),//15
    Animal("Tiger", "1", "4", null),//5
    Animal("Elephant", "2", "2", false),//4
    Animal("Shark", "3", "3", true),//6
    Animal("Jaguar", "4", "7", false),//11
    Animal("Hyenas", "5", "23", true),//28
    Animal("Whale", "6", "26", false),//32
    Animal("Carp", "7", "6", false),//13
    Animal("Ape", "8", "12", false),//20
    Animal("Dinosaur", "9", "5", true),//14
    null
)
//Assignment:
// Giới thiệu toán tử ?:
// 4 số ngày sinh - tháng sinh ddmm -> lấy ra 4 (hoặc ít hơn) dangerousDegree -> list Animal A //2304  A-> 4 6 11 15
// List còn lại = list Animal B // 5 13 14 20 28 32
// List A sẽ "ăn thịt" list B theo lượt: Animal có tổng dangerousDegree + yearOld lớn nhất list A sẽ "thịt" 2 Animal có tổng nhỏ nhất bên B
// Tiếp tục đến Animal có tổng lớn thứ 2,3,4 bên A ăn Animal có tổng nhỏ thứ 2,3,4 bên B
// Quy tắc ăn: Tên Animal mới = "Tên Animal ăn" + "tên Animal bị ăn" + "tên Animal bị ăn", dangerousDegree = "dangerousDegree Animal ăn + Animal bị ăn", yearOld = "yearOld Animal ăn"
// Toàn bộ data của Animal bị ăn = null // dùng apply - also
// Ăn phải animal có độc -> died -> data = null

// Thực hiện quá trình "ăn" và lấy ra tên của Animal nguy hiểm nhất còn sống trong list