package intermediate

fun nullSafety_intermediate() {
    val myInt=42
    val myDouble=3.14
    val myList=listOf(1,2,3)

    // is, !is operator
    println(printObjectType(myInt))     // It's an Integer type : 42
    println(printObjectType(myDouble))  // It's an unknown type: 3.14
    println(printObjectType(myList))    // It's not a Double type : [1, 2, 3]

    // as, as? operator
    // as 연산자는 nullable 유형이 아니므로 불안전 as 연산자이다
    // as? 연산자는 nullable 유형으로 안전한 as 연산자
    val a: String? = null
    //val b = a as String // // runtime error 발생
    // println(b)

    val c: String? = null
    val d = c as? String    // runtime error 발생 x
    println(d)              // null

    // null이 아닌 list Collection 필터링
    val emails : List<String?> = listOf("aaa@naver.com",null,"bbb@daum.net",null,"ccc@google.com")
    val validEmails = emails.filterNotNull()
    println(validEmails)    // [aaa@naver.com, bbb@daum.net, ccc@google.com]

    // Kotlin Collection에서는 값을 찾지 못해도 오류를 발생시키지 않고 null을 반환
    val serverConfig = mapOf(
        "appConfig.json" to "App Configuration",
        "dbConfig.json" to "Database Configuration"
    )

    val requestedFile = "appConfig.json"
    val configFiles = listOfNotNull(serverConfig[requestedFile])
    val configFiles2 = listOfNotNull(serverConfig["abc"])

    println(configFiles)    // [App Configuration]
    println(configFiles2)   // []

    // singleOrNull() : 값에 대응되는 항목 하나 반환, 항목이 없거나 여러개인 경우 null 반환
    // maxOrNull() : 가장 높은 값 반환, 없으면 null 반환
    // minOrNull() : 가장 작은 값 반환, 없으면 null 반환
    val temperatures = listOf(15,18,21,21,19,17,16)
    val temperatures2 = listOf(30)

    val singleHotDay = temperatures.singleOrNull()
    val singleHotDay2 = temperatures2.singleOrNull()
    println("Is a single hot day? : ${singleHotDay ?: "None"}") // 리스트에 값이 7개로 None 출력
    println("Is a single hot day? : ${singleHotDay2 ?: "None"}") // 리스트에 값 1개로 30 출력

    val maxTemperature = temperatures.maxOrNull()
    println("maxTemperature : $maxTemperature") // 21

    val minTemperature = temperatures.minOrNull()
    println("minTemperature : $minTemperature") // 15

    val itemPrices = listOf(20,35,15,40,10)

    // reduceOrNull(a,b->a+b) : a는 맨 처음 값, b는 두 번째 값부터 마지막까지, a+b를 연산한 뒤 a 값으로 갱신
    val totalPrice = itemPrices.reduceOrNull { total, price -> total + price}
    println("totalPrice : $totalPrice") // 120

    val emptyCart = listOf<Int>()
    val emptyTotalPrice = emptyCart.reduceOrNull { total, price -> total + price }
    println("emptyCart : ${emptyTotalPrice ?: "No items"}") // No items

    val itemPrices2 = listOf(20,null,35,15,40,10)
    //val totalPrice2 = itemPrices2.reduceOrNull { total, price -> total + price} // compile error 발생
    val itemPrice2NotNull = itemPrices2.filterNotNull()
    val totalPrice2 = itemPrice2NotNull.reduceOrNull { total, price -> total + price }
    println("totalPrice2 : $totalPrice2")   // 120

    val user1 = User2(1,"park",listOf(2,3))
    val user2 = User2(2,"chan",listOf(1))
    val user3 = User2(3,"ho",listOf(1))

    val users = mapOf(1 to user1, 2 to user2, 3 to user3)
    println(getNumberOfFriends(users,1))    // 2
    println(getNumberOfFriends(users,2))    // 1
    println(getNumberOfFriends(users,4))    // -1

    // Exercise 1
    val userA = User3("park")
    val userB = User3(null)
    val userC = User3("chan")
    val invalidUser = "Not a user"

    println(getNotificationPreferences(userA,true,false))
    // [Email Notifications enabled for park]
    println(getNotificationPreferences(userB,false,true))
    // [SMS Notifications enabled for Guest]
    println(getNotificationPreferences(userC,true,true))
    // [Email Notifications enabled for chan, SMS Notifications enabled for chan]
    println(getNotificationPreferences(invalidUser,true,true))
    // []

    // Exercise 2
    val userWithPremiumPlan = listOf(
        Subscription("Basic",false),
        Subscription("Premium",true),
    )

    val userWithConflictingPlans = listOf(
        Subscription("Basic",true),
        Subscription("Premium",true),
    )
    println(getActiveSubscription(userWithPremiumPlan))
    // Subscription(name=Premium, isActive=true)
    println(getActiveSubscription(userWithConflictingPlans))
    // null

    // Exercise 3
    val allUsers = listOf(
        User4("park",true),
        User4("chan",false),
        User4("chan",true)
    )
    println(getActiveUsernames(allUsers))   // [park, chan]

    // Exercise 4
    println(validateStock(5,10))
    println(validateStock(null,10))
    println(validateStock(-2,10))
}

// is : 대상 객체에 해당하는 타입이 있는지 확인
// !is : 대상 객체에 해당하는 타입이 없는지 확인
fun printObjectType(obj: Any) {
    when(obj){
        is Int -> println("It's an Integer type : $obj")
        !is Double -> println("It's not a Double type : $obj")
        else -> println("It's an unknown type: $obj")
    }
}

fun calculateTotalStringLength(items: List<Any>): Int{
    var totalLength = 0
    for(item in items){
        totalLength += if (item is String) {
            item.length
        } else {
            0
        }
    }
    return totalLength
}

// 위 함수를 람다 표현식으로 가독성을 높인 함수
// ?. -> Safe call 연산자
// ?: -> null-safe를 지원하는 Elvis 연산자
fun calculateTotalStringLengthRefactoring(items: List<Any>): Int{
    // it as? String -> it가 String으로 안전하게 캐스팅되면 해당 값 반환, 아니면 null 반환
    // ?.length -> 앞의 결과가 null이 아니면 length 호출, 아니면 null 반환
    // ?: 0 -> 앞의 표현식이 null이면 0 반환
    return items.sumOf { (it as? String)?.length ?: 0 }
}

// Elvis operator
data class User2(
    val id: Int,
    val name: String,
    val friends: List<Int>
)

fun getNumberOfFriends(users: Map<Int,User2>, userId: Int): Int {
    // users map에서 userId에 해당하는 user없으면 -1 반환
    val user = users[userId] ?: return -1 // ?: -> Elvis 연산자 사용하여 null인 경우 조기 반환
    return user.friends.size
}

// 위 함수의 코드를 아래와 같이 더 간결히 할 수는 있으나 가독성 향상은 애매함
fun getNumberOfFriendsRefactoring(users: Map<Int,User2>, userId: Int): Int {
    return users[userId]?.friends?.size ?: -1
}

// Exercise 1
data class User3(val name: String?)

fun getNotificationPreferences(user: Any, emailEnabled: Boolean, smsEnabled: Boolean): List<String> {
    val validUser = user as? User3 ?: return emptyList()
    val userName = validUser.name ?: "Guest"

    // takeIf() 안의 값이 true면 반환, 아니면 x
    return listOfNotNull(
        "Email Notifications enabled for $userName".takeIf{emailEnabled},
        "SMS Notifications enabled for $userName".takeIf{smsEnabled}
    )
}

// Exercise 2
data class Subscription(val name: String, val isActive: Boolean)

fun getActiveSubscription(subscription: List<Subscription>): Subscription? {
    val result = subscription.filter { it.isActive }
    return result.singleOrNull()
    // 아래 구문도 복수 정답
    // return subscription.singleOrNull { subscription -> subscription.isActive }
    // return subscriptions.singleOrNull { it.isActive }
}

// Exercise 3
data class User4(val username: String, val isActive: Boolean)

fun getActiveUsernames(users: List<User4>): List<String> {
    return users.mapNotNull {
        user -> user.username.takeIf { user.isActive }
    }
}

// Exercise 4
fun validateStock(requested: Int?, available: Int?): Int {
    val request = requested ?: return -1
    val origin = available ?: return -1
    if(request<0) return -1
    if(request>origin) return -1
    return request
}