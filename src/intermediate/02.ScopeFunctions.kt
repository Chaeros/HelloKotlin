package intermediate

// Kotlin 에서의 5개 범위 함수 : let, apply, run, also, with
fun scopeFunction() {
    val address: String? = getNextAddress()
    // address는 nullable 타입 변수이고,
    // sendNotification()은 nullable 타입 반환이 예상되지 않음으로
    // 아래 호출은 컴파일 에러 발생
    //sendNotification(address)

    // 1. let
    // 아래와 같은 방법으로 null값 판단 후 메서드를 실행시킬 수 있음
    // 하지만 보기가 간결하지 않음
    val confirm=if(address!=null) {
        sendNotification(address)
    } else {null}

    // let 범위 함수 사용을 통해 보다 간결한 코드 작성 가능
    // let을 통해 address가 null이 아닌 경우 괄호안 구문 실행
    // address 본인을 it라는 변수로 참조
    val confirm2=address?.let{
        sendNotification(it)
    }

    // 2. apply
    // 아래와 같은 방법은 함수에서 속성을 초기화하고 멤버 함수를 호출하기 전에 클레스 client 인스턴스를 생성
    // 간결하지만 클래스 인스턴스 생성 후 사용까지 시간이 걸림
    var client = Client()

    client.token = "asdf"
    client.connect()
    client.authenticate()
    client.getData()

    // apply 범위 함수 사용을 통해 인스턴스를 참조할 필요 없이 멤버 함수 호출
    // apply의 인자로 들어가는 블록 전체(람다식)에서는 this없이 프로퍼티나 메서드에 접근 가능
    // 인스턴스 생성과 동시에 속성 초기화 및 멤버 함수 호출 가능
    // 인스턴스에서 접근도 가능
    client2.getData()

    // 3. run
    // apply와 비슷하게 객체를 초기화할 수 있음
    // run은 코드의 특정 순간에 객체를 초기화하고 결과를 즉시 활용하는데 사용
    val result : String = client2.run {
        connect()
        authenticate()
        // 마지막의 이 부분만 반환한다. result가 String 타입이므로, println을 반환하는 위 함수를 마지막에 두면 컴파일 에러 발생
        // 반환된 결과를 효율적으로 코드에서 활용 가능
        getData()
    }
    println(result) // Mock data

    // 4. also
    // map()을 통해 medals 값 대문자로 변경
    // filter()를 통해 List 중 조건에 만족하는 값만 유지
    // reversed()를 통해 뒤짚기
    val medals: List<String> = listOf("Gold","Silver","Bronze")
    val reversedLongUppercaseMedals : List<String>
        = medals
            .map{it.uppercase()}
            .filter{it.length>4}
            .reversed()
    println(reversedLongUppercaseMedals)    // [BRONZE, SILVER]

    // also 범위 함수 내부에서 람다 표현식 전달 가능
    // it 객체 반환과 더불어 로깅 및 디버깅 등, 코드 주요 흐름을 방해하지 않고 부가 작업 수행 가능
    val medals2 : List<String> = listOf("Gold","Silver","Bronze")
    val reversedLongUppercaseMedals2 : List<String> =
        medals2
            .map{it.uppercase()}
            .also{println(it)}  // [GOLD, SILVER, BRONZE]
            .filter{it.length>4}
            .also{println(it)}  // [SILVER, BRONZE]
            .reversed()
    println(reversedLongUppercaseMedals2)   // [BRONZE, SILVER]

    // 5. with
    val aaabbbcccdddeeefffCanvas1 = Canvas()
    aaabbbcccdddeeefffCanvas1.text(10, 10, "Foo")
    aaabbbcccdddeeefffCanvas1.rect(20, 30, 100, 50)
    aaabbbcccdddeeefffCanvas1.circle(40, 60, 25)

    // 복잡한 변수에도 가독성 좋게 객체 생성과 동시에 멤버 함수 호출 가능
    val aaabbbcccdddeeefffCanvas2 = Canvas()
    with(aaabbbcccdddeeefffCanvas1) {
        text(10, 10, "Foo")
        rect(20, 30, 100, 50)
        circle(40, 60, 25)
    }
}

// 1. let에 사용
fun sendNotification(recipientAddress: String): String {
    println("recipient address: $recipientAddress")
    return "Notification sent"
}

fun getNextAddress(): String {
    return "chaeros@naver.com"
}

// 2. apply에 사용
class Client() {
    var token: String? = null
    fun connect() = println("connected")
    fun authenticate() = println("authenticated")
    fun getData(): String = "Mock data"
}

val client2 = Client().apply{
    token = "token"
    connect()
    authenticate()
}

// 5. with에 사용
class Canvas {
    fun rect(x: Int, y: Int, width: Int, height: Int): Unit = println("$x,$y,$width,$height")
    fun circle(x: Int, y: Int, rad: Int): Unit = println("$x,$y,$rad")
    fun text(x: Int, y: Int, str: String): Unit = println("$x,$y,$str")
}