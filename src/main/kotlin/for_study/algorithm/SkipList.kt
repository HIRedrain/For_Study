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
* 2025.07.18  최초 작성 : SkipList
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

    fun isEmpty(): Boolean {
        return this.size == 0
    }

    fun getName(): String? {
        return this.name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getSize(): Int {
        return this.size
    }

    operator fun get(key: E): E? {
        checkValidity(key) // 유효성 검사
        val node = findNode(key) // key 에 해당하는 노드 찾기

        // === ; 참조 동등성 - 해당 객체가 같은 주솟값을 가지는가? <=> 존재 자체가 같은 앤가
        return if (node!!.getEntity()!!.compareTo(key) === 0) node!!.getEntity()!! else null
    }

    // in 사용 가능하도록 처리
    operator fun contains(key: E): Boolean {
        // key 에 해당하는 값이 이 클래스에 존재 => true
        return this[key] != null
    }

    protected fun checkValidity(entity: E?) {
        requireNotNull(entity) {"Key 값은 null 이 될 수 없습니다."} // entity 가 null 일 때 예외 던짐 - IllegalArgumentException
    }

    protected fun findNode(key: E): QuadNode<E>? {
        var node = this.head
        var next: QuadNode<E>? = null
        var down: QuadNode<E>? = null
        var nodeKey: E? = null
        while (true) {
            next = node!!.getNext()
            while ((next != null) && (lessThanOrEqual(next.getEntity(), key))) {
                node = next
                next = node.getNext()
            }
            nodeKey = node!!.getEntity()
            if ((nodeKey != null) && (nodeKey.compareTo(key) == 0)) {
                down = node.getDown()
                node = down ?: break
            }
        }

        return node
    }

    protected fun lessThanOrEqual(a: E?, b: E): Boolean {
        return a!!.compareTo(b) <= 0
    }







}