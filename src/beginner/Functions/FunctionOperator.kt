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

fun lambdaOperator(){
    // lambda를 적용하지 않은 기본 함수
    println(uppercaseString("hello"))
    // lambda를 적용하여 메서드를 간략화시킨 모습
    val upperCaseString={text:String->text.uppercase()}
    println(upperCaseString("hello"))
    
    // 람다표현식 유의할점
    // 1. 매개변수 뒤에 '->' 가 붙는다
    // 2. '->' 이후 함수의 본문이 온다
    // 3. 매개변수 없이 람다를 선언하면 '->'를 사용할 필요X

    // 람다표현식 사용 방법
    // 1. 람다 표현식을 다른 함수에 매개변수로 전달
    // 2. 함수에서 람다 표현식 반환
    // 3. 람다 표현식 단독 호출

    // [1. 람다 표현식을 다른 함수에 매개변수로 전달]
    val numbers=listOf(1,-2,3,-4,5,-6)
    // filter를 통해 양수만 추출
    val positives = numbers.filter ({x->x>0})

    val isNegetive={x:Int->x<0}
    val negatives=numbers.filter(isNegetive)

    println(positives) // [1, 3, 5]
    println(negatives) // [-2, -4, -6]

    // map을 사용
    val numbers2=listOf(1,-2,3,-4,5,-6)
    // map을 통해 기존 리스트의 값 변형
    val doubled=numbers.map {x->x*2}

    val isTripled={x:Int->x*3}
    val tripled=numbers2.map{isTripled}

    println(doubled) // [2, -4, 6, -8, 10, -12]
    println(tripled) // [3, -6, 9, -12, 15, -18]

    // [2. 함수에서 람다 표현식 반환]
    // () 안의 변수 : 매개변수
    // '->' 뒤의 변수 : 반환타입
    // 람다 표현식에 매개변수가 없으면 '()' 안은 비워둡니다.
    val upperCaseString2: (String)->String={text->text.uppercase()}
    println(upperCaseString2("hello"))  // HELLO

    // ▶ 문제가 발생하는 코드
    // val upperCaseString = { str -> str.uppercase() }
    // 위 코드는 str이 어떤 타입인지 명시되어 있지 않아서 어떤 uppercase를 써야하는지 알 수 없음
    // 람다식을 쓸 때에는 매개변수 타입이나 전체 함수 타입을 명확히 적어야 컴파일러가 타입 추론 가능

    // 해결책 1. 함수 타입 명시
    val upperCaseString3: (String)->String={text->text.uppercase()}
    // 해결책 2. 람다 내부에서 매개변수 타입 직접 명시
    val upperCaseString4: (String)->String={str: String->str.uppercase()}

    val timesInMinutes=listOf(2,10,15,1)
    val min2sec=toSeconds("minute")
    val totalTimeInseconds=timesInMinutes.map(min2sec).sum()
    println("Total time is $totalTimeInseconds secs")   // Total time is 1680 secs

    println({text:String->text.uppercase()}("hello"))   // HELLO

    // 일반적 람다 표현식 형태
    // fold는 리스트 항목을 누적해서 계산하는 함수
    println(listOf(1,2,3).fold(0,{x,item->x+item}))
    // 후행 람다
    // 람다 표현식이 함수의 마지막 매개변수로 전달되는 경우
    // () 괄호 바깥으로 람다를 빼낼 수 있다 -> 가독성 향상
    println(listOf(1,2,3).fold(0){x,item->x+item})
}

