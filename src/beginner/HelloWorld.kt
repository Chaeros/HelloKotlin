package beginner
// val은 변경 불가 변수(Read-only variables)
// var는 변경 가능 변수(Mutable variables)
// Kotlin은 입력한 타입을 추론하여 자동 배정한다.
val popcorn=5
var customers=10

fun helloWorld(){
    println("====================================")
    println("[HelloWorld]")
    println("Hello Kotlin")
    println(popcorn)
    println(customers)
    //popcorn=3 // val은 Read-only 변수로 컴파일 단계에서 에러
    customers=15
    println(customers)

    // $으로 "로 감싸진 String 내부에 변수 값을 삽입할 수 있음
    // ${}를 통해 출력 변수 값 변경 가능
    println("There are $customers customers")
    println("There are ${customers+1} customers")

    // 전(후)위연산자와 같은 표현식은 중괄호 내에 삽입 가능하나 문장은 불가
    println("There are ${++customers} customers ")
    //println("There are ${customers=8} customers ")

    // \$ -> \(백슬래시) 사용을 통해 $ 기호를 String에 포함 가능
    println("Dollar is \$")
}
