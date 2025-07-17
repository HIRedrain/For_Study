/*
* Algorithm - SkipListIterator
* 알고리즘 - 리스트 공부
* Skip List Iterator - 자료 구조 Skip List를 순회하는 반복자 (Iterator)
* 읽기 전용 반복자 - remove() 지원 x
* 최초 작성 일자 : 2025.07.07
*
* ========================================================
* 프로그램 수정 / 보완 이력
* ========================================================
* 날짜        수정 / 보완 내용
* ========================================================
* 2025.07.17  최초 작성 : SkipListIterator
* ========================================================
*/

package for_study.algorithm

class SkipListIterator<E: Comparable<E>> : MutableIterator<E> {
    private var node: QuadNode<E>? = null

    // Skip List 의 제일 하단의 좌측 끝부터 우측으로 순회
    constructor(node: QuadNode<E>?) {
        this.node = node

        // 가장 아래 레벨까지 내려감
        while (node!!.getDown() != null) {
            this.node = node.getDown()
        }

        // 레벨 0의 맨 앞 노드 (보통 head node) 까지 이동
        while (node!!.getPrev() != null) {
            this.node = node.getPrev()
        }

        // 값이 있는 노드 중 가장 첫 번째 노드로 이동 - head node 다음 노드
        if (node.getNext() != null) {
            this.node = node.getNext()
        }

        // this.node = node
    }

    override fun hasNext(): Boolean {
        return this.node != null
    }

    override fun next(): E {
        val result: E? = this.node!!.getEntity()
        this.node = node!!.getNext()

        return result!!
    }

    override fun remove() {
        // 해당 클래스에서는 요소 제거 기능 지원 x => 예외 던짐
        // "이 기능은 지원하지 않는다"
        throw UnsupportedOperationException()
    }
}