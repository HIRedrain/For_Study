/*
* 문제 풀이로 완성하는 알고리즘 + 자료 구조 - 77가지 핵심 기법
* 2장 누적합 - 테스트 코드
* 최초 작성 일자 : 2025.10.01
*
* ========================================================
* 프로그램 수정 / 보완 이력
* ========================================================
* 날짜        수정 / 보완 내용
* ========================================================
* 2025.10.01  최초 작성 : a06 테스트 함수 작성
* 2025.10.01  a07 관련 테스트 함수 작성
* 2025.10.01  a08
* 2025.10.03  a08_answer
* 2025.10.08  a09
* 2025.10.13  a09 test case 추가
* ========================================================
*/



package for_study.algo77book

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import java.io.ByteArrayInputStream

class Chap2Test {
    val chap2 = Chap2()

    @Test
    @DisplayName("A06 - 1차원 누적 합 (1)")
    fun a06() {
        // given
        val input = "15 3\n62 65 41 13 20 11 18 44 53 12 18 17 14 10 39\n4 13\n3 10\n2 15"
        System.setIn(ByteArrayInputStream(input.toByteArray()))

        // 예상 값
        val expected: ArrayList<Int> = arrayListOf(220, 212, 375)

        // when
        val result = chap2.a06()

        // then
        assertEquals(expected, result)

    }



    @Test
    @DisplayName("A07 - 1차원 누적 합 (2) - 8일 5명")
    fun a07() {
        // given
        val input = "8\n5\n2 3\n3 6\n5 7\n3 7\n1 5"
        System.setIn(ByteArrayInputStream(input.toByteArray()))

        // 예상 값
        val expected1: ArrayList<Int> = arrayListOf(0, 1, 2, 4, 3, 4, 3, 2, 0)
        val expected2: ArrayList<Int> = arrayListOf(1, 2, 4, 3, 4, 3, 2, 0)


        // when
        var start = System.nanoTime()
        val result1 = chap2.a07_1()
        var after = System.nanoTime()
        val a07Time_1 = (after - start) / 1000000 // ns -> ms

        System.setIn(ByteArrayInputStream(input.toByteArray())) // given - for _2
        start = System.nanoTime()
        val result2 = chap2.a07_2()
        after = System.nanoTime()
        val a07Time_2 = (after - start) / 1000000


        println("a07_1Time : ${a07Time_1}ms, a07_2Time : ${a07Time_2}ms")
        println("result1 : $result1") // 0ms
        println("result2 : $result2}") // 0ms

        // then
        assertEquals(expected1, result1)
        assertEquals(expected2, result2)
    }

    @Test
    @DisplayName("A07 - 1차원 누적 합 (2) - 1000명 2000명")
    fun a07_1000_2000() {
        // given
        val input = chap2.a07_random_maker(1000, 2000)
        println(input)
        System.setIn(ByteArrayInputStream(input.toByteArray()))

        // when
        var start = System.nanoTime()
        val result1 = chap2.a07_1()
        var after = System.nanoTime()
        val a07Time_1 = (after - start) / 1000000 // ns -> ms


        System.setIn(ByteArrayInputStream(input.toByteArray())) // given - for _2
        start = System.nanoTime()
        val result2 = chap2.a07_2()
        after = System.nanoTime()
        val a07Time_2 = (after - start) / 1000000


        println("a07_1Time : ${a07Time_1}ms, a07_2Time : ${a07Time_2}ms")
        println("result1 : $result1") // 29ms
        println("result2 : $result2}") // 3ms
    }

    @Test
    @DisplayName("A07 - 1차원 누적 합 (2) - 10000명 10000명")
    fun a07_10000_10000() {
        // given
        val input = chap2.a07_random_maker(10000, 10000)
        System.setIn(ByteArrayInputStream(input.toByteArray()))

        // when
        var start = System.nanoTime()
        val result1 = chap2.a07_1()
        var after = System.nanoTime()
        val a07Time_1 = (after - start) / 1000000 // ns -> ms


        System.setIn(ByteArrayInputStream(input.toByteArray())) // given - for _2
        start = System.nanoTime()
        val result2 = chap2.a07_2()
        after = System.nanoTime()
        val a07Time_2 = (after - start) / 1000000


        println("a07_1Time : ${a07Time_1}ms, a07_2Time : ${a07Time_2}ms")
        println("result1 : $result1") // 90ms
        println("result2 : $result2}") // 18ms
    }



    @Test
    @DisplayName("A08 - 2차원 누적 합 (1) - 내 것")
    fun a08() {
        // given
        val input = "5 5\n2 0 0 5 1\n1 0 3 0 0\n0 8 5 0 2\n4 1 0 0 6\n0 9 2 7 0\n2\n2 2 4 5\n1 1 5 5"
        System.setIn(ByteArrayInputStream(input.toByteArray()))

        // 예상 값
        val expected = arrayListOf(25, 56)

        // when
        val result = chap2.a08()

        // then
        assertEquals(expected, result)
    }

    @Test
    @DisplayName("A08 - 2차원 누적 합 (1) - 정답")
    fun a08_answer() {
        // given
        val input = "5 5\n2 0 0 5 1\n1 0 3 0 0\n0 8 5 0 2\n4 1 0 0 6\n0 9 2 7 0\n2\n2 2 4 5\n1 1 5 5"
        System.setIn(ByteArrayInputStream(input.toByteArray()))

        // 예상 값
        val expected = arrayListOf(25, 56)

        // when
        val result = chap2.a08_answer()

        // then
        assertEquals(expected, result)
    }



    @Test
    @DisplayName("A09 - 2차원 누적 합 (2)")
    fun a09() {

        // test case 1
        // given
        var input = "5 5 2\n1 1 3 3\n2 2 4 4"
        System.setIn(ByteArrayInputStream(input.toByteArray()))

        // 예상 값
        val expected1 = arrayOf(
            intArrayOf(0, 0, 0, 0, 0, 0),
            intArrayOf(0, 1, 1, 1, 0, 0),
            intArrayOf(0, 1, 2, 2, 1, 0),
            intArrayOf(0, 1, 2, 2, 1, 0),
            intArrayOf(0, 0, 1, 1, 1, 0),
            intArrayOf(0, 0, 0, 0, 0, 0)
        )

        // when
        val result1 = chap2.a09()


        // test case 2
        // given
        input = "5 5 5\n1 1 3 3\n2 2 5 5\n1 3 2 5\n4 1 5 2\n3 3 4 4"
        System.setIn(ByteArrayInputStream(input.toByteArray()))

        // 예상 값
        val expected2 = arrayOf(
            intArrayOf(0, 0, 0, 0, 0, 0),
            intArrayOf(0, 1, 1, 2, 1, 1),
            intArrayOf(0, 1, 2, 3, 2, 2),
            intArrayOf(0, 1, 2, 3, 2, 1),
            intArrayOf(0, 1, 2, 2, 2, 1),
            intArrayOf(0, 1, 2, 1, 1, 1),
        )

        // when
        val result2 = chap2.a09()



        // then
        // assertEquals() 사용 시 참조 값이 달라서 테스트 실패 : 코틀린 - Array<IntArray> : 참조 기반 => 내부 값 일치해도 객체 참조 다를 시 실패
        // => 중첩 배열 시 contentDeepEquals 사용하여 내부 값 비교
        assertTrue(expected1.contentDeepEquals(result1))
        assertTrue(expected2.contentDeepEquals(result2))

    }


}