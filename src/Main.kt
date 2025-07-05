
import coding_test.Lv0

fun main() {
    println("Hello World!")

    // 선언
    val lv0: Lv0 = Lv0()


    // Lv0 - 옹알이
    //
    val babbling1: Array<String> = arrayOf("aya", "yee", "u", "maa", "wyeoo")
    val babbling2: Array<String> = arrayOf("ayaye", "uuuma", "ye", "yemawoo", "ayaa", "Aya")
    println("lv0.babbling(babbling1) : " + lv0.babbling(babbling1))
    println("lv0.babbling(babbling2) : " + lv0.babbling(babbling1))
}