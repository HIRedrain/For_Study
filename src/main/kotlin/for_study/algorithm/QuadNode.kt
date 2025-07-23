/*
* Algorithm - QuadNode
* 알고리즘 - 리스트 공부
* Quadtree (사분 트리) - 공간을 네 개의 하위 영역으로 나눈다
* 최초 작성 일자 : 2025.07.07
*
* ========================================================
* 프로그램 수정 / 보완 이력
* ========================================================
* 날짜        수정 / 보완 내용
* ========================================================
* 2025.07.14  최초 작성 : QuadNode
* 2025.07.17  마무리 구현
* 2025.07.23  클래스 이름 내 공란 한 칸 삭제
* ========================================================
*/


package for_study.algorithm

class QuadNode<E : Comparable<E>> {
    private var entity: E? = null
    private var level = 0
    private var up: QuadNode<E>? = null
    private var down: QuadNode<E>? = null
    private var next: QuadNode<E>? = null
    private var prev: QuadNode<E>? = null

    constructor() { // 기본 생성자
        this.entity = null
        this.level = 0
    }

    constructor(level: Int) {
        this.entity = null
        this.level = level
    }

    constructor(entity: E, level: Int) {
        this.entity = entity as E
        this.level = level
    }

    constructor(entity: Any?, level: Int) {
        this.entity = entity as E
        this.level = level
    }

    override fun toString(): String {
        var str: String = ""
        str += if (this.entity == null) "None" else this.entity.toString()

        return str
    }

    fun getEntity(): E? {
        return this.entity
    }

    fun setEntity(entity: E) {
        this.entity = entity
    }

    fun getLevel(): Int {
        return this.level
    }

    fun setLevel(level: Int) {
        this.level = level
    }

    fun getUp(): QuadNode<E>? {
        return this.up
    }

    fun setUp(up: QuadNode<E>?) {
        this.up = up
    }

    fun getDown(): QuadNode<E>? {
        return this.down
    }

    fun setDown(down: QuadNode<E>?) {
        this.down = down
    }

    fun getNext(): QuadNode<E>? {
        return this.next
    }

    fun setNext(next: QuadNode<E>?) {
        this.next = next
    }

    fun getPrev(): QuadNode<E>? {
        return this.prev
    }

    fun setPrev(prev: QuadNode<E>?) {
        this.prev = prev
    }
}