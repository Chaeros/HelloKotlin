package beginner.Functions

fun functionsOperator(){
    println("====================================")
    println("[Functions_function]")
    hello() // Hello Kotlin
    println("1+2=${sum(1,2)}")  // 1+2=3
    // 함수의 매개변수에서 명명된 인수 사용을 통해 순서가 달라도 호출 가능
    printMessageWithPrefix(prefix="Log",message="Hi")   // [Log] Hi
    // 함수 선언 순서대로 매개변수 입력
    printMessageWithPrefix2("Nice to meet you","Log")   // [Log] Nice to meet you
    // 함수의 default 값 사용
    printMessageWithPrefix2("Hello")    // [Info] Hello
    // 명명 인수 사용을 통한 매개변수 순서 변경
    printMessageWithPrefix2(prefix="Log", message="HiHi")   // [Log] HiHi
    // 반환 타입 없는 함수
    printMessage("Hello~!") // Hello~!
    // 대입 연산을 통한 함수 타입 및 관 추론, Kotlin에서는 반환 유형 선언이 바람직
    println("1+2=${sum2(1,2)}") // 1+2=3
    // 매개변수 조건에 따른 함수의 조기 반환
    println(registerUser("chaeros","chaeros@naver.com"))    // Username already taken.
    println(registerUser("byebye",email="email@naver.com")) // User registered successfully : byebye || email@naver.com
}

