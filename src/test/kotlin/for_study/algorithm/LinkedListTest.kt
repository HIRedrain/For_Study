/*
* Algorithm - LinkedList
* Test 파일
* 최초 작성 일자 : 2025.07.07
*
* ========================================================
* 프로그램 수정 / 보완 이력
* ========================================================
* 날짜        수정 / 보완 내용
* ========================================================
* 2025.07.13  최초 작성 : 연결 리스트 전체 기능 테스트
* ========================================================
*/


package for_study.algorithm

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class LinkedListTest {

    @Test
    @DisplayName("Linked List 전체 기능 테스트")
    fun linkedListTotalTest(){
        val DLLStr: LinkedList<String> = LinkedList<String>()
        DLLStr.insertBack("E")
        DLLStr.insertBack("L")
        DLLStr.insertBack("L")
        DLLStr.insertBack("O")
        DLLStr.insertFront("H")
        DLLStr.insertBack("!")

        print("DLLStr : ")
        DLLStr.print()

        println("Remove : ")
        for (i in 0 until DLLStr.getCount()) {
            when (i) {
                0 -> println("$i - DLLStr.removeFront() : ${DLLStr.removeFront()}")
                1 -> println("$i - DLLStr.removeBack() : ${DLLStr.removeBack()}")
                2 -> println("$i - DLLStr.removeUsingData('O') : ${DLLStr.removeUsingData("O")}")
                else -> println("$i - DLLStr.removeBack() : ${DLLStr.removeBack()}")
            }
        }

        print("DLLStr : ")
        DLLStr.print()
    }

}