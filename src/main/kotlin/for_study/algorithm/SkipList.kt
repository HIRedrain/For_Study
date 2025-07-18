/*
* Algorithm - SkipList
* 알고리즘 - 리스트 공부
* Skip List - 정렬된 데이터 => 검색, 삽입, 삭제 : 빠르게 처리할 수 있도록 설계된 자료 구조
* 시간 복잡도 : O(log n)
* 최초 작성 일자 : 2025.07.07
*
* ========================================================
* 프로그램 수정 / 보완 이력
* ========================================================
* 날짜        수정 / 보완 내용
* ========================================================
* 2025.07.17  최초 작성 : SkipList
* ========================================================
*/

package for_study.algorithm

class SkipList<E : Comparable<E>> : MutableIterator<E> {
    protected var name: String? = null
    private var head: QuadNode<E>?
    private var size = 0

    constructor(name: String?) {
        this.name = name
        this.head = QuadNode<E> ()
        this.size = 0
    }

    fun getName(): String? {
        return this.name
    }

    fun setName(name: String) {
        this.name = name
    }


}