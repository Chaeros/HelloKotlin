package beginner.Functions

fun uppercaseString(text: String): String {
    return text.uppercase()
}

fun toSeconds(time: String): (Int)->Int=when(time){
    "hour"->{value->value*60*60}
    "minute"->{value->value*60}
    "second"->{value->value}
    else ->{value->value}
}
