/*
* 문제 풀이로 완성하는 알고리즘 + 자료 구조 - 77가지 핵심 기법
* 1장 알고리즘과 계산량 - 테스트 코드
* 최초 작성 일자 : 2025.09.26
*
* ========================================================
* 프로그램 수정 / 보완 이력
* ========================================================
* 날짜        수정 / 보완 내용
* ========================================================
* 2025.09.26  최초 작성 : a01 테스트 코드 작성
* ========================================================
*/

package for_study.algo77book

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import java.io.ByteArrayInputStream

class Chap1Test {
    val chap1 = Chap1()

    @Test
    @DisplayName("A01 - 정사각형 넓이 출력")
    fun a01() {
        // given
        val n1 = 1
//        val n2 = 2
//        val n3 = 3
//        val n4 = 4

        val input1 = "1\n"
        System.setIn(ByteArrayInputStream(input1.toByteArray())) // 미리 입력

        // 예상값
        val expected1 = n1 * n1
//        val expected2 = n2 * n2
//        val expected3 = n3 * n3
//        val expected4 = n4 * n4

        // when
        val result1 = chap1.a01(n1)
//        val result2 = chap1.a01(n2)
//        val result3 = chap1.a01(n3)
//        val result4 = chap1.a01(n4)

        // then
        assertEquals(expected1, result1)
//        assertEquals(expected2, result2)
//        assertEquals(expected3, result3)
//        assertEquals(expected4, result4)
    }
}