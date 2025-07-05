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
* ========================================================
*/

package coding_test

class Lv0 {

    // 2025.07.04 - babbling
    // https://school.programmers.co.kr/learn/courses/30/lessons/120956?language=kotlin
    // 머쓱이 조카 : 6개월 - "aya", "ye", "woo", "ma" 네 가지 발음을 최대 한 번씩 사용해 조합한(이어 붙인) 발음만 가능
    // 매개변수 : 문자열 배열 babblings
    // Q. 머쓱이 조카가 발음할 수 있는 단어 개수 반환
    fun babbling(babllings: Array<String>): Int {
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

        return 0;
    }
}