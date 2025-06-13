package intermediate

import kotlin.properties.Delegates.observable

fun properties() {
    val person = Person()
    // Person 클래스 내부에서 set 메서드 동작
    person.name = "kodee"
    // Person 클래스 내부에서 get 메서드 동작
    println(person.name)    // Kodee

    val person2 = Person2("park","chaeros")
    println(person2.fullName)   // park chaeros

    val user = User("park","chaeros")
    println(user.displayName)   // Computed and cached: park chaeros
    println(user.displayName)   // Accessed from cache: park chaeros

    // Standard delegates - Lazy properties
    fetchData()
    // Connected to database
    // Data : [Data1, Data2, Data3]
    fetchData()
    // Data : [Data1, Data2, Data3]

    // Standard delegates - Observable properties
    val thermostat = Thermostat()
    thermostat.temperature = 23.1   // Temperature updated : 20.0°C->23.1°C

    thermostat.temperature = 29.5   // Warning : Temperature is too high! (23.1°C->29.5°C)

    // Exercise 1
    val inventory = listOf(3,0,7,0,5)
    println(findOutOfStockBooks(inventory)) // [1, 3]

    // Exercise 2
    val distanceKm = 5.0
    println("$distanceKm km is ${distanceKm.asMiles} miles")
    val marathonDistance = 42.195
    println("$marathonDistance km is ${marathonDistance.asMiles} miles")
}

// Backing fields
// Kotlin에서 속성(Property)은 자동으로 getter/setter 생성
// getter/setter 함수들에서 실제 속성 값을 저장하거나 읽을 때 field 키워드 사용
// field는 get/set 블록 안에서만 사용 가능 -> 커스텀 setter에서 속성을 직접 참조하면 무한 재귀 발생
// 로그 출력, 변경 감지 및 조건 비교, 알림 전송 등의 로직에서 사용됨
class Contact(val id: Int, var email: String) {
    val category: String = ""
}

// 위 예제는 아래와 동일한 의사코드 적용됨
// category 속성에 대한 get, set이 자동 생성된 것과 동일
// val 속성에서는 set 작성 불가
class Contact2(val id: Int, var email: String) {
    var category: String = ""
        get() = field
        set(value) {
            field = value
        }
}

// 기본 제공되는 get/set을 커스텀한 모습
class Person {
    var name: String = ""
        set(value) {
            field = value.replaceFirstChar { firstChar -> firstChar.uppercase() }
        }
}

// Extension properties
// 기존 클래스에 새로운 프로퍼티 추가 가능
data class Person2(val firstName: String, val lastName: String)

val Person2.fullName: String
    get() = "$firstName $lastName"

// Delegated properties
// 일반 위임 프로퍼티
class User(val firstName: String, val lastName: String) {
    val displayName: String by CachedStringDelegate()
}

// 캐싱을 통해 만약 비용이 크게 들거나, 사용되지 않을 수 있는 클래스인 경우 최적화
class CachedStringDelegate {
    var cachedValue: String? = null

    operator fun getValue(thisRef: User, property: Any?): String {
        if (cachedValue == null) {
            cachedValue = "${thisRef.firstName} ${thisRef.lastName}"
            println("Computed and cached: $cachedValue")
        } else {
            println("Accessed from cache: $cachedValue")
        }
        return cachedValue ?: "Unknown"
    }
}

// Standard delegates - Lazy properties
class Database {
    fun connect() {
        println("Connected to database")
    }
    fun query(sql:String): List<String> {
        return listOf("Data1","Data2","Data3")
    }
}

val databaseConnection: Database by lazy {
    // lazy로 선언된 속성으로 처음 이 속성에 접근할 때만 Database()객체가 생성되고 이후로는 단순 호출
    // 이로인해 해상 속성이 사용되지 않는 경우 객체를 생성하지않음
    // 초기화 비용이 크거나 무거운 경우 효율적으로 사용가능
    // lazy는 기본적으로 Synchronized를 제공해 thread-safe 하여 멀티 스레드 환경에서도 안전하게 초기화 가능
    // db 에 Database 객체를 생성하고, 메서드 실행 후 db 객체 반환
    val db = Database()
    db.connect()
    db
}

// Standard delegates - Observable properties
// 속성 값이 바뀔 때 이를 감지해서 특정 동작을 수행하고 싶을 때 사용
// import kotlin.properties.Delegates.observable 하여야 사용 가능
// 값이 변경될 때마다 실행할 코드를 람다식으로 작성
// old는 이전 값, new는 새로운 값, _ 는 속성 이름 안씀, 변경값만 출력
// 디버깅, UI 업데이트, 데이터 유효성 검사 등에 활용됨
class Thermostat {
    var temperature: Double by observable(20.0) { _, old, new ->
        if ( new > 25 ) {
            println("Warning : Temperature is too high! ($old°C->$new°C)")
        }
        else {
            println("Temperature updated : $old°C->$new°C")
        }
    }
}

fun fetchData() {
    val data = databaseConnection.query("SELECT * FROM user")
    println("Data : ${data}")
}

// Exercise 1
fun findOutOfStockBooks(inventory: List<Int>): List<Int> {
    var result = mutableListOf<Int>()
    for(i in inventory.indices) {
        if(inventory[i]==0){
            result.add(i)
        }
    }
    return result
}

// Exercise 2
val Double.asMiles : Double
    get() = this * 0.621371