import org.assertj.core.api.Assertions
import org.junit.Test

//Refer this for handling database
class ComplexSystemStore(private val filePath: String) {
    private val cache: HashMap<String, String>

    init {
        println("Reading data from file : $filePath")
        cache = HashMap()
        //Read properties from file and put to cache
    }

    fun store(key: String, value: String) {
        cache[key] = value
    }

    fun read(key: String) = cache[key] ?: ""

    fun commit() = println("Storing cached data to file $filePath")
}

data class User(val login: String)

//Facade
class UserRepository {
    private val systemPreferences = ComplexSystemStore("/data/test.prefs")
    fun save(user: User) {
        systemPreferences.store("USER_KEY", user.login)
        systemPreferences.commit()
    }

    fun findFirst(): User = User(systemPreferences.read("USER_KEY"))
}

class FacadeTest {
    @Test
    fun testFacade() {
        val userRepo = UserRepository()
        val user = User("Mel")
        userRepo.save(user)
        val retrieverUser = userRepo.findFirst()
        Assertions.assertThat(retrieverUser.login).isEqualTo("Mel")
    }
}