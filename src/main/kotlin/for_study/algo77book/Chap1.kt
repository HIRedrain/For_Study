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
* 2025.09.27  a02 작성, a01 수정
* ========================================================
*/


package for_study.algo77book

import kotlin.math.pow


class Chap1 {

    // A01. 도입 문제 - 실행 시간 제한 1초, 난이도 별 하나
    // 정수 N 이 주어졌을 때 한 변의 길이가 N인 정사각형의 넓이를 출력하는 프로그램을 작성하십시오.
    // 입력 : N
    // 출력 : 정사각형 넓이를 정수로 출력
    // 제약 : 1 <= N <= 100, N 은 정수
    fun a01_(n: Int): Int {
        val before = System.nanoTime()
//        print("정사각형 한 변 길이 : ")
        val N = readLine()?.toInt() // nullable
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

    fun a01(): Int {
        val before = System.nanoTime()
        val N = readln().toInt() // non-nullable
        println("입력 값 : $N")

        val result = N * N
        println("정사각형 한 변의 길이 : $N, 넓이 : $result")
        val after = System.nanoTime()
        val a01Time = (after - before) / 1000000 // ns -> ms
        println("출력 시간 : $a01Time ms")

        return result
    }



    // A02. 완전 탐색 (1) - 실행 시간 제한 1초, 난이도 별 하나
    // N 개의 정수 A1, A2, ..., AN 안에 정수 X 가 포함돼 있는지 판정하는 프로그램을 작성하시오.
    // 입력 : N X \n A1 A2 ... AN
    // 출력 : Yes (X 포함), No (X 미포함)
    // 제약 : 1 <= N, X, A1, ..., AN <= 100, 정수
    fun a02(): Boolean {
        val before = System.nanoTime()
        var input = readln() // non-nullable
        var numStr = input.split(" ") // " " 기준 분리
        val NX = numStr.map { it.toInt() } // map : 요소 호출 => Int 로 바꿔서 저장
        val X = NX[1]

        input = readln()
        numStr = input.split(" ")
        val AList = numStr.map { it.toInt() }

        println("입력 값 (N X) : ${NX[0]} $X")
        println("입력 값 (A1, ..., AN) : $AList")

        var answer: Boolean = false
        for (a in AList) {
            if (a == X) {
                // A1, ..., AN 정수 중 X 값 포함
                answer = true
                println("Yes")

                break
            }
        }

        if (!answer) {
            // 미포함
            println("No")
        }

        val after = System.nanoTime()
        val a02Time = (after - before) / 1000000 // ns -> ms
        println("출력 시간 : $a02Time ms")

        return answer
    }





}