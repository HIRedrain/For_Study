/*
* Algorithm - Sorting
* 알고리즘 - 정렬 공부
* 최초 작성 일자 : 2025.07.07
*
* ========================================================
* 프로그램 수정 / 보완 이력
* ========================================================
* 날짜        수정 / 보완 내용
* ========================================================
* 2025.07.07  최초 작성 : 선택 정렬
* ========================================================
*/

package for_study.algorithm

class Sorting {

    // 선택 정렬 : n(n-1) / 2
    // 1. 처음부터 끝까지 비교 => 최솟값 제일 앞으로 이동
    // 2. 그다음 것부터 끝까지 비교 => 그중 최솟값 2번째 자리로 이동
    // 3. 위 방식 (n-1)번 반복
    fun selectionSort(array: IntArray) {
        println("\uD83D\uDD25 selectionSort() - 선택 정렬 시작")

        for (i in 0 until array.size) {
            println("selectionSort() - i : ${i}, array[i] : ${array[i]}")
            var minValue: Int = array[i] // 최솟값 array[i] 로 초기화
            var minIndex: Int = i // 최솟값 인덱스 i 로 초기화
            for (j in i + 1 until array.size) {
                println("selectionSort() - j : ${j}, array[j] : ${array[j]}")

                if (array[j] < minValue) {
                    // 현재 저장된 최솟값보다 array[j] 값이 더 작다 => 최솟값 정보 갱신
                    minValue = array[j]
                    minIndex = j
                }
            }

            println("selectionSort() - minValue : ${minValue}, minIndex : ${minIndex}")

            if (minValue < array[i]) {
                // minValue 가 array[i] 보다 작다 => 최솟값 => 정렬
                var temp: Int = array[i] // 임시 저장
                array[i] = minValue // i번째 위치로 최솟값 이동
                array[minIndex] = temp // 최솟값이 기존에 있던 위치로 temp 값 이동
            }
        }

        println("selectionSort() - Result : ${array.contentToString()}")
    }


}