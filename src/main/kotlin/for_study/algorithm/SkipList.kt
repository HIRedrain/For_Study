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
* 2025.07.23  Debugging - add()
* 2025.07.23  Debugging - get()
* ========================================================
*/

package for_study.algorithm

import kotlin.random.Random

class SkipList<E : Comparable<E>> : MutableIterator<E> {
    private var name: String? = null
    private var head: QuadNode<E>?
    private var size = 0

    constructor() {
        this.name = null
        this.head = QuadNode<E> ()
        this.size = 0
    }

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
        if ((node!!.getEntity() != null) && (node.getEntity()!!.compareTo(key) == 0)) {
            node.setEntity(key)

            return
        }

        var newNode: QuadNode<E> = QuadNode<E>(key, node.getLevel())
        insertNext(node, newNode)

        var currentLevel = node.getLevel()
        var headLevel = this.head!!.getLevel()
        val rand = Random.Default
        var randomValue = rand.nextDouble()
        println("SkipList.add() - randomValue : ${randomValue}")
        while (randomValue < 0.5) {
            // 0.5 미만일 때 반복문 실행 이유 : "동전을 던져서 앞면이 나오면 한 단계 레벨 올린다" 이런 느낌
            // 평균적으로 전체 노드 중 일부만 높은 레벨
            // => 높은 레벨 노드 적게 유지 => 탐색 속도 높 && 데이터 삽입 빠르면서도 효율적으로 균형 잡히도록
            // if) 기준 0.25 : 레벨 느리게 오름 => 낮은 레벨 多
            // if) 기준 0.75 : 레벨 빠르게 오름 => 높은 레벨 多
            if (currentLevel >= headLevel) {
                val newHead = QuadNode<E>(headLevel + 1)
                this.head = newHead
                headLevel = this.head!!.getLevel()
            }

            while (node?.getUp() == null) {
                node = node?.getPrev()

                if (node == null) {
                    break
                }
            }

            val upNode = node?.getUp()
            if (upNode != null) {
                node =upNode
                val temp = QuadNode<E>(key, node.getLevel())
                insertNext(node, temp)
                insertAbove(newNode, temp)
                newNode = temp
                currentLevel++
            }
            else {
                // 더 이상 레벨 업 x
                break
            }
        }

        this.size++
    }

    fun remove(key: E) {
        checkValidity(key)
        var node = findNode(key)

        //if ((node == null) || (node.getEntity()!!.compareTo(key) !== 0)) {
        // !== 참조 동등성 부정 연산자 <=> a !== b : a 와 b 가 서로 다른 객체를 참조하고 있는가?
        // compareTo() : 두 객체의 값 크기를 비교 => Int 값 반환 (0 : 같음, + : 왼쪽이 더 큼, - : 오른쪽이 더 큼)
        // => 예시 : "zebra".compareTo("apple") > 0
        // 고로, node.getEntity()!!.compareTo(key) != 0 가 맞지 않나 하고

        val entity = node?.getEntity()
        if ((node == null) || (entity == null) || (entity.compareTo(key) != 0)) {
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
        // 여기서 예외 발생 : NullPointerException
        // node == null 일 때 node!! 로 강제 호출 => NPE 발생
        // !! : null => 예외 발생
        //return if (node!!.getEntity()!!.compareTo(key) === 0) node!!.getEntity()!! else null
        //return if (node!!.getEntity()!!.compareTo(key) == 0) node!!.getEntity()!! else null


        if (node != null) {
            val entity = node.getEntity()
            if ((entity != null) && (entity.compareTo(key) == 0)) {
                return entity
            }
        }

        return null
    }

    // in 사용 가능하도록 처리
    operator fun contains(key: E): Boolean {
        // key 에 해당하는 값이 이 클래스에 존재 => true
        return this[key] != null
    }

    private fun checkValidity(entity: E?) {
        requireNotNull(entity) {"Key 값은 null 이 될 수 없습니다."} // entity 가 null 일 때 예외 던짐 - IllegalArgumentException
    }

    private fun findNode(key: E): QuadNode<E>? {
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
                break
            }

            down = node.getDown()
            node = down ?: break // down != null => node = down, down == null => break : 아래 레벨 노드 있으면 계속 진행 아니면 그만
        }

        return node
    }

    private fun lessThanOrEqual(a: E?, b: E): Boolean {
        return a!!.compareTo(b) <= 0
    }

    private fun insertNext(x: QuadNode<E>?, y: QuadNode<E>?) {
        y!!.setPrev(x)
        y!!.setNext(x!!.getNext())

        if (x.getNext() != null) {
            x.getNext()!!.setPrev(y)
        }
        x.setNext(y)
    }

    private fun insertAbove(x: QuadNode<E>, y: QuadNode<E>) {
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
        println("\uD83D\uDD25 SkipList.toString() - 출력 형식 처리 시작")

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
            println("SkipList.toString() - while (node!!.getDown() != null)")
            node = node.getDown()
        }

        while (node!!.getPrev() != null) {
            println("SkipList.toString() - while (node!!.getPrev() != null)")
            node = node.getPrev()
        }

        if (node.getNext() != null) {
            println("SkipList.toString() - while (node!!.getNext() != null)")
            node = node.getNext()
        }

        while (node != null) {
            println("SkipList.toString() - while (node != null)")
            str += String.format("%3s", node)
            node = node.getNext()
        }
        str += " ]"

        return str
    }


}