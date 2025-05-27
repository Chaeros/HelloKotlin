package beginner.Functions

import jdk.internal.util.StaticProperty.userName

fun hello(){
    return println("Hello Kotlin")
}

// x,y : parameter
// 매개 변수의 : Int -> 명시적 타입 지정
// 함수의 : Int -> 함수 반환 타입
fun sum(x: Int, y: Int) : Int {
    return x+y
}

fun printMessageWithPrefix(message: String, prefix: String) {
    println("[$prefix] $message")
}

// 매개변수의 default 값 사용
fun printMessageWithPrefix2(message: String, prefix: String = "Info") {
    println("[$prefix] $message")
}

// 반환 타입이 없는 함수
fun printMessage(message: String){
    println(message)
}

// 대입 연산자를 통한 함수 반환 타입 추론
fun sum2(x: Int, y: Int) = x + y

val registeredUserNames=mutableListOf("chaeros","nyangnyang")
val registeredMails=mutableListOf("chearos@naver.com","cat@naver.com")

// return을 사용한 함수 조기 반환
fun registerUser(username: String, email: String) : String {
    if(username in registeredUserNames){
        return "Username already taken."
    }
    if(email in registeredMails){
        return "Email already registered."
    }
    registeredUserNames.add(username)
    registeredMails.add(email)
    return "User registered successfully : $username || $email"
}