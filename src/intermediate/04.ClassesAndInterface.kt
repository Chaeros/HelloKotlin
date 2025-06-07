package intermediate

// Kotlin에서 모든 클래스는 최상위 클래스로 Any 클래스를 가진다
// 단일 클래스 상속 가능하며, 인터페이스는 다중 구현 가능
fun classesAndInterfaces() {
    // 추상 클래스 예제
    val laptop = Electronic(name="Laptop", price=3000.0, warranty = 2)
    println(laptop.productInfo())   // Product: Laptop, Category: Electronic, Price: 3000.0

    // 인터페이스 단일 구현 예제
    val paymentMethod = CreditCardPayment("1234 5678 0123 9999", "Chaeros", "06/07")
    println(paymentMethod)

    // 인터페이스 다중 구현 예제
    val paymentMethod2 = CreditCardPayment2("1234 5678 0123 9999", "Chaeros", "06/07")
    println(paymentMethod2.initiatePayment(200.0))

    println("Payment type : ${paymentMethod2.paymentType}")

    // Exercise
    val myRoomLight = SmartLight("My room light")
    val livingRoomThermostat = SmartThermostat("Living room thermostat")

    myRoomLight.turnOn()
    myRoomLight.adjustBrightness(10)
    myRoomLight.turnOff()

    livingRoomThermostat.turnOn()
    livingRoomThermostat.adjustBrightness(15)
    livingRoomThermostat.turnOff()

    // Exercise 2
    val audio = Audio("Symphony No.5 " , "Beethoven")
    audio.play()

    // Exercise 3
    val visa = CreditCard("Visa")
    visa.authorize(100.0)
    visa.processPayment(100.0)
    visa.refund(50.0)

    // Exercise 4
    val basicMessenger = BasicMessenger()
    val smartMessenger = SmartMessenger(basicMessenger)

    basicMessenger.sendMessage("Hello~~!")
    println(smartMessenger.receiveMessage())
    smartMessenger.sendMessage("Hi Hi!!!")
}

// 추상 클래스는 생성자가 있지만, 인스턴스 생성 불가
abstract class Product(val name: String, var price: Double) {
    // abstract 속성이나 함수는 상속받은 클래스가 반드시 override 해야함
    abstract val category: String
    // abstract 하지않고 구현한 속성이나 함수는 override 하지않아도 되며, 사용 가능
    fun productInfo(): String {
        return "Product: $name, Category: $category, Price: $price"
    }
}

// 추상 클래스인 부모 클래스로부터 받은 속성과 함수들 중 abstract인 것들은 반드시 override 해야함
class Electronic(name: String, price: Double, val warranty: Int): Product(name, price){
    override val category = "Electronic"
}

// abstract 키워드가 별도로 안 쓰임
// 구현하지 않은 함수라면 구현 클래스가 override 해야함
interface PaymentMethod {
    fun initiatePayment(amount: Double): String
}

class CreditCardPayment(val cardNumber: String, val cardHolderName: String, val expiryDate: String) : PaymentMethod {
    override fun initiatePayment(amount: Double): String {
        return "Payment of $$amount, Credit Card Number : $cardNumber"
    }
}

interface PaymentType {
    val paymentType: String
}

// 2개의 인터페이스를 구현한 클래스로, 각각에서 요구하는 함수와 속성을 override
class CreditCardPayment2(val cardNumber: String, val cardHolderName: String, val expiryDate: String) : PaymentMethod, PaymentType {
    override fun initiatePayment(amount: Double): String {
        return "Payment of $$amount, Credit Card Number : $cardNumber"
    }
    override val paymentType = "Credit Card"
}

interface Drawable {
    fun draw()
    fun resize()
    val color: String?
}

// 아래 Circle class는 color 변수가 명시적으로 정의되기 때문에
// 기본 생성자가 막힘
// 따라서 이를 상속받는 클래스는 반드시 Circle의 인스턴스를 상속 받아야함 ': Circle() 형태로'
// 기본적으로 Kotlin의 클래스 멤버는 final 상태가 기본값, open을 통해 상속 객체에서 override 할 수 있도록 변경 가능
open class Circle: Drawable {
    override fun draw() {
        // TODO는 만약 남아있으면 실행단계에서 런타임 에러 발생함
        // 개발 미완료를 뜻함
        TODO("draw")
    }
    override fun resize() {
        TODO("resize")
    }
    // : String? 으로 명시해주지 않으면 null로 정의되면서 Noting 타입으로 추론됨
    override open val color: String? = null
}

class RedCircle(): Circle() {
    override fun draw() {
        super.draw()
    }
    override fun resize() {
        super.resize()
    }
    // Kotlin에서는 val이 open이 아닌 경우, 기존에 정의 됐다면 구현 클래스에서 override 불가
    // -> 부모 클래스와 해당 속성도 open이 붙어야만 override 가능
    // 만약 Circle의 클래스와 속성에 open을 붙이지 않았따면 아래 코드는 컴파일 에러 발생
    override val color = "red"
}

// delegation 사용을 통해 override 해야 할 함수 및 속성을 param을 통해 대신함
// 보일러 코드(필요는 하지만 의미없이 반복 작성되는 코드, 똑같은 기능인데 자식 클래스가 모두 override 하는 등) 감소
class DelegationRedCircle(param: Circle): Drawable by param{
    override val color = "red"
}

// Exercise 1
// SmartDevice 추상 클래스와 SmartThermostat 클래스 구현
abstract class SmartDevice(val name: String) {
    abstract fun turnOn()
    abstract fun turnOff()
}

class SmartLight(name: String) : SmartDevice(name) {
    override fun turnOn() {
        println("$name is now ON.")
    }

    override fun turnOff() {
        println("$name is now OFF.")
    }

    fun adjustBrightness(level: Int) {
        println("Adjusting $name brightness to $level%.")
    }
}

class SmartThermostat(name: String) : SmartDevice(name) {
    override fun turnOn() {
        println("$name is now ON.")
    }

    override fun turnOff() {
        println("$name is now OFF.")
    }

    fun adjustBrightness(level: Int) {
        println("Adjusting $name brightness to $level%.")
    }
}

// Exercise 2
// Media 인터페이스와 이를 구현하는 Audio 클래스 구현
interface Media {
    val title: String
    fun play()
}

class Audio(override val title: String, val composer: String): Media {
    override fun play() {
        println("Playing audio: $title, composed by $composer")
    }
}

// Exercise 3
// Refundable 인터페이스와 PaymentMethod2 추상 클래스 작성 후 CreditCard 가 상속 및 구현하는 코드 작성
interface Refundable {
    fun refund(payment: Double)
}

abstract class PaymentMethod2(val name: String) {
    fun authorize(payment: Double) {
        println("Authorizing payment of $payment")
    }
    abstract fun processPayment(payment: Double)
}

class CreditCard(name: String): Refundable, PaymentMethod2(name) {
    override fun refund(payment: Double) {
        println("Refunding $$payment to the credit card")
    }
    override fun processPayment(payment: Double) {
        println("Processing credit card payment of $payment")
    }
}

// Exercise 4
// Delegation 사용하여 SmartMessenger 구현
interface Messenger {
    fun sendMessage(message: String)
    fun receiveMessage(): String
}

class BasicMessenger: Messenger {
    override fun sendMessage(message: String) {
        println("Sending message: $message")
    }

    override fun receiveMessage(): String {
        return "You've got a new message"
    }
}

class SmartMessenger(val basicMessenger: BasicMessenger): Messenger by basicMessenger {
    override fun sendMessage(message: String) {
        println("Sending message: $message")            // Sending message: Hi Hi!!!
        basicMessenger.sendMessage("[smart] $message")  // Sending message: [smart] Hi Hi!!!
    }
}