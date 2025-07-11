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
* 2025.07.09  병합 정렬
* 2025.07.10  퀵 정렬
* 2025.07.11  배열 생성, 배열 원소 섞는 함수
* 2025.07.11  하이브리드 정렬 (삽입 + 퀵)
* ========================================================
*/

package for_study.algorithm

class Sorting {

    // 선택 정렬 : O(n^2)
    // 1. 처음부터 끝까지 비교 => 최솟값 제일 앞으로 이동
    // 2. 그다음 것부터 끝까지 비교 => 그중 최솟값 2번째 자리로 이동
    // 3. 위 방식 (n-1)번 반복
    // n(n-1)  2 번 비교
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


    // 병합 정렬 : O(n * log n)
    // 분할 및 정복 방식의 알고리즘 - 분할 (Divide) & 병합 (Merge)
    // 분할 : 원소가 2개 내지는 1개 남을 때까지 1/2 분할 진행 후 정렬 처리
    // 병합 : 정렬된 부분 => 병합 & 정렬
    // 병합해야 할 부분이 구간별로 정렬 돼 있어 병합 정렬 신속히 처리 가능
    // 각 병합 단계에서 총 N 개 비교
    fun mergeSort(array: IntArray) {
        println("\uD83D\uDD25 mergeSort() - 병합 정렬 시작")

        val tempArray = array.copyOf() // 깊은 복사 - 별도의 객체로 복사
        _mergeSort(array, tempArray, 0, array.size - 1)

        println("mergeSort() - Result : ${array.contentToString()}")
    }

    fun _mergeSort(array: IntArray, tempArray: IntArray, left: Int, right: Int) {
        println("\uD83D\uDD25 _mergeSort() - 내부 병합 정렬 시작")

        if (left >= right) {
            // 배열의 원소가 하나다? => 할 필요 없음
            return
        }

        var mid = (left + right) / 2 // 소수점 아래 값 버림
        println("_mergeSort() - left = ${left}, right = ${right}, mid = ${mid}")

        // 재귀 함수 호출
        _mergeSort(array, tempArray, left, mid) // 왼쪽 구간
        _mergeSort(array, tempArray, mid + 1, right) // 오른쪽 구간

        // 1. temp_array 에다가 배열 반씩 복사
        for (i in left .. mid) {
            println("_mergeSort() - left = ${left}, i = ${i}")

            tempArray[i] = array[i]
        }

        for (i in 1 .. (right - mid)) {
            println("_mergeSort() - right = ${right}, right - i + 1 = ${right - i + 1}, mid + 1 = ${mid + 1}")

            tempArray[right - i + 1] = array[mid + i]
        }

        // 2. 나눈 것을 다시 병합
        var i = left
        var j = right
        var k = left
        while (k <= right) {
            println("_mergeSort() - i = ${i}, j = ${j}, k = ${k}")

            if (tempArray[i] < tempArray[j]) {
                println("_mergeSort() - temp_array[i] (${tempArray[i]}) < temp_array[j] (${tempArray[j]})")

                array[k] = tempArray[i++]
            }
            else {
                println("_mergeSort() - temp_array[i] (${tempArray[i]}) > temp_array[j] (${tempArray[j]})")

                array[k] = tempArray[j--]
            }

            k++
        }

        println("_mergeSort() - Result : ${array.contentToString()}")
    }


    // 퀵 정렬 : O(n * log n)
    // 분할 및 정복 방식의 알고리즘 (Divide and Conquer)
    // pivot : 탐색 구간의 중간 값
    // _partition() : pivot 과 다른 원소 비교 & 원소 자리 변경 (swapping)- pivot 보다 작은 원소, 큰 원소 구분 => 집합 분할
    // => 분할 구간 - 재귀 함수 호출 => 탐색 구간 1/2 줄여
    // 재귀 함수 호출 시 함수 호출 오버헤드 발생 => 원소가 적은 배열 - 삽입 정렬, 선택 정렬보다 더 오래 걸릴 수 있음
    // => 성능 저하 : 따라서, 추후 하이브리드 정렬 구현
    fun quickSort(array: IntArray) {
        println("\uD83D\uDD25 quickSort() - 퀵 정렬 시작")

        _quickSort(array, 0, array.size - 1)

        println("quickSort() - Result : ${array.contentToString()}")
    }

    fun _quickSort(array: IntArray, left: Int, right: Int) {
        println("\uD83D\uDD25 _quickSort() - 내부 퀵 정렬 시작")

        if (left >= right) {
            // 배열의 원소가 하나다? => 할 필요 없음
            return
        }

        var pivotIndex = (left + right) / 2
        var newPivotIndex = _partition(array, left, right, pivotIndex)

        if (left < (newPivotIndex - 1)) {
            println("_quickSort() - left (${left}) < newPivotIndex - 1 (${newPivotIndex - 1})")

            _quickSort(array, left, newPivotIndex - 1)
        }

        if (right > (newPivotIndex + 1)) {
            println("_quickSort() - right (${right}) > newPivotIndex + 1 (${newPivotIndex + 1})")

            _quickSort(array, newPivotIndex + 1, right)
        }

        println("_quickSort() - Result : ${array.contentToString()}")
    }

    fun _partition(array: IntArray, left: Int, right: Int, pivotIndex: Int): Int {
        println("\uD83D\uDD25 _partition() - 퀵 정렬 시작")

        // array[pivotIndex] <-> array[right]
        // 최종적으로 pivotValue 값이 array[right] 에 위치함
        var pivotValue = array[pivotIndex]
        array[pivotIndex] = array[right]
        array[right] = pivotValue

        var temp: Int
        var newPivotIndex = left
        for (i in left .. (right -1)) {
            println("_partition() - for : i = $i")

            if (array[i] <= pivotValue) {
                // pivotValue 보다 원소 값이 작거나 같다 => 왼쪽으로 이동
                println("_partition() - for : array[i] (${array[i]}) <= pivotValue (${pivotValue})")

                // array[i] <-> array[newPivotIndex]
                // 배열의 왼쪽 구간에 i번째 원소 값 저장
                temp = array[i]
                array[i] = array[newPivotIndex]
                array[newPivotIndex] = temp
                newPivotIndex++
            }
        }

        // array[newPivotIndex] <-> array[right]
        // array[newPivotIndex] : pivotValue 보다 작거나 같은 원솟값이 끝나는 지점
        // array[right] : pivotValue 값 저장된 상태
        // pivotValue 값을 array[newPivotIndex] 위치로 옮김
        println("_partition() - array[newPivotIndex] (${array[newPivotIndex]}) <-> array[right] (${array[right]})")
        temp = array[newPivotIndex]
        array[newPivotIndex] = array[right]
        array[right] = temp

        println("_partition() - Result : ${array.contentToString()}")

        return newPivotIndex
    }


    // 중복 없는 난수 배열 생성 함수 - 테스트 때 사용할 용도
    // 배열 원소 섞는 건 별도로 진행하는 걸로 처리 : test 코드에서 예측 값 설정할 때 사용해야 하니까..
    fun genBigRandIntArray(size: Int, offset: Int): IntArray {
        println("\uD83D\uDD25 genBigRandIntArray() - 배열 생성 시작")

        // 배열 생성
        val array = IntArray(size)
        for (i in 0 until size) {
            array[i] = i + offset
        }

        // 원소 자리 바꾸기
        // shuffleArray(array)

        println("genBigRandIntArray() - Result : ${array.contentToString()}")

        return array
    }


    // 원소 섞는 함수 - 테스트 때 사용할 용도
    fun shuffleArray(array: IntArray) {
        println("\uD83D\uDD25 shuffleArray() - 배열 섞기 시작")

        // 원소 섞기
        for (i in 0 until array.size) {
            val j = (Math.random() * array.size).toInt()
            if (j == i) {
                // i 와 j 가 같으면 원소 자리 바꿀 필요 없으니까
                continue
            }

            // array[i] <-> array[j] : 다르면 원소 자리 바꿔야지
            val temp = array[i]
            array[i] = array[j]
            array[j] = temp
        }

        println("shuffleArray() - Result : ${array.contentToString()}")
    }


    // 하이브리드 정렬
    // 배열 원소 <= 32 : 삽입 정렬
    // 배열 원소 > 32 : 퀵 정렬
    fun hybridSort(array: IntArray) {
        println("\uD83D\uDD25 hybridSort() - 하이브리드 정렬 시작")

        if (array.size > 32) {
            // 배열 크기 32보다 큼 : 퀵 정렬
            println("hybridSort() - array.size (${array.size}) : quickSort()")

            quickSort(array)
        }
        else {
            // 배열 크기 32 이하 : 삽입 정렬0
            println("hybridSort() - array.size (${array.size}) : insertionSort()")

            insertionSort(array)
        }
    }




}