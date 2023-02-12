import org.assertj.core.api.Assertions
import org.junit.Test

//3nd party functionality

data class DisplayDataType(val index: Float, val data: String)

class DataDisplay {
    fun displayData(data: DisplayDataType) {
        println("Data is displayed: ${data.index} - ${data.data}")
    }

    fun doSmWithData(data: DisplayDataType) {}
}

// ---
// Our code
data class DatabaseData(val position: Int, val amount: Int)

class DatabaseDataGenerator {
    fun generateData(): List<DatabaseData> {
        val list = arrayListOf<DatabaseData>()
        list.add(DatabaseData(2, 2))
        list.add(DatabaseData(3, 3))
        list.add(DatabaseData(4, 4))
        return list
    }
}

interface DatabaseDataConverter {
    fun convertData(data: List<DatabaseData>): List<DisplayDataType>
    fun doSomethingUnit(listData: List<DatabaseData>)
}

class DataDisplayAdapter(private val display: DataDisplay) : DatabaseDataConverter {
    override fun convertData(data: List<DatabaseData>): List<DisplayDataType> {
        val returnList = arrayListOf<DisplayDataType>()
        for (datum in data) {
            val ddt = DisplayDataType(
                index = datum.position.toFloat(),
                data = datum.amount.toString()
            )
            display.displayData(ddt)
            returnList.add(ddt)
        }
        return returnList
    }

    override fun doSomethingUnit(listData: List<DatabaseData>) {
        for (datum in listData) {
            val ddt = DisplayDataType(
                index = datum.position.toFloat(),
                data = datum.amount.toString()
            )
            display.doSmWithData(ddt)
        }
    }
}

class AdapterTest {
    @Test
    fun adapterTest() {
        val generator: DatabaseDataGenerator = DatabaseDataGenerator()
        val generatedData: List<DatabaseData> = generator.generateData()
        val adapter: DataDisplayAdapter = DataDisplayAdapter(DataDisplay())
        val convertData: List<DisplayDataType> = adapter.convertData(generatedData)


        Assertions.assertThat(convertData.size).isEqualTo(3)
        Assertions.assertThat(convertData[1].index).isEqualTo(3f)
        Assertions.assertThat(convertData[1].data).isEqualTo("3")
    }
}