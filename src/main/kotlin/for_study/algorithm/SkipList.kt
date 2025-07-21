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
* 2025.07.21  SkipList 마무리
* ========================================================
*/

package for_study.algorithm

import kotlin.random.Random

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

    fun add(key: E) {
        checkValidity(key)

        var node = findNode(key)
        if ((node!!.getEntity() != null) && (node.getEntity()!!.compareTo(key) === 0)) {
            node.setEntity(key)

            return
        }

        var newNode: QuadNode<E> = QuadNode<E>(key, node.getLevel())
        insertNext(node, newNode)

        var currentLevel = node.getLevel()
        var headLevel = this.head!!.getLevel()
        var rand = Random.Default
        while (rand.nextDouble() < 0.5) {
            if (currentLevel >= headLevel) {
                val newHead = QuadNode<E>(headLevel + 1)
                this.head = newHead
                headLevel = this.head!!.getLevel()
            }

            while (node!!.getUp() == null) {
                node = node.getPrev()
            }

            node =node.getUp()
            val temp = QuadNode<E>(key, node!!.getLevel())
            insertNext(node, temp)
            insertAbove(newNode, temp)
            newNode = temp
            currentLevel++
        }

        this.size++
    }

    fun remove(key: E) {
        checkValidity(key)
        var node = findNode(key)
        if ((node == null) || (node.getEntity()!!.compareTo(key) !== 0)) {
            throw NoSuchElementException("key 에 해당하는 node 가 존재하지 않습니다.")
        }

        while (node!!.getDown() != null) {
            node = node.getDown()
        }

        var prev: QuadNode<E>? = null
        var next: QuadNode<E>? = null
        while (node != null) {
            prev = node.getPrev()
            next = node.getNext()
            prev?.setNext(next)
            next?.setPrev(prev)
            node = node.getUp()
        }

        while ((this.head!!.getNext() == null) && (this.head!!.getDown() != null)) {
            this.head = this.head!!.getDown()
            this.head!!.setUp(null)
        }

        this.size--
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
                node = down ?: break // down != null => node = down, down == null => break : 아래 레벨 노드 있으면 계속 진행 아니면 그만
            }
        }

        return node
    }

    protected fun lessThanOrEqual(a: E?, b: E): Boolean {
        return a!!.compareTo(b) <= 0
    }

    protected fun insertNext(x: QuadNode<E>?, y: QuadNode<E>?) {
        y!!.setPrev(x)
        y!!.setNext(x!!.getNext())

        if (x.getNext() != null) {
            x.getNext()!!.setPrev(y)
        }
        x.setNext(y)
    }

    protected fun insertAbove(x: QuadNode<E>, y: QuadNode<E>) {
        x!!.setUp(y)
        y.setDown(x)
    }

    override fun hasNext(): Boolean {
        TODO("아직 구현되지 않은 함수입니다.") // 특수 함수 - 나중에 구현해야 함
    }

    override fun next(): E {
        TODO("아직 구현되지 않은 함수입니다.") // 특수 함수 - 나중에 구현해야 함
    }

    override fun remove() {
        TODO("아직 구현되지 않은 함수입니다.") // 특수 함수 - 나중에 구현해야 함
    }

    override fun toString(): String {
        var str: String = this.name as String
        str += String.format("(size = %3d) : ", this.size)

        if (this.size == 0) {
            str += "Empty"
            return str
        }
        else {
            str += "[ "
        }

        var node: QuadNode<E>? = this.head

        while (node!!.getDown() != null) {
            node = node.getDown()
        }

        while (node!!.getPrev() != null) {
            node = node.getPrev()
        }

        if (node.getNext() != null) {
            node = node.getNext()
        }

        while (node != null) {
            str += String.format("%3s", node)
            node = node.getNext()
        }
        str += " ]"

        return str
    }


}