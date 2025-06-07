package intermediate

fun lambdaExpressionsWithReceiver() {
    // 확장함수 예시
    fun StringBuilder.appendText() { append("Hello") }

    val stringBuilder = StringBuilder()
    stringBuilder.appendText()
    println("Lambda with receiver : ${stringBuilder.toString()}")   // Hello

    // 확장함수 대신 Lambda with receiver를 사용한 예시
    // Receiver : StringBuilder, () : 매개변수 없음, Unit : 반환값 없음
    // 람다식을 myLambda 변수에 저장
    val myLambda: StringBuilder.() -> Unit = {
        append("Hello")
    }

    val sb = StringBuilder()
    // StringBuilder를 Receiver로 갖는 람다식을 저장한 변수 myLambda 수행
    sb.myLambda()  // myLambda.invoke(sb)로 대체 가능
    println("Lambda with receiver : ${sb.toString()}")  // Hello

    // 일반 코드 형태
    // 수신자를 통해 메서드 호출
    val menu = Menu("Main Menu")
    menu.item("Home")
    menu.item("Settings")
    menu.item("Exit")

    // DSL(Domain Specific Language) 형태
    // 일반 코드 형태보다 코드 가독성이 높음(menu.~~~ 등 수신자 안 쓰고 람다식에서 처리 가능)
    val mainMenu = menu("Main Menu") {
        item("Home")
        item("MyInfo")
        item("Exit")
    }

    printMenu(mainMenu)

    // 연습문제
    // Exercise 1
    fetchData {
        append(" data add!")
        // this는 receiver인 StringBuilder
        println(this.toString())
    }

    // Exercise 2
    val button = Button()
    button.onEvent {
        if(!isRightClick && amount==2) println("Double click")
    }

    // Exercise 3
    val originalList = listOf(1,2,3)
    println(originalList)   // [1, 2, 3]
    val newList = originalList.incremented()
    println(newList)        // [2, 3, 4]
}

class MenuItem(val name: String)

class Menu(val name: String) {
    val items = mutableListOf<MenuItem>()

    fun item(name: String) {
        items.add(MenuItem(name))
    }
}

// init: Menu.() -> Unit 이 부분이 없으면, 호출부에서 람다식으로 item 메서드를 호출할 수 없다.
// menu.init() 호출을 생략하면, 전달된 람다가 실행되지 않아서 menu 객체에 item들이 추가되지 않는다.
fun menu(name: String, init: Menu.() -> Unit): Menu {
    val menu = Menu(name)
    menu.init() // 이 부분에서 init 람다가 실행되어야 호출부의 menu 객체에 item들이 추가
    return menu
}


fun printMenu(menu: Menu) {
    println("Menu: ${menu.name}")
    menu.items.forEach { println(" Item: ${it.name}") }
}

// Exercise 1
// StringBuilder에 문자열 add
fun fetchData(callback: StringBuilder.() -> Unit) {
    val builder = StringBuilder("Data received")
    builder.callback()
}

// Exercise 2
// 마우스 더블 클릭 시 Double click 문구 출력
data class Position(
    val x: Int,
    val y: Int
)

data class ButtonEvent(
    val isRightClick: Boolean,
    val amount: Int,
    val position: Position
)

class Button {
    fun onEvent(action: ButtonEvent.() -> Unit ) {
        val event = ButtonEvent(isRightClick = false, amount = 2, position = Position(100,200))
        event.action()
    }
}

// Exercise 3
// 기존 List 원소 값들 1씩 증가
fun List<Int>.incremented(): List<Int> {
    val originalList = this
    // buildList는 immutableList<T>를 반환하는 표준 라이브러리
    return buildList {
        originalList.forEach {
            add(it + 1)
        }
    }
}