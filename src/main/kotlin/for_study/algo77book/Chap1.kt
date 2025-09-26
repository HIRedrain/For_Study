/*
* 문제 풀이로 완성하는 알고리즘 + 자료 구조 - 77가지 핵심 기법
* 1장 알고리즘과 계산량
* 최초 작성 일자 : 2025.09.26
*
* ========================================================
* 프로그램 수정 / 보완 이력
* ========================================================
* 날짜        수정 / 보완 내용
* ========================================================
* 2025.09.26  최초 작성 : a01 작성
* ========================================================
*/


package for_study.algo77book

import kotlin.math.pow


class Chap1 {

    // A01. 실행 시간 제한 1초, 난이도 별 하나
    // 정수 N 이 주어졌을 때 한 변의 길이가 N인 정사각형의 넓이를 출력하는 프로그램을 작성하십시오.
    // 입력 : N
    // 출력 : 정사각형 넓이를 정수로 출력
    // 제약 : 1 <= N <= 100, N 은 정수
    fun a01(n: Int): Int {
        val before = System.nanoTime()
//        print("정사각형 한 변 길이 : ")
        val N = readLine()?.toInt()
        println("입력 값 : $N")
        if (N == null) {
            println("입력 값 == null")
        }
        else if ((N > 0) && (N < 101)) {
            println("정사각형 한 변의 길이 : $N, 넓이 : ${N * N}")
        }
        else {
            println("제약 조건 충족 x")
        }
        val after = System.nanoTime()
        val a01Time = (after - before) / 1000000 // ns -> ms
        println("출력 시간 : $a01Time ms")



        // 테스트 코드용~
        val result: Int = n * n

//        val nDouble: Double = N.toDouble()
//        val result2: Double = nDouble.pow(2)

        println("정사각형 한 변의 길이 : $n, 넓이 : $result")
//        println("Double 함수 사용 : $result2")

        return result
    }


}