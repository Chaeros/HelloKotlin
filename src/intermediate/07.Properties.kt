package intermediate

fun properties() {
    val person = Person()
    // Person 클래스 내부에서 set 메서드 동작
    person.name = "kodee"
    // Person 클래스 내부에서 get 메서드 동작
    println(person.name)    // Kodee

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