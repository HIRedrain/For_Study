/*
* 문제 풀이로 완성하는 알고리즘 + 자료 구조 - 77가지 핵심 기법
* 2장 누적합
* 최초 작성 일자 : 2025.09.30
*
* ========================================================
* 프로그램 수정 / 보완 이력
* ========================================================
* 날짜        수정 / 보완 내용
* ========================================================
* 2025.09.30  최초 작성 : a06 작성
* 2025.10.01  a07 관련 함수 작성
* 2025.10.01  a08
* ========================================================
*/


package for_study.algo77book


class Chap2 {

    // A06. 1차원 누적 합 (1) - 실행 시간 제한 1초, 난이도 별 둘
    // 어떤 유원지에서 N 일에 걸쳐서 이벤트를 개최했고, i 일차에 Ai 명이 방문했습니다. 다음 Q 개의 질문에 답하는 프로그램을 작성하십시오.
    // 질문 1 : L1 일차부터 R1 일차까지의 총 방문자 수는?
    // 질문 Q : LQ 일차부터 RQ 일차까지의 총 방문자 수는?
    // 입력 : N Q \n A1 A2 ... AN \n L1 R1 ... LQ RQ
    // 출력 : L1 R1 에 대한 값 \n L2 R2 에 대한 값 ... \n LQ RQ 에 대한 값
    // 제약 : 1 <= N, Q <= 100000, 1 <= Ai <= 10000, 1 <= Lj <= Rj <= N
    fun a06(): ArrayList<Int> {
        var input = readln().split(" ")
        val NQ = input.map { it.toInt() }
        val Q = NQ[1]


        input = readln().split(" ")
        val As = input.map { it.toInt() }
        val APrefixSum: ArrayList<Int> = arrayListOf(0) // index 1 부터 사용
        var prefixSum = 0
        for (a in As) {
            prefixSum += a
            APrefixSum.add(prefixSum)
        }

        // List : 인터페이스, ㅇ읽기 전용 (immutable) => 데이터 추가, 삭제 x (if 데이터 추가, 삭제 원함 => MutableList 사용)
        // ArrayList : 클래스, 가변성 (mutable) => 데이터 추가, 삭제 o


        val Ls = ArrayList<Int>()
        val Qs = ArrayList<Int>()
        val answer = ArrayList<Int>()
        for (i in 0 until Q) {
            input = readln().split(" ")
            val LQ = input.map { it.toInt() }
            Ls.add(LQ[0])
            Qs.add(LQ[1])

            val cal = APrefixSum[LQ[1]] - APrefixSum[LQ[0] - 1]
            answer.add(cal)
            println(cal)
        }

        return answer
    }



    // A07. 1차원 누적 합 (2) - 실행 시간 제한 1초, 난이도 별 셋
    // 어떤 회사에서 D 일에 걸쳐 이벤트를 개최했으며, N 명이 출석했습니다. 참가자 i (1, ..., N) 는 Li 일차부터 Ri 일차까지 출석할 예정입니다. 날짜별 출석자 수를 출력하는 프로그램을 작성하십시오.
    // 입력 : D \n N \n L1 R1 \n ... \n LN RN
    // 출력 : D 행으로 출력하십시오. d 번째 행에는 d 일차의 출석자 수를 출력하십시오.
    // 제약 : 1 <= D, N <= 100000, 1 <= Li <= Ri <= D
    fun a07_1(): ArrayList<Int> { // 각 일자에 직접 참석자 수 증가 처리하여 기록 - 일일이 헤아린다. <=> D, N 값이 커지면 그만큼 오래 걸림 : 비효율
        val D = readln().toInt()
        val N = readln().toInt()
        val answer: ArrayList<Int> = arrayListOf(0)
        for (i in 0 until D) {
            answer.add(0)
        }


        val Ls = ArrayList<Int>()
        val Rs = ArrayList<Int>()

        for (i in 0 until N) {
            val input = readln().split(" ").map { it.toInt() }
            Ls.add(input[0])
            Rs.add(input[1])

            // 좋은 알고리즘 x
            for (j in input[0] until input[1] + 1) {
                answer[j] += 1 // 참석자 수 1 증가
            }
        }

        for (i in 1 until D + 1) {
            println(answer[i])
        }

        return answer
    }

    fun a07_2(): ArrayList<Int> { // 전일 대비 출석자 수 기록하여 측정 : D, N 값이 커지면 이게 더 빨라 <=> a07_1보다 성능 더 좋음
        val D = readln().toInt()
        val N = readln().toInt()
        val check: ArrayList<Int> = arrayListOf(0)
        for (i in 0 until D) {
            check.add(0)
        }


        val Ls = ArrayList<Int>()
        val Rs = ArrayList<Int>()

        for (i in 0 until N) {
            val input = readln().split(" ").map { it.toInt() }
            Ls.add(input[0])
            Rs.add(input[1])

            check[input[0]] += 1 // 참석자 수 1 증가

            if (input[1] == D) {
                // Ri 값이 D <=> 마지막 날까지 참석한다 <=> 차감 따로 안 해도 됨
                continue
            }
            else {
                check[input[1] + 1] -= 1 // 참석자 수 1 감소
            }
        }

        val answer = ArrayList<Int>()
        var count = 0
        for (i in 1 until D + 1) {
            count += check[i]
            answer.add(count)
            println(count)
        }

        return answer
    }

    fun a07_random_maker(D: Int, N: Int): String { // D, N 에 따른 Li, Ri 값 생성
        var answer = "$D\n$N"

        val random = java.util.Random()
        for (i in 0 until N) {
            // [1, D] 난수 생성 - random.nextInt(n) + offset => [offset, n + offset - 1]
            val start = random.nextInt(D) + 1
            val end = random.nextInt(D - start + 1) + start
            answer += "\n$start $end"
        }

        return answer
    }



    // A08. 2차원 누적 합 (1) - 실행 시간 제한 5초, 난이도 별 넷
    // H x W 칸 있습니다. 위부터 i 번째 행, 왼쪽부터 j 번째 열에 있는 칸 (i, j) 에는 정수 Xij 가 쓰여 있습니다. 질문 Q 개에 대한 답변을 출력하는 프로그램을 작성하시오.
    // 질문 1 : 왼쪽 위 (A1, B1), 오른쪽 아래 (C1, D1) 로 이뤄진 사각형 영역에 쓰인 정수의 총합은?
    // 질문 Q : 왼쪽 위 (AQ, BQ), 오른쪽 아래 (CQ, DQ) 로 이뤄진 사각형 영역에 스인 정수의 총합은?
    // 입력 : H W \n X11 X12 ... X1W \n XH1 XH2 ... XHW \n Q \n A1 B1 C1 D1\n ... \n AQ BQ CQ DQ
    // 출력 : Q행으로 출력하십시오. i 번째 행에는 질문 i의 답을 출력하십시오.
    // 제약 : 1 <= H, W <= 1500, 1 <= Q <= 100000, 0 <= Xij <= 9, 1 <= Ai <= Di <= H, 1 <= Bi <= Di <= W
    fun a08(): ArrayList<Int> { // 조금 느린 애
        val rHcW = readln().split(" ").map { it.toInt() }
        val mtrxHW = Array(rHcW[0] + 1) { IntArray(rHcW[1] + 1) } // 2차원 배열 생성 (0, 0) 이 아닌 (1, 1) 부터 시작점을 잡아서 그렇게 쓸 수 있게 만듦
        for (rh in 1 ..rHcW[0]) { // i in 0 until n : [0, n) , i in 0 .. n : [0, n]
            var count = 0
            val input = readln().split(" ").map { it.toInt() }
            for (cw in 1 .. rHcW[1]) {
                mtrxHW[rh][cw] = input[count++]
            }
        }

        val Q = readln().toInt()
        val As = ArrayList<Int>() // 기록용
        val Bs = ArrayList<Int>() // 기록용
        val Cs = ArrayList<Int>() // 기록용
        val Ds = ArrayList<Int>() // 기록용
        val answer = ArrayList<Int>()
        for (i in 0 until Q) {
            val input = readln().split(" ").map { it.toInt() }
            As.add(input[0])
            Bs.add(input[1])
            Cs.add(input[2])
            Ds.add(input[3])

            var sum = 0
            for (r in As[i] .. Cs[i]) {
                for (c in Bs[i] .. Ds[i]) {
                    sum += mtrxHW[r][c]
                }
            }
            println(sum)
            answer.add(sum)
        }

        return answer
    }
}