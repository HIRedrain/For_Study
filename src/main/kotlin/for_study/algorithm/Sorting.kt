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
* 2025.07.08  삽입 정렬
* ========================================================
*/

package for_study.algorithm

class Sorting {

    // 선택 정렬 : O(n^2)
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


    // 삽입 정렬 : 최선 - O(n), 최악 - O(n^2)
    // 1. 두 번째 원소를 첫 번째 원소와 비교한다.
    // 1-1. 첫 번째 원소가 두 번째 원소보다 크다면? 첫 번째 원소를 한 칸 뒤로 밀어 두 번째 원소 자리에 넣고, 두 번째 원소를 첫 번째 자리에 넣는다.
    // 1-2. 첫 번째 원소가 두 번째 원소보다 작다면? 그대로 둔다.
    // 2. 세 번째 원소를 두 번째, 첫번째 원소와와 비교한다.
    // 2-1-1. 두 번째 원소가 세 번째 원소보다 크다면? 두 번째 원소를 한 칸 뒤로 밀어 세 번째 원소 자리에 넣는다.
    // 2-1-2. 첫 번째 원소가 세 번째 원소보다 크다면? 첫 번째 원소를 한 칸 뒤로 밀어 두 번째 원소 자리에 넣고, 세 번째 원소를 첫 번째 자리에 넣는다.
    // 2-1-3. 첫 번째 원소가 세 번째 원소보다 작다면? 두 번째 원소 자리에 세 번째 원소를 넣는다.
    // 2-2-1. 두 번째 원소가 세 번째 원소보다 작다면? 그대로 둔다. (더 할 필요 x)
    // 3. 위 방식을 n번째 원소까지 진행한다.

    // 헷갈린 부분 : if) 세 번째 원소가 첫 번째 원소보다는 작고, 두 번째 원소보다는 크면 어떻게 되는 걸까?
    // => 모순 - 말이 안 됨
    // => 세 번째 원소 순서가 왔다 == 첫 번째 원소와 두 번째 원소는 이미 정렬된 상태다. : 첫 번째 원소 < 두 번째 원소
    // => (세 번째 원소 < 첫 번째 원소) && (세 번째 원소 > 두 번째 원소) : 성립 불가
    fun insertionSort(array: IntArray) {
        println("\uD83D\uDD25 insertionSort() - 삽입 정렬 시작")
        for (i in 1 until array.size) {
            println("insertionSort() - i : ${i}, array[i] : ${array[i]}")
            var keyValue = array[i]
            //var keyIndex = i

            // 방법 1. for 반복문으로 작성 - Test 로 정상 동작 확인
//            for (j in i - 1 downTo 0) {
//                println("insertionSort() - ver1. for : j = ${j}, array[j] = ${array[j]}")
//
//                if (array[j] > keyValue) {
//                    // array[j] 가 keyValue 보다 크다
//                    // 원소 한 칸 뒤로 밀기
//                    println("insertionSort() - array[j] > keyValue")
//
//                    array[j + 1] = array[j]
//
//                    if (j == 0) {
//                        // j 가 0 일 때 처리
//                        array[j] = keyValue
//                    }
//                }
//                else {
//                    // array[j] 가 keyValue 보다 작다
//                    // j + 1 위치에 keyValue 저장 후 반복문 종료
//                    println("insertionSort() - array[j] < keyValue")
//
//                    array[j + 1] = keyValue
//
//                    break;
//                }
//            }

            // 방법 2. while 반복문으로 작성
            var j = i - 1
            while ((j >= 0) && (array[j] > keyValue)) {
                // j 가 0보다 크거나 같고, array[j] 가 keyValue 보다 큰 상황에서만 반복
                println("insertionSort() - ver2. while : j = ${j}, array[j] = ${array[j]}")
                array[j + 1] = array[j] // 한 칸 뒤로 물림
                j-- // j 값 ; 1 감소
            }

            array[j + 1] = keyValue
        }

        println("insertionSort() - Result : ${array.contentToString()}")
    }

}