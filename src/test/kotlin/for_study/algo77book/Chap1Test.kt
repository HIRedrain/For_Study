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
* 2025.09.27  a01, a02 테스트 코드 작성
* 2025.09.27  a03, a04 테스트 코드 작성
* ========================================================
*/

package for_study.algo77book

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import java.io.ByteArrayInputStream

class Chap1Test {
    val chap1 = Chap1()

    @Test
    @DisplayName("A01_ - 정사각형 넓이 출력")
    @Disabled
    fun a01_() {
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
        val result1 = chap1.a01_(n1)
//        val result2 = chap1.a01(n2)
//        val result3 = chap1.a01(n3)
//        val result4 = chap1.a01(n4)

        // then
        assertEquals(expected1, result1)
//        assertEquals(expected2, result2)
//        assertEquals(expected3, result3)
//        assertEquals(expected4, result4)
    }

    @Test
    @DisplayName("A01 - 정사각형 넓이 출력")
    fun a01() {
        // given
        val input = "5"
        System.setIn(ByteArrayInputStream(input.toByteArray()))

        // 예상 값
        val expected = 5 * 5

        // when
        val result = chap1.a01()

        // then
        assertEquals(expected, result)
    }



    @Test
    @DisplayName("A02 - X 포함")
    fun a02true() {
        // given
        val input = "5 29\n3 9 20 29 43"
        System.setIn(ByteArrayInputStream(input.toByteArray())) // 표준 입력을 input으로 변경

        // System.setIn(ByteArrayInputStream(input.toByteArray())) : 설명
        // 1. input.toByteArray() : input 문자열 -> Byte 배열로 변환 : InputStream - 바이트 단위로 데이터 읽음
        // 2. ByteArrayInputStream() : 앞서 변경한 바이트 배열 기반으로 입력 스트림 (InputStream) 생성 : 내가 원하는 문자열을 입력으로 읽을 수 있게
        // 3. System.setIn() : JVM 의 표준 입력 (System.in) 을 내가 만든 ByteArrayInputStream 으로 변경
        // 그 결과, readLine(), readln() 호출 => 키보드로 입력하는 대신 미리 설정해 둔 문자열을 입력으로 읽음


        // 예상 값
        val expected: Boolean = true

        // when
        val result = chap1.a02()

        // then
        assertEquals(expected, result)
    }

    @Test
    @DisplayName("A02 - X 미포함")
    fun a02false() {
        // given
        val input = "5 12\n3 9 20 29 43"
        System.setIn(ByteArrayInputStream(input.toByteArray())) // 표준 입력을 input으로 변경

        // System.setIn(ByteArrayInputStream(input.toByteArray())) : 설명
        // 1. input.toByteArray() : input 문자열 -> Byte 배열로 변환 : InputStream - 바이트 단위로 데이터 읽음
        // 2. ByteArrayInputStream() : 앞서 변경한 바이트 배열 기반으로 입력 스트림 (InputStream) 생성 : 내가 원하는 문자열을 입력으로 읽을 수 있게
        // 3. System.setIn() : JVM 의 표준 입력 (System.in) 을 내가 만든 ByteArrayInputStream 으로 변경
        // 그 결과, readLine(), readln() 호출 => 키보드로 입력하는 대신 미리 설정해 둔 문자열을 입력으로 읽음


        // 예상 값
        val expected: Boolean = false

        // when
        val result = chap1.a02()

        // then
        assertEquals(expected, result)
    }



    @Test
    @DisplayName("A03 - 카드 합계 K 가능")
    fun a03true() {
        // given
        val input = "7 43\n3 9 20 29 39 43 85\n5 1 7 12 14 11 91"
        System.setIn(ByteArrayInputStream(input.toByteArray()))

        // 예상 값
        val expected = true

        // when
        val result = chap1.a03()

        // then
        assertEquals(expected, result)
    }

    @Test
    @DisplayName("A03 - 카드 합계 K 불가능")
    fun a03false() {
        // given
        val input = "7 91\n3 9 20 29 39 85\n92 93 94 95 96 97 98"
        System.setIn(ByteArrayInputStream(input.toByteArray()))

        // 예상 값
        val expected = false

        // when
        val result = chap1.a03()

        // then
        assertEquals(expected, result)
    }



    @Test
    @DisplayName("A04 - 이진수 출력")
    fun a04() {
        // given
        val input = "43"
        System.setIn(ByteArrayInputStream(input.toByteArray()))

        // 예상 값
        val expected = "0000101011"

        // when
        val result = chap1.a04()

        // then
        assertEquals(expected, result)
    }
}