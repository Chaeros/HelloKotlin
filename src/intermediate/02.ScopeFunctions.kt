package intermediate

// Kotlin 에서의 5개 범위 함수 : let, apply, run, also, with
fun scopeFunction() {
    val address: String? = getNextAddress()
    // address는 nullable 타입 변수이고,
    // sendNotification()은 nullable 타입 반환이 예상되지 않음으로
    // 아래 호출은 컴파일 에러 발생
    //sendNotification(address)

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
}

fun sendNotification(recipientAddress: String): String {
    println("recipient address: $recipientAddress")
    return "Notification sent"
}

fun getNextAddress(): String {
    return "chaeros@naver.com"
}