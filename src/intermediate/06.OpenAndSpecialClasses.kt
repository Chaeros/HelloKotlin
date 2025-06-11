package intermediate

// Kotlin 에서는 일반 클래스는 기본적으로 final class로 상속 불가
// open 키워드를 통해 클래스 상속 및 속성, 메서드 재정의가 가능하다
fun openAndSpecialClasses() {
    // 일반 클래스 상속을 위한 open 클래스 활용 예시
    val car = Car2("Hyundai","genesis g80",4)
    println("[Car Info]")
    println("make : ${car.make}")                       // make : Hyundai
    println("model : ${car.model}")                     // model : genesis g80
    println("numberOfDoors : ${car.numberOfDoors}")     // numberOfDoors : 4

    // open 키워드 사용을 통한 일반 클래스 함수 재정의 예시
    val car1 = Car3("KIA", "K7",4)
    val car2 = Car3("Hyundai","genesis g80",4)
    car1.displayInfo()  // Car Info : KIA - K7 - 4
    car2.displayInfo()  // Car Info : Hyundai - genesis g80 - 4

    // Special classes 1 - sealed class 예제
    // 같은 패키지 내에서만 자식 클래스 생성 가능
    println(greetMammal(Cat("Navy")))
    println(greetMammal(Human("Chaeros","programmer")))

    // Special classes 2 - enum class 예제
    val state = State.RUNNING
    val message = when (state) {
        State.IDLE -> "idle"
        State.RUNNING -> "running"
        State.FINISHED -> "finished"
    }
    println(message)

    val red = Color.RED
    println(red.containsRed())          // true
    println(Color.BLUE.containsRed())   // false
    println(Color.YELLOW.containsRed()) // true -> enum 속성 값을 확인하면 RED와 같음 확인 가능

    // Exercise 1
    val status1: DeliveryStatus = DeliveryStatus.Pending("Alice")
    val status2: DeliveryStatus = DeliveryStatus.InTransit("2024-11-20")
    val status3: DeliveryStatus = DeliveryStatus.Delivered("2024-11-18", "Bob")
    val status4: DeliveryStatus = DeliveryStatus.Canceled("Address not found")

    printDeliveryStatus(status1)
    printDeliveryStatus(status2)
    printDeliveryStatus(status3)
    printDeliveryStatus(status4)

    // Exercise 2
    val status5: Status = Status.Error(Status.Error.Problem.NETWORK)
    val status6: Status = Status.OK(listOf("Data1", "Data2"))

    handleStatus(status5)   // Network issue
    handleStatus(status6)   // Data received: [Data1, Data2]
}

// Kotlin에서는 클래스가 기본적으로 final class이기 때문에 open을 쓰지 않으면 일반 클래스 상속이 불가능
// 추상 클래스나 인터페이스는 예외
open class Vehicle2(val make: String, val model: String)

class Car2(make: String, model: String, val numberOfDoors: Int) : Vehicle2(make, model)

// 기본 클래스를 상속하기 위해 class 앞에 open 키워드 필요
// 일반 클래스의 속성이나 함수 또한 재정의를 위해서는 반드시 open 키워드 필요
open class Vehicle3(val make: String, val model: String) {
    open fun displayInfo() {
        println("Vehicle Info : Make - $make, Model - $model")
    }
}

class Car3(make: String, model: String, val numberOfDoors: Int) : Vehicle3(make, model) {
    override fun displayInfo() {
        println("Car Info : $make - $model - $numberOfDoors")
    }
}

// 속성 재정의 시 추천되지 않는 예시
open class Vehicle4(val make: String, val model: String) {
    open val transmissionType: String = "Manual"
}

class Car4(make: String, model: String, numberOfDoors: Int) : Vehicle4(make, model) {
    override val transmissionType: String = "Automatic"
}

// 속성 재정의 시 추천되는 예시
// 속성을 override하는 대신, 생성자에서 직접 접근하여 값을 전달(불필요한 override 제거)
open class Vehicle5(val make: String, val model: String, val transmissionType: String = "Manual")

class Car5(make: String, model: String, val numberOfDoors: Int) : Vehicle5(make, model, "Automatic")

// open class && interface
// open class 상속과 interface 구현을 동시헤 수행할 수 있다
interface EcoFriendly {
    val emissionLevel: String
}

interface ElectricVehicle {
    val batteryCapacity: Double
}

open class Vehicle6(val make: String, val model: String)

open class Car6(make: String, model: String, val numberOfDoors: Int) : Vehicle6(make, model)

// open class를 사용한 상속과 두 인터페이스를 구현한 class
class ElectricCar(
    make: String,
    model: String,
    numberOfDoors: Int,
    val capacity: Double,
    val emission: String
) : Car6(make, model, numberOfDoors), EcoFriendly, ElectricVehicle {
    override val batteryCapacity: Double = capacity
    override val emissionLevel: String = emission
}

// Special classes 1 - Sealed classes
// 같은 패키지 내의 클래스들만이 자식 클래스가 될 수 있음
sealed class Mammal(val name: String)

class Cat(val catName: String) : Mammal(catName)
class Human(val humanName: String, val job: String) : Mammal(humanName)

// when을 통해 mammal 이 어떤 타입인지에 따라 다른 결과값 반환
fun greetMammal(mammal: Mammal): String {
    when (mammal) {
        is Human -> return "Human : name=${mammal.name}, job=${mammal.job}"
        is Cat -> return "Cat : name=${mammal.name}"
    }
}

// Special classes 2 - enum classes
// 각 열거형 상수는 쉼표로 구분
enum class State {
    IDLE, RUNNING, FINISHED
}

// enum 클래스는 일반 클래스와 마찬가지로 속성과 멤버함수 가질 수 있음
// 속성 뒤에 멤버 함수가 오기전 ; 으로 구분
enum class Color(val rgb: Int) {
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF),
    YELLOW(0xFF0000);

    fun containsRed() = (this.rgb and 0xFF0000 != 0)
}

// Special classes 3 - inline classes
// @JvmInline 어노테이션을 통해 컴파일 타임에 실제 클래스는 없애고, 그 안의 프로퍼티 하나만 직접 사용(성능 최적화)
// -> 메모리 사용량 줄이고 코드의 런타임 성능 향상
// 하나의 프로퍼티(매개변수)만 가질 수 있으며, 두 개 이상인 경우 컴파일 에러 발생
// 타입 안정성 증가(sendEmail의 매개변수로 Email이 아닌 String 타입을 사용한 경우, 실수로 다른 정보 입력 가능)
@JvmInline
value class Email(val address: String)

fun sendEmail(email: Email) {
    println("Sending email to ${email.address}")
}

// Exercise 1
// sealed class DeliveryStatus 작성
sealed class DeliveryStatus {
    data class Pending(val sender: String) : DeliveryStatus()
    data class InTransit(val estimatedDeliveryDate: String) : DeliveryStatus()
    data class Delivered(val recipient: String, val deliveryDate: String) : DeliveryStatus()
    data class Canceled(val reason: String) : DeliveryStatus()
}

fun printDeliveryStatus(status: DeliveryStatus) {
    when (status) {
        is DeliveryStatus.Pending -> {
            println("The package is pending pickup from ${status.sender}.")
        }
        is DeliveryStatus.InTransit -> {
            println("The package is in transit and expected to arrive by ${status.estimatedDeliveryDate}.")
        }
        is DeliveryStatus.Delivered -> {
            println("The package was delivered to ${status.recipient} on ${status.deliveryDate}.")
        }
        is DeliveryStatus.Canceled -> {
            println("The delivery was canceled due to: ${status.reason}.")
        }
    }
}

// Exercise 2
sealed class Status {
    data object Loading : Status()
    data class Error(val problem: Problem) : Status() {
        enum class Problem {
            NETWORK, TIMEOUT, UNKNOWN
        }
    }

    data class OK(val data: List<String>) : Status()
}

fun handleStatus(status: Status) {
    when (status) {
        is Status.Loading -> println("Loading...")
        is Status.OK -> println("Data received: ${status.data}")
        is Status.Error -> when (status.problem) {
            Status.Error.Problem.NETWORK -> println("Network issue")
            Status.Error.Problem.TIMEOUT -> println("Request timed out")
            Status.Error.Problem.UNKNOWN -> println("Unknown error occurred")
        }
    }
}