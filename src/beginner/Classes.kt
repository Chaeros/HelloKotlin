package beginner

import kotlin.random.Random

// 클래스 괄호 안에 있는 내용을 클래스 헤더(속성) 라고 한다
// 클래스 속성을 선언할 때 쉼표로 구분
// val : 변경 불가, var : 변경 가능
class Contract(val id: Int, var email: String){
    val category: String=""
}

// 클래스 속성에 default 값을 지정할 수 있음
class Contract2(val id: Int, val email: String = "chaeros@gmail.com"){
    val category: String="work"
}

// 멤버 함수 사용 예시
class Contract3(val id: Int, var email: String){
    fun printId(){
        println(id)
    }
}

// 데이터 클래스
// 클래스와 동일한 기능 제공하면서 추가 멤버 함수 자동 제공
// 1) toString() : 클래스 인스턴스와 해당 속성의 읽을 수 있는 문자열 출력
// 2) equals() : 클래스의 인스턴스 비교
// 3) copy() : 클래스를 복사하여 다른 속성을 갖는 클래스 인스턴스 생성
data class User(val name: String, val id: Int)

fun classesOperator(){
    println("====================================")
    println("[Classes]")
    // 기본 생성자를 통해 클래스 인스턴스 생성
    val contract = Contract(1,"chaero@gmail.com")
    println("category = ${contract.category}")  // ""
    println(contract.id)    // 1
    println(contract.email) // chaero@gmail.com
    contract.email="roro@gmail.com"
    println(contract.email) // roro@gmail.com
    println("Their email address is : ${contract.email}")   // Their email address is : roro@gmail.com

    // default 클래스 속성 사용 예시
    val contract2 = Contract2(2,"chaero@gmail.com")
    println("category = ${contract2.category}") // category = work

    // 멤버 함수 사용
    val contract3 = Contract3(3,"kotlin@gmail.com")
    contract3.printId() // 3

    // 데이터 클래스
    // 1) toString()
    val user = User("Chaeros",1)
    println(user)   // User(name=Chaeros, id=1)

    // 2) equals()
    val secondUser = User("Choco",2)
    val thirdUSer = User("Milk",3)
    println("user==secondUser: ${user==secondUser}")    // user==secondUser: false
    println("user==thirdUSer: ${user==thirdUSer}")      // user==thirdUSer: false

    // 3) copy()
    // 인스턴스 복사본 생성
    // 속성 변경을 원한다면 대체 값 함수 매개변수에 입력
    // 인스턴스 복사본을 만드는 것이 원본 인스턴스를 수정하는 것보다 안전(불변성 유지)
    // 단, 인스턴스 생성이 잦을 경우 성능 저하될 수 있으니 유의
    val user2 = User("Cup",4)
    println(user2.copy())                   // User(name=Cup, id=4)
    println(user2.copy("Mouse"))    // User(name=Mouse, id=4)
    println(user2.copy(id=3))               // User(name=Cup, id=3)

    // 예제 풀이
    classesPrepare1()
    classesPrepare2()
    classesPrepare3()
}

// 연습문제
data class Employee(val name: String, var salary: Int)

fun classesPrepare1(){
    val emp=Employee("Wall",20)
    println(emp)    // Employee(name=Wall, salary=20)
    emp.salary+=20
    println(emp)    // Employee(name=Wall, salary=40)
}

data class Person(val name: Name, val address: Address, val ownsAPet: Boolean = true)
data class Name(val first: String, val last: String)
data class Address(val street:String, val city: City)
data class City(val name: String, val country: String)

fun classesPrepare2(){
    val person = Person(
        Name("Park","Chaeros"),
        Address("gold street",City("Cheonan","Korea")),
        false
    )
    println(person) // Person(name=Name(first=Park, last=Chaeros), address=Address(street=gold street, city=City(name=Cheonan, country=Korea)), ownsAPet=false)
}

data class Employee2(val name: String, val salary: Int)

class RandomEmployeeGenerator(var min: Int, var max: Int) {
    val names = listOf("Chan","Ho","Park","Cup","Mouse","Choco")
    fun generateEmployee() = Employee(names.random(),
        Random.nextInt(min, max))
}

fun classesPrepare3(){
    val empGen = RandomEmployeeGenerator(5,20)
    println(empGen.generateEmployee())  // Employee(name=Ho, salary=7)
    println(empGen.generateEmployee())  // Employee(name=Mouse, salary=11)
    empGen.min=30
    empGen.max=60
    println(empGen.generateEmployee())  // Employee(name=Choco, salary=45)
}