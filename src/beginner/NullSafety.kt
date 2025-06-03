package beginner

fun nullSafety(){
    // nullable을 명시하지 않으면 기본 타입 변수는 null을 가질 수 없다
    var neverNull : String = "This can't be null"
    //neverNull=null    // compile error O

    // '?' 기호를 통해 nullable 변수 지정 가능
    var nullable: String ? = "This can be null"
    nullable = null // compile error X

    var inferredNonNull = "This can't be null too"
    //inferredNonNull=null    // compile error O

    // 매개변수나 반환타입 모두 nullable X
    fun strLength(notNull : String) : Int{
        return notNull.length
    }

    // 매개 변수나 반환 타입 뒤에'?' 를 붙여 nullable로 입력 가능
    fun strLengthNullable(notNull : String?) : Int {
        if(notNull==null) return 0
        return notNull.length
    }

    println(strLength(neverNull))           // 18
    println(strLengthNullable(neverNull))   // 18
    //println(strLength(nullable))  // compile error O
    println(strLengthNullable(nullable))    // 0

    println(describeString(neverNull))  // Length of string = 18
    println(describeString(nullable))   // Empty or null string

    println(lengthString(neverNull))    // 18
    println(lengthString(nullable))     // null

    var representativeProduct = Product("GalaxyS")
    var company = Company("SAMSUNG",representativeProduct,null)
    println(company)    // Company(name=SAMSUNG, representativeProduct=Product(name=GalaxyS), nullValue=null)

    // nullable 변수에 접근할 때는 반드스 '?.'로 접근해야한다.
    //println(company.nullValue.length)   // compile error O, nullValue가 nullable 객체일 경우
    println(company.nullValue?.length)          // null, length로 접근하기 전에 null값 반환
    println(company.representativeProduct.name) // GalaxyS

    // '?: {default value}' 를 통해 null일 경우 반환값 지정 가능
    val nullString: String? = null
    println(nullString?.length ?: 100)  // 100

    // Exercise
    println((1..5).sumOf{id->salaryById(id)})   // 64
}

fun describeString(maybeString: String?): String {
    if(maybeString!=null && maybeString.length>0){
        return "Length of string = ${maybeString.length}"
    }
    return "Empty or null string"
}

fun lengthString(maybeString: String?): Int? = maybeString?.length

data class Product(val name:String){}
data class Company(val name:String, var representativeProduct: Product, var nullValue:String?){}

// Exercise
data class Employee3(val name:String, var salary: Int)

fun employeeById(id: Int) = when(id){
    1->Employee3("Mary",20)
    2-> null
    3->Employee3("John",21)
    4->Employee3("Ann",23)
    else -> null
}

fun salaryById(id: Int): Int = employeeById(id)?.salary ?: 0