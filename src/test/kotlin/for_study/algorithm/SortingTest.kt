/*
* Algorithm - Sorting
* Test 파일
* 최초 작성 일자 : 2025.07.07
*
* ========================================================
* 프로그램 수정 / 보완 이력
* ========================================================
* 날짜        수정 / 보완 내용
* ========================================================
* 2025.07.07  최초 작성 : 선택 정렬 테스트
* 2025.07.08  삽입 정렬 테스트
* 2025.07.09  병합 정렬 테스트
* 2025.07.10  퀵 정렬 테스트
* ========================================================
*/

package for_study.algorithm

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class SortingTest {
    val sorting: Sorting = Sorting()


    @Test
    @DisplayName("선택 정렬")
    @Disabled
    fun selectionSortTest() {
        // given ; data 미리 준비
        val intArray: IntArray = intArrayOf(2, 5, 7, 3, 1, 8, 6, 10, 4, 9)

        // 예상 값 정답
        val expectedIntArray: IntArray = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        // when ; test 기능 수행
        sorting.selectionSort(intArray)

        // then ; 실제 결과 확인
        assertArrayEquals(expectedIntArray, intArray)
    }

    @Test
    @DisplayName("삽입 정렬")
    @Disabled
    fun insertionSortTest() {
        // given ; data 미리 준비
        val intArray: IntArray = intArrayOf(2, 5, 7, 3, 1, 8, 6, 10, 4, 9)

        // 예상 값 정답
        val expectedIntArray: IntArray = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        // when ; test 기능 수행
        sorting.insertionSort(intArray)

        // then ; 실제 결과 확인
        assertArrayEquals(expectedIntArray, intArray)
    }

    @Test
    @DisplayName("병합 정렬")
    @Disabled
    fun mergeSortTest() {
        // given ; data 미리 준비
        val intArray: IntArray = intArrayOf(2, 5, 7, 3, 1, 8, 6, 10, 4, 9)

        // 예상 값 정답
        val expectedIntArray: IntArray = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        // when ; test 기능 수행
        sorting.mergeSort(intArray)

        // then ; 실제 결과 확인
        assertArrayEquals(expectedIntArray, intArray)
    }

    @Test
    @DisplayName("퀵 정렬")
    fun quickSortTest() {
        // given ; data 미리 준비
        val intArray: IntArray = sorting.genBigRandIntArray(10, 0)
        val expectedIntArray = intArray.copyOf() // 예상 값 정답 - 깊은 복사 처리

        sorting.shuffleArray(intArray) // 배열 원소 섞기

        //println("intArray = ${intArray.contentToString()}")
        //println("expectedIntArray = ${expectedIntArray.contentToString()}")

        // when ; test 기능 수행
        sorting.quickSort(intArray)

        // then ; 실제 결과 확인
        assertArrayEquals(expectedIntArray, intArray)
    }

}