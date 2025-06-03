package intermediate

fun extensionFunction() {
    // 기존 String type의 확장함수
    println("hello kotlin".bold())

    // 사용자 생성 클래스에서의 확장함수
    val client = HttpClient()
    val getRequestResponse = client.request("Get","https://localhost:8080/info",emptyMap())
    val extensionGetRequestResponse = client.get("https://localhost:8080/info")
    println(getRequestResponse)             // MyHttpResponse(statusCode=200, body=success)
    println(extensionGetRequestResponse)    // MyHttpResponse(statusCode=200, body=success)

    // Exercise
    println("Exercise 1 : ${1.isPositive()}")                       // true
    println("Exercise 2 : " + "HELLO KOTLIN".toLowerCaseString())   // hello kotlin
}

// Extension Function, 기존 String class에 bold()라는 확장함수 추가
// 확장함수를 통해 기존 원본 소스 코드를 변경하지 않고 기능을 추가할 수 있음
// 확장 중심 설계 구현 가능, 코드의 가독성과 유지 관리 향상
// 문자열 템플릿은 $this 를 사용하여 접근
fun String.bold(): String = "<b>$this</b>"

data class MyHttpResponse(val statusCode: Int, val body: String){}

class HttpClient{
    fun request(method: String, url: String, headers: Map<String,String>): MyHttpResponse {
        return MyHttpResponse(200,"success")
    }
}

// 확장함수 활용을 통해 원본 HttpClient 코드 변경없는 기능 확장 가능
fun HttpClient.get(url: String): MyHttpResponse = request("Get","https://localhost:8080/info",emptyMap())
fun HttpClient.post(url: String): MyHttpResponse = request("Post","https://localhost:8080/info",emptyMap())

// Exercise 1
fun Int.isPositive() : Boolean {
    if(this>=0) return true
    return false
}

// Exercise 2
fun String.toLowerCaseString() : String = this.lowercase()