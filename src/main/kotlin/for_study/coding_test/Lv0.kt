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
* 2025.07.29  두 직선 평행 여부 판단 함수
* 2025.08.01  겹치는 선분의 길이, parallel() - .toDouble() 처리 변경
* 2025.08.02  겹치는 선분의 길이 정리 중
* 2025.08.03  겹치는 선분의 길이 논리적 오류 수정 중 - 새로 구현 중
* ========================================================
*/

package for_study.coding_test

import kotlin.math.abs

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



    // 2025.07.29 - parallel
    // https://school.programmers.co.kr/learn/courses/30/lessons/120875?language=kotlin
    // 매개변수 : [x1, y1], [x2, y2], [x3, y3], [x4, y4] 좌표 4개
    // 주어진 좌표를 기준으로 직선 두 개 그었을 때 평행하는 두 직선 존재 1, 아니면 0
    // 평행 기준 : 기울기 동일, y절편 다름
    // 제약 조건 1. dots 의 길이 = 4
    // 제약 조건 2. dots 원소 [x, y] 형태, x, y 는 정수 - 0 <= x, y <= 100
    // 제약 조건 3. 두 직선이 겹칠 때 (일치할 때) 1 반환
    // 제약 조건 4. 임의의 두 점을 이은 직선이 x축 또는 y축과 평행한 상황 고려 x
    // 입출력 예시 1. 점 [1, 4], [3, 8] 을 잇고, 점 [9, 2], [11, 6] 을 이으면 두 선분은 평행합니다.
    // 입출력 예시 2. 점을 어떻게 연결해도 평행하지 않습니다.
    fun parallel(dots: Array<IntArray>): Int {
        println("\uD83D\uDD25 parallel() - 평행 여부 판단 시작")

        println("dots : ${dots.contentDeepToString()}")

        var answer: Int = 0

        val slope01 = (dots[1][1].toDouble() - dots[0][1].toDouble()) / (dots[1][0].toDouble() - dots[0][0].toDouble())
        val slope02 = (dots[2][1].toDouble() - dots[0][1].toDouble()) / (dots[2][0].toDouble() - dots[0][0].toDouble())
        val slope03 = (dots[3][1].toDouble() - dots[0][1].toDouble()) / (dots[3][0].toDouble() - dots[0][0].toDouble())
        val slope12 = (dots[2][1].toDouble() - dots[1][1].toDouble()) / (dots[2][0].toDouble() - dots[1][0].toDouble())
        val slope13 = (dots[3][1].toDouble() - dots[1][1].toDouble()) / (dots[3][0].toDouble() - dots[1][0].toDouble())
        val slope23 = (dots[3][1].toDouble() - dots[2][1].toDouble()) / (dots[3][0].toDouble() - dots[2][0].toDouble())

        println("slope01 : ${slope01}, slope02 : ${slope02}, slope03 : ${slope03}, slope12 : ${slope12}, slope13 : ${slope13}, slope23 : $slope23")

        if (slope01 == slope23) {
            answer = 1

            println("점 [${dots[0][0]}, ${dots[0][1]}], [${dots[1][0]}, ${dots[1][1]}] 을 잇고, 점 [${dots[2][0]}, ${dots[2][1]}], [${dots[3][0]}, ${dots[3][1]}] 을 이은 두 선분은 평행합니다.")
        }

        if (slope02 == slope13) {
            answer = 1

            println("점 [${dots[0][0]}, ${dots[0][1]}], [${dots[2][0]}, ${dots[2][1]}] 을 잇고, 점 [${dots[1][0]}, ${dots[1][1]}], [${dots[3][0]}, ${dots[3][1]}] 을 이은 두 선분은 평행합니다.")
        }

        if (slope03 == slope12) {
            answer = 1

            println("점 [${dots[0][0]}, ${dots[0][1]}], [${dots[3][0]}, ${dots[3][1]}] 을 잇고, 점 [${dots[1][0]}, ${dots[1][1]}], [${dots[2][0]}, ${dots[2][1]}] 을 이은 두 선분은 평행합니다.")
        }

        if (answer == 0) {
            println("어떤 점을 연결해도 평행하지 않습니다.")
        }

        return answer
    }



    // 2025.08.01 - MeasureLineLength
    // https://school.programmers.co.kr/learn/courses/30/lessons/120876?language=kotlin
    // 겹치는 선분의 길이 측정
    // 매개변수 : lines - [[start, end], [start, end], [start, end]]
    // 두 개 이상의 선분이 겹치는 부분의 길이 return
    // 제약 조건 1. lines 길이 = 3 : "원소 3개"
    // 제약 조건 2. lines 원소의 길이 = 2
    // 제약 조거 3. 모든 선분의 길이 >= 1
    // 제약 조건 4. lines 원소 : [a, b] 형태 (a : start, b : end)
    // 제약 조건 5. -100 <= a < b <= 100
    fun MeasureLineLength(lines: Array<IntArray>): Int {
        println("\uD83D\uDD25 MeasureLineLength() - 겹치는 선분의 길이 측정 시작")

        println("lines : ${lines.contentDeepToString()}")

        // 로직 정리 - 내 생각 : 근데 이게 반나절 생각해 보니 고려할 게 너무 많음.
        // 각 선분이 겹친다, 안 겹친다로 이렇게 저렇게 계산해 보면 고려해야 할 상황이 대략 9가지
        // 이게 과연 좋은 로직인가 생각하게 됨
        // i)s1 > s2 => e1 > e2 > s1 > s2 : 이렇게 되면 겹치는 구간 존재
        // ii) s1 < s2 => e2 > e1 > s2 > s1
        // iii) s1 < s2 < e1 < e3 < s2 < s3 : 중복 x 구간 겹침
        // iv) s1 < s2 < s3 < s2 <= e3, e1 : 중복 o 구간 겹침 (y1, y3 둘 중 누가 더 크든 상관 없음)
        // 1. x좌표 기준 오름차순 정렬 - 삽입 정렬 : 원소 3개니까 quick 정렬보다 나을 것 같아서
        // 2. 겹침 구간 측정 - 중복 발생 시 해당 구간 길이 제거 처리

        // x좌표 기준 삽입 정렬
//        for (i in 0 until lines.size) {
//            var minS = lines[i][0]
//            var minIndex = i
//
//            for (j in (i + 1) until lines.size) {
//                if (lines[j][0] < minS) {
//                    minS = lines[j][0]
//                    minIndex = j
//                }
//            }
//
//            if (minS < lines[i][0]) {
//                // minS <-> lines[i][0]
//                // start 값 먼저 변경
//                var temp = lines[i][0]
//                lines[i][0] = minS
//                lines[minIndex][0] = temp
//
//                // end 값 변경
//                temp = lines[i][1]
//                lines[i][1] = lines[minIndex][1]
//                lines[minIndex][1] = temp
//            }
//        }


        // 검색 => 실마리 get : 스위핑 기법 (Sweeping Algorithm) 활용 문제
        // 참고 자료 1 : https://blog.naver.com/kks227/220907708368
        // 참고 자료 2 : https://byeo.tistory.com/entry/%EC%8A%A4%EC%9C%84%ED%95%91-Sweeping
        // Copilot - 실마리 : [시작점, 끝점] 좌표 => [시작점, +1], [끝점, -1] 로 처리하여 배열 따로 생성 후 점 기준 오름차순 정렬 => 겹치는 구간 찾아라
        // => Q. 그렇게 분리하고, 오름차순 정렬하면 각 선분의 시작점, 끝점 정보가 흩어진다. 겹치는 구간을 찾는다 한들 이게 어떤 선분의 시작점이고 끝점인지 모르는데 겹치는 구간을 찾는 게 의미 있나?
        // => A. 이 방식의 목적은 "전체 구간 속 겹침 영역" 측정이다. "선분 개별 정보"가 아니다. 어떤 선분이 겹쳤는지 궁금하면 개별 정보가 필요하겠지만, 그게 아니라 어떤 구간에서 2개 이상의 선분이 겹쳤고 그 길이가 얼마인지만 따지는 상황이면 몰라도 된다.


        // 1. [시작점, 끝점] 분리 작업
        // val points: Array<IntArray> = arrayOf() // 초기화
        // points.plus(intArrayOf(lines[i][0], +1)) - 이거 불가
        // Array - plus() : 배열에 원소 삽입해서 새로운 배열을 반환함
        // => 즉, points = points.plus(intArrayOf(lines[i][0], +1)) 를 진행해야 함
        // => 근데 앞서 points 를 val 로 선언함 ; 재할당 불가
        // => 이때 방법 1. points 을 var 로 선언하고 plus() 사용하면서 재할당 실행
        // => 방법 2. val points: MutableList<IntArray> = mutableListOf<IntArray>() 로 선언 후 add() 사용
//        val points: MutableList<IntArray> = mutableListOf<IntArray>()
//        for (i in 0 until lines.size) {
//            points.add(intArrayOf(lines[i][0], +1)) // 시작점
//            points.add(intArrayOf(lines[i][1], -1)) // 끝점
//        }
//        println("MeasureLineLength() - 1. [시작점, 끝점] 분리 후 - points = ${points.contentDeepToString()}")
//        println("MeasureLineLength() - 1. [시작점, 끝점] 분리 후 - points = ${points.toTypedArray().contentDeepToString()}")

        // 2. 점 기준 정렬 - 삽입 정렬 사용
//        for (i in 0 until points.size) {
//            var minP = points[i][0]
//            var minIndex = i
//
//            for (j in i + 1 until points.size) {
//                if (points[j][0] < minP) {
//                    minP = points[j][0]
//                    minIndex = j
//                }
//            }
//
//            // if (minP < points[i][0]) {
//            if (minIndex != i) {
//                // points[i] <-> points[minIndex]
//                var temp = points[i][0]
//                points[i][0] = minP
//                points[minIndex][0] = temp
//
//                temp = points[i][1]
//                points[i][1] = points[minIndex][1]
//                points[minIndex][1] = temp
//            }
//        }
//        println("MeasureLineLength() - 2. 점 기준 정렬 후 - points = ${points.contentDeepToString()}")
//        println("MeasureLineLength() - 2. 점 기준 정렬 후 - points = ${points.toTypedArray().contentDeepToString()}")

        // 3. 겹치는 선분의 길이 측정
//        var answer: Int = 0
//        var count: Int = 0
//        for (i in 0 until points.size) {
//            count += points[i][1]
//
//            if (count > 1) {
                // count 가 1보다 크다 <=> count >= 2
                // answer = abs(points[i][0] - points[i - 1][0]) // 이때 i = 0 이면 count 가 1 초과할 일 없어서 괜찮음
                // 이게 아닌 듯...????
                // 내가 생각했던 건 켭치는 게 2 이상일 때 값을 계산해서 하는 건가 했는데
                // 겹치는 게 2 이상이었다가 1로 떨어지는 순간에 계산해야 길이 측정되는 듯..?

                // 입출력 예시 고려한 처리 과정 - 해당 선분 관련 정보 찾아서 findLine 에 넣은 후 출력할 때 사용
                // 이건 조금 더 고민하고 구현해야겠다
//                val findLine: Array<IntArray> = arrayOf()
//                for (j in 0 until lines.size) {
//                    if (lines[j][0] == points[i][0]) {
//                        findLine.plus(intArrayOf(lines[j][0], lines[j][1]))
//                    }
//                }
//            }
//        }
//        println("MeasureLineLength() - 3. 겹치는 선분 길이 측정 - answer = $answer")



        // 참고 자료 2 기반으로 새로 구현
        // 1. 시작점 기준 lines 삽입 정렬
        for (i in 0 until lines.size) {
            var minS = lines[i][0]
            var minIndex = i

            for (j in (i + 1) until lines.size) {
                if (lines[j][0] < minS) {
                    minS = lines[j][0]
                    minIndex = j
                }
            }

            if (minS < lines[i][0]) {
                // minS <-> lines[i][0]
                // start 값 먼저 변경
                var temp = lines[i][0]
                lines[i][0] = minS
                lines[minIndex][0] = temp

                // end 값 변경
                temp = lines[i][1]
                lines[i][1] = lines[minIndex][1]
                lines[minIndex][1] = temp
            }
        }
        println("MeasureLineLength() - 1. 시작점 기준 정렬 - lines = ${lines.contentDeepToString()}")

        // 2. 스위핑 기법 (Sweeping) 활용
        var answer: Int = 0
        var start: Int = Int.MIN_VALUE
        var end: Int = Int.MIN_VALUE
        for (i in 0 until lines.size) {
            if (lines[i][1] > end) {
                // 완전 새로운 선분
                start = lines[i][0]
                end = lines[i][1]
            }
            else if ((lines[i][0] <= end) && (lines[i][1] > end)) {
                // 일부 겹치는 선분
                println("[${lines[i][0]}, ${end}] 에서 겹치는 구간이 발생합니다.")
                answer += (end - lines[i][0])

            }
        }









        return answer
    }


}