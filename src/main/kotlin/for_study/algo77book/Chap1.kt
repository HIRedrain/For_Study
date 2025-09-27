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
* 2025.09.27  a03, a04 작성
* 2025.09.27  a04Back : 이진수 -> 십진수
* ========================================================
*/


package for_study.algo77book


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



    // A03. 완전 탐색 (2) - 실행 시간 제한 1초, 난이도 별 하나
    // 빨간 카드 N 장, 각 카드에는 정수 P1, P2, ... PN 이 적혀 있다.
    // 파란 카드 N 장, 각 카드에는 정수 Q1, Q2, ... QN 이 적혀 있다.
    // 경진 씨는 빨간 카드 중에서 1장, 파란 카드 중에서 1장, 총 2장의 카드를 선택함. 이때 카드에 적힌 정수의 합계가 K가 되도록 하는 방법이 존재하는지 확인하시오.
    // 입력 : N K \n P1, P2, ..., PN \n Q1, Q2, ..., QN
    // 출력 : Yes (가능), No (불가능)
    // 제약 : 1 <= N, K, P1, ..., PN, O1, ..., ON <= 100, 정수
    fun a03(): Boolean {
        val before = System.nanoTime()
        var input = readln()
        var numStr = input.split(" ")
        val NK = numStr.map { it.toInt() }
        val K = NK[1]

        input = readln()
        numStr = input.split(" ")
        val redPList = numStr.map { it.toInt() }

        input = readln()
        numStr = input.split(" ")
        val blueQList = numStr.map { it.toInt() }

        println("입력 값 (N K) : ${NK[0]} $K")
        println("입력 값 (P1, ..., PN) : $redPList")
        println("입력 값 (Q1, ..., QN) : $blueQList")

        var answer: Boolean = false
        for (r in redPList) {
            for (b in blueQList) {
                if ((r + b) == K) {
                    answer = true
                    println("Yes")

                    break
                }
            }
        }

        if (!answer) {
            println("No")
        }

        val after = System.nanoTime()
        val a03Time = (after - before) / 1000000 // ns -> ms
        println("실행 시간 : $a03Time ms")

        return answer
    }



    // A04. 2진법 - 실행 시간 제한 1초, 난이도 별 둘
    // 정수 N 10진수 표기로 주어짐. N 을 2진법으로 변환한 값을 출력하는 프로그램을 작성하십시오.
    // 입력 : N
    // 출력 : 2진법으로 변환한 값을 10자리로 출력, 자릿수 부족하면 왼쪽을 0으로 채우시오.
    // 제약 : 1 <= N <= 100, N 은 정수
    fun a04(): String {
        val before = System.nanoTime()
        var N = readln().toInt()
        println("입력 값 (N) : $N")

        var binary: String = "" // 2진법 기록할 변수
        while (N > 0) {
            val binaryMod = N % 2
            binary = binaryMod.toString() + binary

            N /= 2 // N = N / 2
        }

        if (binary.length < 10) {
            binary = "0".repeat(10 - binary.length) + binary
        }

        println(binary)

        val after = System.nanoTime()
        val a04Time = (after - before) / 1000000 // ns -> ms
        println("실행 시간 : $a04Time ms")

        // 이진수 문자열을 정수로 변환할 때
        //val binaryInt = binary.toInt(2)
        //println("binaryInt : $binaryInt")

        return binary
    }

    // 응용. 2진수 -> 10진수 : 이때 이진수 8자리 이내
    fun a04Back(): Int {
        val binary = readln()
        var binaryCopy = binary
        var decimal = 0
        var cal = 1
        for (i in 0 until 8) {
            // Char.toInt() : deprecated (더 이상 사용 x - 문자 코드를 숫자로 바꾸라는 건지, 해당 문자를 숫자로 변환하라는 건지 혼란 유발)
            // => 문자 코드 : Char.code(), 숫자 : Char.digitToInt()
            val num = binaryCopy.last().digitToInt()
            decimal += num * cal
            cal *= 2

            // String.dropLast(끝에서 N 글자) : 이거 해당 문자열의 끝에서 N 글자 제거한 걸 반환함 => 따로 저장 안 하면 기존 문자열 변화 x : 코틀린 - 문자열 불변
            binaryCopy = binaryCopy.dropLast(1)
        }
        println("입력 값 : $binary, 10진수 : $decimal, 계산 후 입력 값 : $binaryCopy")

        return decimal
    }








}