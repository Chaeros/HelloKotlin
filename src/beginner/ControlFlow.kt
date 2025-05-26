package beginner

fun controlFlow(){
    ifExample()
    whenExample()
    rangesExample()
    whileExample()
}

fun ifExample(){
    val d: Int
    val check = true

    // if 조건문 예씨
    if(check) {
        d=1
    }
    else{
        d=2
    }
    println(d) // 1

    // Kotlin에서는 삼항 연산자가 존재X
    val a=1
    val b=2
    println(if(a>b) a else b)   // 2
}

fun whenExample(){
    // when 조건절 사용법1
    val obj="hihi"
    when(obj){
        "1"->println("One")
        "hihi"->println("Nice to meet you")
        else->println("Unkwon")
    }   // Nice to meet you

    // when 조건절 사용법2
    val obj2="good"
    val result = when(obj2){
        "1"->"One"
        "good"->"Say good"
        else->"Unkown"
    }
    println(result) // Say good

    // when 조건절 사용법3
    val trafficLightState="Green"
    val trafficAction = when{
        trafficLightState == "Green" -> "Go"
        trafficLightState == "Yellow" -> "Slow down"
        trafficLightState == "Red" -> "Stop"
        else -> "Malfunction"
    }
    println(trafficAction)  // Go

    // when 조건절 사용법 4
    val trafficLightState2="Red"
    val trafficAction2 = when(trafficLightState2){
        "Green" -> "Go"
        "Yellow" -> "Slow down"
        "Red" -> "Stop"
        else -> "Malfunction"
    }
    println(trafficAction2) // Stop
}

fun rangesExample(){
    // 1..5 -> 12345
    for(number in 1..5){
        println(number)
    } // 12345

    // list 원소 반복
    val cakes = listOf("strawberry", "chocolate", "cheese")
    for(cake in cakes){
        println("It's a $cake cake")
    }
    // It's a strawberry cake
    // It's a chocolate cake
    // It's a cheese cake
}

fun whileExample(){
    // 기본 while
    var count=0
    while(count<3){
        println("current count : $count")
        count++
    }
    // current count : 0
    // current count : 1
    // current count : 2

    // while, do-while
    var countA=0
    var countB=0
    while(countA<3){
        println("current countA : $countA")
        countA++
    }
    do{
        println("current countB : $countB")
        countB++
    } while(countB<countA)
    // current countA : 0
    // current countA : 1
    // current countA : 2
    // current countB : 0
    // current countB : 1
    // current countB : 2
}