package intermediate

// Kotlin에서 object 선언을 사용하면 싱글톤과 같은 단일 인스턴스를 가진 클래스 선언 가능
// Kotlin의 객체는 접근 시에만 생성되는 Lazy 방식 동작
// JVM에서 Threads-safe(여러 스레드가 같은 자원에 접근해도 같은 결과 보장)를 보장
fun objects() {
    println("[05.Objects]")
    // object
    DoAuth.takeParam("myID","1234") // Auth param = myID:1234

    // data object
    println(DoAuth)                     // intermediate.DoAuth@61e4705b -> 내부적으로 toString() 제공x
    println(AppConfig)                  // AppConfig -> 내부적으로 자동으로 toString()을 붙힘
    println(AppConfig.appName)          // intermediate
    println(AppConfig.hashCode())       // -2106057234
    println(AppConfig.equals(DoAuth))   // false
    println(AppConfig.equals(AppConfig))// true

    // companion object
    Alarm.getBeep(12)
    // beep beep beep beep beep beep beep beep beep beep beep beep

    // Exercise 1
    println("Order name: $OrderOne")
    println("Order name: $OrderTwo")

    println("Are the two orders identical? ${OrderOne == OrderTwo}")

    if(OrderOne == OrderTwo){
        println("OrderOne == OrderTwo : true")
    }
    else{
        println("OrderOne == OrderTwo : false")
    }

    println("Is the same OrderOen and OrderTwo? ${OrderOne == OrderTwo}")
    // Is the same OrderOen and OrderTwo? false

    // Exercise 2
    println("${FlyingSkateboard.name}: ${FlyingSkateboard.move()}")
    println("${FlyingSkateboard.name}: ${FlyingSkateboard.fly()}")

    // Exercise 3
    val fahrenheit = 90.0
    val temp = Temperature.fromFahrenheit(fahrenheit)
    println("${temp.celsius}°C is $fahrenheit °F")  // 32.22222222222222°C is 90.0 °F
}

object DoAuth {
    fun takeParam(username: String, password: String) {
        println("Auth param = $username:$password")
    }
}

interface Auth {
    fun takeParam(username: String, password: String)
}

// object는 클래스와 인터페이스를 상속받거나 구현할 수 있음
object ImplAuth: Auth {
    override fun takeParam(username: String, password: String) {
        println("Auth param = $username:$password")
    }
}

// data object
// toString(), equals(), hashCode() 자동 구현
data object AppConfig {
    var appName: String = "intermediate"
    var version: String = "1.0.0"
}

// Companion object(동반 객체)
// 클래스 당 동반 객체는 하나만 가질 수 있음
// 해당 클래스가 처음 참조될 때 동반 객체가 Lazy 생성됨
// 다른 object와 달리 클래스 내부에 위치
class Alarm {
    companion object Buzzer {
        fun getBeep(nTimes: Int) {
            repeat(nTimes) {print("beep ")}
        }
    }
}

// Exercise 1
// OrderTwo object 작성
interface Order {
    val orderId: String
    val customerName: String
    val orderTotal: Double
}

data object OrderOne: Order {
    override val orderId = "orderOneID"
    override val customerName = "orderOneCustomer"
    override val orderTotal = 10.50
}

data object OrderTwo: Order {
    override val orderId = "orderTwoID"
    override val customerName = "orderTwoCustomer"
    override val orderTotal = 15.50
}

// Exercise 2
// FlyingSkateboard object 작성
interface Vehicle {
    val name: String
    fun move(): String
}

object FlyingSkateboard: Vehicle {
    override val name = "Flying Skateboard"
    override fun move(): String {
        return "Glides through the air with a hover engine"
    }
    fun fly(): String = "Woooooooo"
}

// Exercise 3
// 입력받은 화씨를 섭씨로 변경하여 멤버 변수 celsius로 가지는 Temperature 객체 생성 함수 작성
data class Temperature(val celsius: Double) {
    val fahrenheit: Double = celsius * 9/5 + 32
    companion object {
        fun fromFahrenheit(fahrenheit: Double): Temperature
        = Temperature((fahrenheit-32)*5/9)
    }
}