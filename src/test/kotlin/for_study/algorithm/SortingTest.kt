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
* 2025.07.11  하이브리드 정렬 테스트
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
    @Disabled
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

    @Test
    @DisplayName("하이브리드 정렬")
    fun hybridSortTest() {
        // given ; data 미리 준비
        val intArray1 = sorting.genBigRandIntArray(32, 2)
        val expectedIntArray1 = intArray1.copyOf()
        sorting.shuffleArray(intArray1)

        val intArray2 = sorting.genBigRandIntArray(33, 10)
        val expectedIntArray2 = intArray2.copyOf()
        sorting.shuffleArray(intArray2)

        val intArray3 = sorting.genBigRandIntArray(100, 100)
        val expectedIntArray3 = intArray3.copyOf()
        sorting.shuffleArray(intArray3)

        val intArray4 = sorting.genBigRandIntArray(1000, 0)
        val expectedIntArray4 = intArray4.copyOf()
        sorting.shuffleArray(intArray4)

//        val intArray5 = sorting.genBigRandIntArray(10000, 0)
//        val expectedIntArray5 = intArray5.copyOf()
//        sorting.shuffleArray(intArray5)


        val intArray1Quick = intArray1.copyOf()
        val intArray2Insertion = intArray2.copyOf()
        val intArray3Insertion = intArray3.copyOf()
//        val intArray4Insertion = intArray4.copyOf()

//        println("intArray1 = ${intArray1.contentToString()}")
//        println("expectedIntArray1 = ${expectedIntArray1.contentToString()}")
//        println("intArray2 = ${intArray2.contentToString()}")
//        println("expectedIntArray2 = ${expectedIntArray2.contentToString()}")
//        println("intArray3 = ${intArray3.contentToString()}")
//        println("expectedIntArray3 = ${expectedIntArray3.contentToString()}")

        // when ; test
        var before = System.nanoTime()
        sorting.hybridSort(intArray1)
        var after = System.nanoTime()
        val intArray1HybridTime = (after - before) / 1000000 // ns -> ms

        before = System.nanoTime()
        sorting.quickSort(intArray1Quick)
        after = System.nanoTime()
        val intArray1QuickTime = (after - before) / 1000000

        before = System.nanoTime()
        sorting.hybridSort(intArray2)
        after = System.nanoTime()
        val intArray2HybridTime = (after - before) / 1000000

        before = System.nanoTime()
        sorting.insertionSort(intArray2Insertion)
        after = System.nanoTime()
        val intArray2InsertionTime = (after - before) / 1000000

        before = System.nanoTime()
        sorting.hybridSort(intArray3)
        after = System.nanoTime()
        val intArray3HybridTime = (after - before) / 1000000

        before = System.nanoTime()
        sorting.insertionSort(intArray3Insertion)
        after = System.nanoTime()
        val intArray3InsertionTime = (after - before) / 1000000

        before = System.nanoTime()
        sorting.hybridSort(intArray4)
        after = System.nanoTime()
        val intArray4HybridTime = (after - before) / 1000000

//        before = System.nanoTime()
//        sorting.insertionSort(intArray4Insertion)
//        after = System.nanoTime()
//        val intArray4InsertionTime = (after - before) / 1000000

//        var before = System.nanoTime()
//        sorting.hybridSort(intArray5)
//        var after = System.nanoTime()
//        val intArray5Time = (after - before) / 1000000


        println("intArray1HybridTime(ms) - insertion (${intArray1.size}) : $intArray1HybridTime")
        println("intArray1QuickTime(ms) - Quick (${intArray1.size}) : $intArray1QuickTime")
        println("intArray2InsertionTime(ms) - insertion (${intArray2.size}) : $intArray2InsertionTime")
        println("intArray2HybridTime(ms) - Quick (${intArray2.size}) : $intArray2HybridTime")
        println("intArray3InsertionTime(ms) - insertion (${intArray3.size}) : $intArray3InsertionTime")
        println("intArray3HybridTime(ms) - Quick (${intArray3.size}) : $intArray3HybridTime")
//        println("intArray4InsertionTime(ms) - insertion (${intArray4.size}) : $intArray4InsertionTime")
        println("intArray4HybridTime(ms) - Quick (${intArray4.size}) : $intArray4HybridTime")
//        println("intArray5HybridTime(ms) - Quick (${intArray5.size}) : $intArray5Time")


        // then ; 실제 결과 확인
        assertArrayEquals(expectedIntArray1, intArray1)
        assertArrayEquals(expectedIntArray2, intArray2)
        assertArrayEquals(expectedIntArray3, intArray3)
        assertArrayEquals(expectedIntArray4, intArray4)
//        assertArrayEquals(expectedIntArray5, intArray5)


        assertArrayEquals(expectedIntArray1, intArray1Quick)
        assertArrayEquals(expectedIntArray2, intArray2Insertion)
        assertArrayEquals(expectedIntArray3, intArray3Insertion)
//        assertArrayEquals(expectedIntArray4, intArray4Insertion)
    }


}