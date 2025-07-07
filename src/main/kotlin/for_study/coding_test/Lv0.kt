/*
* Coding Test - Programmers Lv.0
* 문제 해결, 풀이 기록
* 최초 작성 일자 : 2025.07.04
*
* ========================================================
* 프로그램 수정 / 보완 이력
* ========================================================
* 날짜        수정 / 보완 내용
* ========================================================
* 2025.07.04  최초 작성 : 옹알이
* 2025.07.05  옹알이 수정 + 자동화 테스트 도입 중
* 2025.07.06  babbling() - return 0 => return answer 수정
* 2025.07.07  나선형 행렬 출력
* ========================================================
*/

package for_study.coding_test

class Lv0 {

    // 2025.07.04 - babbling
    // https://school.programmers.co.kr/learn/courses/30/lessons/120956?language=kotlin
    // 머쓱이 조카 : 6개월 - "aya", "ye", "woo", "ma" 네 가지 발음을 최대 한 번씩 사용해 조합한(이어 붙인) 발음만 가능
    // 매개변수 : 문자열 배열 babblings
    // Q. 머쓱이 조카가 발음할 수 있는 단어 개수 반환
    fun babbling(babllings: Array<String>): Int {
        println("\uD83D\uDD25 babbling() - 옹알이 시작")

        val baby: Array<String> = arrayOf("aya", "ye", "woo", "ma") // 현재 조카가 발음할 수 있는 것
        var answer: Int = 0

        // 접근 방법
        // 1. 제약 조건 - 매개변수 babllings 의 길이 확인 (1 <= babllings <= 100)
        // 2. 제약 조건 - 1 <= babllings[i] 의 길이 <= 15 확인
        // 3. 문자열은 알파벳 소문자로 존재하는지 확인 => 아니면 소문자로 변환 처리
        // 4. babblings 의 각 문자열에서 "aya", "ye", "woo", "ma" 가 두 번 미만 등장하는지 확인
        // 5. 발음할 수 있는 소리가 포함됨 => " " 로 변경 처리 => 만약 남은 알파벳이 존재한다? 발음 못함

        // 1.
        if ((babllings.isEmpty()) || (babllings.size > 100)) {
            // 매개변수로 전달된 문자열 배열의 길이가 0보다 작거나 100보다 크다 => 할 필요 없음
            println("babbling() - 1. 제약 조건 - 매개변수 babllings 의 길이 확인 (1 <= babllings <= 100)")
            println("babbling() - babllings.size : ${babllings.size}")

            return answer
        }

        for (word in babllings) {
            // 2.
            if ((word.length < 1) || (word.length > 15)) {
                println("babbling() - 2. 제약 조건 - 1 <= babllings[i] 의 길이 <= 15 확인")
                println("babbling() - word.length : ${word.length}")

                continue
            }

            // 3.
            if (!word.all { it.isLowerCase()}) {
                println("babbling() - 3. 문자열은 알파벳 소문자로 존재하는지 확인 => 아니면 소문자로 변환 처리")
                println("babbling() - !word.all { it.isLowerCase()} : ${!word.all { it.isLowerCase()}}")

                continue
            }

            // babllings 원소 하나씩 word 에 넣어서 반복 실행
            var nowWord = word
            var can = true

            // 4.
            for (pronounce in baby) {
                if (nowWord.contains(pronounce.repeat(2))) {
                    println("babbling() - 4. babblings 의 각 문자열에서 \"aya\", \"ye\", \"woo\", \"ma\" 가 두 번 미만 등장하는지 확인")
                    println("babbling() - nowWord.contains(pronounce.repeat(2) : ${nowWord.contains(pronounce.repeat(2))}")

                    can = false

                    break;
                }
            }

            // 5.
            if (can) {
                for (pronounce in baby) {
                    nowWord = nowWord.replace(pronounce, " ")
                }

                if (nowWord.trim().isEmpty()) { // trim() : 앞뒤 공백 제거 함수
                    answer += 1 // 말할 수 있음
                }

                println("babbling() - 5. 발음할 수 있는 소리가 포함됨 => \" \" 로 변경 처리 => 만약 남은 알파벳이 존재한다? 발음 못함")
                println("babbling() - nowWord : $nowWord")
            }

        }

        println("babbling() - answer : $answer")

        return answer
    }



    // 2025.07.07 - spiral
    // https://school.programmers.co.kr/learn/courses/30/lessons/181832?language=kotlin
    // 매개변수 : 양의 정수 n (1 <= n <= 30)
    // n x n 행렬 (배열) - 1 ~ n^2 정수 [0][0]부터 시계 방향 나선형으로 배치 => 해당 이차원 배열 반환
    fun spiral(n: Int): Array<IntArray> {
        println("\uD83D\uDD25 spiral() - 나선형 행렬 시작")

        if (n !in 1 .. 30) {
            // 제약 조건 : 1 <= n <= 30
            // n 이 0 이거나 30 초과한다 => 빈 배열 반환
            println("spiral() - 1 <= n <= 31 범위 만족 x")

            return arrayOf()
        }

        // n x n 2차원 행렬
//        val answer: Array<IntArray> = arrayOf<IntArray>()
//        for (i in 0 until n) {
//            // 2차원 배열 생성
//            answer[i] = IntArray(n)
//        }
        val answer = Array(n) { IntArray(n) }
        println("spiral() - 2차원 배열 생성 : answer = ${answer.contentDeepToString()}")

        // 접근 방법
        // 이동 방향 : 우 -> 하 -> 좌 -> 상
        // answer[r][c] == 0 => 값 입력
        // answer[r][c] != 0 => 방향 변경
        val moveRow = arrayOf(0, 1, 0, -1) // 행 이동
        val moveCol = arrayOf(1, 0, -1, 0) // 열 이동

        var r = 0 // 현재 행
        var c = 0 // 현재 열
        var dir = 0 // 현재 방향 (0 : 우, 1 : 하, 2 : 좌, 3 : 상)

        for (i in 1 .. (n * n)) {
            println("spiral() - r = ${r}, c = ${c}, dir = ${dir}")

            // 행렬 원솟값 저장
            answer[r][c] = i

            if (i == (n * n)) {
                // 마지막 값까지 다 넣음 => 반복문 종료
                break
            }

            // 다음 위치 계산
            var nextR = r + moveRow[dir]
            var nextC = c + moveCol[dir]

            if ((nextR !in 0 until n) || (nextC !in 0 until n) || (answer[nextR][nextC] != 0)) {
                // r, c index 값 범위 벗어남 or answer[r][c] != 0 => 방향 전환
                println("spiral() - 방향 전환 : nextR = ${nextR}, nextC = ${nextC}")

                dir = (dir + 1) % 4 // 방향 전환
            }

            // r, c 갱신
            r += moveRow[dir]
            c += moveCol[dir]
        }

        println("spiral() - answer : ${answer.contentDeepToString()}")

        return answer
    }

}