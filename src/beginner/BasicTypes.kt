package beginner

// Kotlin이 타입을 추론 Int
var market=10

fun basicTypes(){
    println("====================================")
    println("[BasicTypes]")
    // 추론된 Int 타입으로 연산 수행
    println("pre value = $market")
    market=8             // 8
    market = market+3 // 11
    market+=7            // 18
    market-=3            // 15
    market*=2            // 30
    market/=3            // 10
    println("after value = $market")

    // Kotlin은 Java와 달리 안전성을 위해
    // 명확한 형변환 없는 한 아래 구문 허용 X
    // market+="hello"

    // 문자열과 martket 변수를 더하고 싶다면
    var result = "dangeun count="+"$market"
    println(result)

    // 명시적 타입 선언
    val aType: Int
    val bType: String="hello"
    // 명시적 타입 선언 후 정의를 하지 않으면 에러 발생
    // println(d)
    aType=3
    println("aType=$aType")
    println("bType=$bType")

    // 타입 종류와 명시적 타입 선언
    val a: Int=1000
    val b: String="message"
    val c: Double=3.14
    val d: Long=100_000_000_000_000
    val e: Boolean=true
    val f: Char='a'
}