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

}