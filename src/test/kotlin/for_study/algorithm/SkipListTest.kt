/*
* Algorithm - SkipList
* Test 파일
* 최초 작성 일자 : 2025.07.21
*
* ========================================================
* 프로그램 수정 / 보완 이력
* ========================================================
* 날짜        수정 / 보완 내용
* ========================================================
* 2025.07.21  최초 작성 : SkipList 전체 기능 테스트
* 2025.07.23  skipListTest() 작성
* ========================================================
*/

package for_study.algorithm

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class SkipListTest {
    @Test
    @DisplayName("Skip List 전체 기능 테스트")
    fun skipListTest() {
        val skipList: SkipList<String> = SkipList("SkipList_str")
        val strArray = arrayOf("C", "A", "E", "B", "R", "D", "V", "L", "T")
        for (str in strArray) {
            print("adding $str => ")
            skipList.add(str)
            println("$skipList")
        }

        val testArray = arrayOf("T", "A", "N")
        for (str in testArray) {
            if (skipList.contains(str)) {
                println("Searching %s from %s => contains".format(str, skipList.getName()))
            }
            else {
                println("Searching %s from %s => does not contain".format(str, skipList.getName()))
            }
        }

        for (str in strArray) {
            print("removing (%s) from SkipList<String> => ".format(str))
            skipList.remove(str)
            println("%s".format(skipList))
        }
    }

}