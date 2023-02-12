import org.assertj.core.api.Assertions
import org.junit.Test

interface DataSource

class DatabaseDataSource : DataSource

class NetworkDataSource : DataSource

abstract class DataSourceFactory {
    abstract fun makeDataSource(): DataSource

    companion object {
        inline fun <reified T : DataSource> createFactory(): DataSourceFactory =
            when (T::class) {
                DatabaseDataSource::class -> DatabaseFactory()
                NetworkDataSource::class -> NetworkFactory()
                else -> throw java.lang.IllegalArgumentException()
            }
    }
}

class NetworkFactory : DataSourceFactory() {
    override fun makeDataSource(): DataSource = NetworkDataSource()
}

class DatabaseFactory : DataSourceFactory() {
    override fun makeDataSource(): DataSource = DatabaseDataSource()
}

class AbstractFactoryTest {
    @Test
    fun afTest() {
        val datasourceFactory = DataSourceFactory.createFactory<DatabaseDataSource>()
        val dataSource = datasourceFactory.makeDataSource()
        println("Created datasource: $dataSource")

        Assertions.assertThat(dataSource).isInstanceOf(DatabaseDataSource::class.java)
    }
}
