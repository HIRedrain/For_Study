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
* 2025.10.01  최초 작성 : a06 작성
* ========================================================
*/



package for_study.algo77book

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
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
}