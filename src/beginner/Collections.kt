package beginner

fun collections(){
    listExample()
    setExample()
    mapExample()
}

fun listExample(){
    println("====================================")
    println("[Collections_List]")
    // List -> listOf() 메서드로 생성 : Read-only list
    // MutableList -> mutableListOF() 메서드로 생성 : 추가 삭제 가능 List
    // <> 를 사용하여 List의 타입을 명시적 지정 가능(default 자동)
    val readOnlyShapes = listOf("A", "B", "C")
    val shapes: MutableList<String> = mutableListOf("A", "B", "C")

    println(readOnlyShapes)     // [A, B, C]
    println(shapes)             // [A, B, C]

    // mutable한 List를 Read-only List로 변경
    val shapes2: MutableList<String> = mutableListOf("A", "B", "C")
    val shapes2Locked: List<String> = shapes2
    println(shapes2Locked)      // [A, B, C]

    // List collection에서는 인덱스를 통해 원소에 접근 가능
    // List collection의 원소에 접근하기 위해서는 ${}를 사용해야함
    val readOnlyShapes2: MutableList<String> = mutableListOf("A", "B", "C")
    println("The first item in the list is : $readOnlyShapes2")         // [A, B, C]
    println("The first item in the list is : ${readOnlyShapes2[0]}")    // A
    println("The first item in the list is : $readOnlyShapes2[0]")      // [A, B, C][0]

    // List의 시작 원소와 마지막 원소 출력
    println("The first item in the list is : ${readOnlyShapes2.first()}")   // A
    println("The last item in the list is : ${readOnlyShapes2.last()}")    // C

    // List 원소 개수
    println("This list has : ${readOnlyShapes2.count()} items")
    println("This list has : ${readOnlyShapes2.size} items")

    // List에 포함된 원소인지 확인
    println("List in test : "+("C" in readOnlyShapes2))

    // MutableList 원소 삽입/삭제
    shapes.add("D")
    println(shapes)                 // [A, B, C, D]
    shapes.remove("C")
    println(shapes)                 // [A, B, D]
}

fun setExample(){
    println("====================================")
    println("[Collections_Set]")
    // Set -> setOF() 메서드로 생성 : Read-only Set
    // MutableSet -> mutableSetOf() 메서드로 생성 : 추가 삭제 가능 Set
    // <> 꺽새를 사용하여 Set의 자료형을 명시적으로도 지정 가능
    val readOnlyShape = setOf("A", "B", "C", "C")
    val shape: MutableSet<String> = mutableSetOf("A","B","C","C")

    println(readOnlyShape)  // [A, B, C]
    println(shape)          // [A, B, C]

    // mutable한 set을 Read-only한 set으로 변경
    val shapes2: MutableSet<String> = mutableSetOf("A", "B", "C", "C")
    val shapes2Locked: MutableSet<String> = shapes2
    println(shapes2Locked)  // [A, B, C]

    // set이 가지고 있는 원소 수 반환
    val readOnlyShape2 = setOf("A", "B", "C", "C")
    println("This set has ${readOnlyShape2.count()} items") // This set has 3 items
    println("This set has ${readOnlyShape2.size} items")    // This set has 3 items

    // set이 가지고 있는 원소인지 판별
    println("B is in a set : " + ("B" in readOnlyShape2))   // B is in a set : true

    // MutableSet 원소 삽입 삭제
    shape.add("Z")
    println(shape)  // [A, B, C, Z]
    shape.remove("A")
    println(shape)  // [B, C, Z]
}

fun mapExample(){
    println("===============================")
    println("[Collections_Map]")
    // Map -> mapOf() 메서드로 생성 : Read-only map
    // MutableMap -> mutableMap() 메서드로 생성 : 추가 삭제 가능 map
    val readOnlyJuiceMenu = mapOf("apple" to 100, "kiwi" to 200, "orange" to 300)
    val juiceMenu: MutableMap<String,Int> = mutableMapOf("apple" to 100, "kiwi" to 200, "orange" to 300)
    println(readOnlyJuiceMenu)  // {apple=100, kiwi=200, orange=300}
    println(juiceMenu)          // {apple=100, kiwi=200, orange=300}

    // MutableMap을 Read-only한 Map으로 변경
    val juiceMenuLocked: Map<String,Int> = juiceMenu
    println(juiceMenuLocked)    // {apple=100, kiwi=200, orange=300}

    // Map의 [] 대괄호를 통해 특정 key 값의 value 호출 가능
    println("The value of apple juice is : ${readOnlyJuiceMenu["apple"]}")

    // 없는 key값을 조회하려고 하면 null 값이 출력
    println("The value of apple juice is : ${readOnlyJuiceMenu["melon"]}")

    // [](대괄호) 사용을 통한 map add
    println(juiceMenu)  // {apple=100, kiwi=200, orange=300}
    juiceMenu["watermelon"]=150
    println(juiceMenu)  // {apple=100, kiwi=200, orange=300, watermelon=150}
    // juiceMenu.add("strawberry" to 100) // 사용 불가

    // map item 삭제
    println(juiceMenu)  // {apple=100, kiwi=200, orange=300, watermelon=150}
    juiceMenu.remove("watermelon")
    println(juiceMenu)  // {apple=100, kiwi=200, orange=300}

    // map count
    println("This map has ${juiceMenu.count()}")    // This map has 3
    println("This map has ${juiceMenu.size}")       // This map has 3

    // map 에 속한 key 값인지 판별
    println("kiwi is in map : " + (juiceMenu.contains("kiwi")))

    // key, value list 출력
    println("juiceMenu map keys : " + juiceMenu.keys)       // [apple, kiwi, orange]
    println("juiceMenu map values : " + juiceMenu.values)   // [100, 200, 300]

    // key, value 존재 여부 판별
    println("orange is in map : " + (juiceMenu.contains("orange")))
    println("orange is in map : " + ("orange" in juiceMenu.keys))
    println("value 999 is in map : " + (999 in juiceMenu.values))
}