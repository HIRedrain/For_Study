/*
* Coding Test - Programmers Lv.0
* Test 파일
* 최초 작성 일자 : 2025.07.06
*
* ========================================================
* 프로그램 수정 / 보완 이력
* ========================================================
* 날짜        수정 / 보완 내용
* ========================================================
* 2025.07.06  최초 작성 : 옹알이 테스트
* ========================================================
*/

package for_study.coding_test

import coding_test.Lv0
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import kotlin.test.Test

class Lv0Test {
    val lv0: Lv0 = Lv0()

    @Test
    @DisplayName("조카가 발음 가능한 옹알이 단어 개수 확인")
    fun babllingsTest() {
        // given ; data 미리 준비
        val babbling1: Array<String> = arrayOf("aya", "yee", "u", "maa", "wyeoo")
        val babbling2: Array<String> = arrayOf("ayaye", "uuuma", "ye", "yemawoo", "ayaa", "Aya")

        val expected1: Int = 1
        val expected2: Int = 2

        // when ; test 기능 수행
        val result1: Int = lv0.babbling(babbling1)
        val result2: Int = lv0.babbling(babbling2)

        // then ; 실제 결과 확인
        assertEquals(expected1, result2)

    }
}