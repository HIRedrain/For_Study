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
* 2025.07.29  평행 테스트
* 2025.08.03  겹치는 선분 길이 테스트
* 2025.08.08  겹치는 선분 길이 테스트 데이터 추가 작성
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
    @Disabled
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

    @Test
    @DisplayName("평행 여부 판단")
    @Disabled
    fun parallel() {
        // given ; data 미리 준비
        val coordinate1: Array<IntArray> = arrayOf(
            intArrayOf(1, 4),
            intArrayOf(9, 2),
            intArrayOf(3, 8),
            intArrayOf(11, 6)
        )
        val coordinate2: Array<IntArray> = arrayOf(
            intArrayOf(3, 5),
            intArrayOf(4, 1),
            intArrayOf(2, 4),
            intArrayOf(5, 10)
        )

        // 예상 값
        val expected1 = 1
        val expected2 = 0

        // when ; test 기능 수행
        val result1 = lv0.parallel(coordinate1)
        val result2 = lv0.parallel(coordinate2)

        // then ; 실제 결과 확인
        // println("result1 = ${result1}, expected1 = $expected1")
        // println("result2 = ${result2}, expected2 = $expected2")
        assertEquals(expected1, result1)
        assertEquals(expected2, result2)


    }

    @Test
    @DisplayName("겹치는 선분 길이 측정")
    fun measureLineLength() {
        // given ; data 미리 준비
        val lines1: Array<IntArray> = arrayOf(
            intArrayOf(0, 1),
            intArrayOf(2, 5),
            intArrayOf(3, 9)
        )
        val lines2: Array<IntArray> = arrayOf(
            intArrayOf(-1, 1),
            intArrayOf(1, 3),
            intArrayOf(3, 9)
        )
        val lines3: Array<IntArray> = arrayOf(
            intArrayOf(0, 5),
            intArrayOf(3, 9),
            intArrayOf(1, 10)
        )
        val lines4: Array<IntArray> = arrayOf(
            intArrayOf(-7, 3),
            intArrayOf(-2, 5),
            intArrayOf(2, 7),
        )
        val lines5: Array<IntArray> = arrayOf(
            intArrayOf(5, 8),
            intArrayOf(2, 6),
            intArrayOf(1, 4)
        )
        val lines6: Array<IntArray> = arrayOf(
            intArrayOf(0, 2),
            intArrayOf(4, 6),
            intArrayOf(2, 4)
        )
        val lines7: Array<IntArray> = arrayOf(
            intArrayOf(0, 10),
            intArrayOf(4, 6),
            intArrayOf(2, 8)
        )
        val lines8: Array<IntArray> = arrayOf(
            intArrayOf(1, 5),
            intArrayOf(3, 7),
            intArrayOf(2, 6)
        )
        val lines9: Array<IntArray> = arrayOf(
            intArrayOf(0, 100),
            intArrayOf(75, 125),
            intArrayOf(50, 150)
        ) // 제약 조건에 따라 있을 수 없는 일이지만, 확인용으로 추가함.

        // 예상 값
        val expected1 = 2
        val expected2 = 0
        val expected3 = 8
        val expected4 = 7
        val expected5 = 3
        val expected6 = 0
        val expected7 = 6
        val expected8 = 4
        val expected9 = 75

        // when ; test 기능 수행
        val result1 = lv0.MeasureLineLength(lines1)
        val result2 = lv0.MeasureLineLength(lines2)
        val result3 = lv0.MeasureLineLength(lines3)
        val result4 = lv0.MeasureLineLength(lines4)
        val result5 = lv0.MeasureLineLength(lines5)
        val result6 = lv0.MeasureLineLength(lines6)
        val result7 = lv0.MeasureLineLength(lines7)
        val result8 = lv0.MeasureLineLength(lines8)
        val result9 = lv0.MeasureLineLength(lines9)

        // then ; 실제 결과 확인
        println("result1 = $result1, result2 = $result2, result3 = $result3, result4 = $result4")
        println("result5 = $result5, result6 = $result6, result7 = $result7, result8 = $result8. result9 = $result9")
        assertEquals(expected1, result1)
        assertEquals(expected2, result2)
        assertEquals(expected3, result3)
        assertEquals(expected4, result4)
        assertEquals(expected5, result5)
        assertEquals(expected6, result6)
        assertEquals(expected7, result7)
        assertEquals(expected8, result8)
        assertEquals(expected9, result9)

    }
}