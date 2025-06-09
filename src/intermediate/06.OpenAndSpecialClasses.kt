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