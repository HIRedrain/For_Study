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
* ========================================================
*/


package for_study.algorithm

class  QuadNode<E : Comparable<E>> {
    private var entity: E? = null
    private var level = 0
    private var up: QuadNode<E>? = null
    private var down: QuadNode<E>? = null
    private var next: QuadNode<E>? = null
    private var prev: QuadNode<E>? = null


}