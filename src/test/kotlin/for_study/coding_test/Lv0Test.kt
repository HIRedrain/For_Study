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
* 2025.07.07  나선형 행렬
* ========================================================
*/

package for_study.coding_test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
//import kotlin.test.Test

class Lv0Test {
    val lv0: Lv0 = Lv0()

    @Test
    @DisplayName("조카가 발음 가능한 옹알이 단어 개수 확인")
    @Disabled
    fun babllingsTest() {
        // given ; data 미리 준비
        val babbling1: Array<String> = arrayOf("aya", "yee", "u", "maa", "wyeoo")
        val babbling2: Array<String> = arrayOf("ayaye", "uuuma", "ye", "yemawoo", "ayaa", "Aya")

        // 예상 값 (정답)
        val expected1: Int = 1
        val expected2: Int = 3

        // when ; test 기능 수행
        val result1: Int = lv0.babbling(babbling1)
        val result2: Int = lv0.babbling(babbling2)

        // then ; 실제 결과 확인
        assertEquals(expected1, result1)
        assertEquals(expected2, result2)
    }

    @Test
    @DisplayName("나선형 행렬")
    fun spiralTest() {
        // given ; data 미리 준비
        val n5 = 5
        val n2 = 2
        val n0 = 0 // 안 되는 애
        val n31 = 31 // 안 되는 애

        // 예상 값 (정답)
        val expectedN5: Array<IntArray> = arrayOf(
            intArrayOf(1, 2, 3, 4, 5),
            intArrayOf(16, 17, 18, 19, 6),
            intArrayOf(15, 24, 25, 20, 7),
            intArrayOf(14, 23, 22, 21, 8),
            intArrayOf(13, 12, 11, 10, 9)
        )
        val expectedN2: Array<IntArray> = arrayOf(
            intArrayOf(1, 2),
            intArrayOf(4, 3)
        )

        // when ; test 기능 수행
        val resultN5 = lv0.spiral(n5)
        val resultN2 = lv0.spiral(n2)
        val resultN0 = lv0.spiral(n0)
        val resultN31 = lv0.spiral(n31)

        // then ; 실제 결과 확인
        assertArrayEquals(expectedN5, resultN5)
        assertArrayEquals(expectedN2, resultN2)
        assertArrayEquals(arrayOf(), resultN0)
        assertArrayEquals(arrayOf(), resultN31)
    }
}