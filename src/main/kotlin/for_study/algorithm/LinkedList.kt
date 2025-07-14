/*
* Algorithm - Linked List
* 알고리즘 - 리스트 공부
* 최초 작성 일자 : 2025.07.07
*
* ========================================================
* 프로그램 수정 / 보완 이력
* ========================================================
* 날짜        수정 / 보완 내용
* ========================================================
* 2025.07.12  최초 작성 : 연결 리스트
* ========================================================
*/

package for_study.algorithm

class LinkedList<T> {
    private var head: DLLN<T> = DLLN<T>() // head : dummy node
    private var tail: DLLN<T> = DLLN<T>() // tail : dummy node
    private var count = 0 // 원소 개수

    constructor() { // 기본 생성자
        this.head.next = this.tail
        this.tail.prev = this.head
        this.count = 0
    }

    fun insertFront(data: T) { // 제일 앞에 원소 삽입
        val newDLLN = DLLN<T>(data)
        val nextDLLN = this.head!!.getDLLNNext()
        newDLLN.setDLLNNext(nextDLLN)
        this.head!!.setDLLNNext(newDLLN)
        nextDLLN!!.setDLLNPrev(newDLLN)
        this.count++
    }

    fun insertBack(data: T) { // 제일 끝에 원소 삽입
        val newDLLN = DLLN<T>(data)
        val prevDLLN = this.tail!!.getDLLNPrev()
        prevDLLN!!.setDLLNNext(newDLLN)
        newDLLN.setDLLNPrev(prevDLLN)
        this.tail!!.setDLLNPrev(newDLLN)
        newDLLN.setDLLNNext(this.tail!!)
        this.count++
    }

    fun removeFront(): T? { // 제일 앞 원소 추출 (제거)
        val tobeRemovedDLLN = this.head!!.getDLLNNext() // 실제 제거 원소 : head 다음 원소 (head : dummy)
        val nextDLLN = tobeRemovedDLLN!!.getDLLNNext()
        this.head!!.setDLLNNext(nextDLLN)
        nextDLLN!!.setDLLNPrev(this.head)
        this.count--

        return tobeRemovedDLLN.getDLLNData()
    }

    fun removeBack(): T? { // 제일 뒤 원소 추출 (제거)
        val tobeRemovedDLLN = this.tail!!.getDLLNPrev() // 실제 제거 원소 : taill 이전 원소 (tail : dummy)
        val prevDLLN = tobeRemovedDLLN!!.getDLLNPrev()
        this.tail!!.setDLLNPrev(prevDLLN)
        prevDLLN!!.setDLLNNext(this.tail)
        this.count--

        return tobeRemovedDLLN.getDLLNData()
    }

    fun removeUsingData(data: T): T? { // 원솟값으로 data 를 가진 원소 추출
        var currentDLLN = this.head!!.getDLLNNext()
        while (!currentDLLN!!.equals(this.tail)) {
            if (currentDLLN.getDLLNData()!!.equals(data)) {
                // 현재 원소 - 원솟값과 매개변수 data 값이 동일 => 추출 (제거)
                val currentPrev: DLLN<T>? = currentDLLN.getDLLNPrev()
                val currentNext: DLLN<T>? = currentDLLN.getDLLNNext()
                currentPrev!!.setDLLNNext(currentNext)
                currentNext!!.setDLLNPrev(currentPrev)
                count--

                return currentDLLN.getDLLNData()
            }
            else {
                currentDLLN = currentDLLN.getDLLNNext()
            }
        }

        println("'data (${data})' 원소를 찾지 못했습니다.")

        return null
    }

    fun getCount(): Int { // count 반환
        return this.count
    }

    fun print() { // 연결 리스트 출력 함수
        if (this.count == 0) {
            // 출력할 원소 x
            println("출력할 원소가 없습니다. // DLL is empty.")

            return
        }

        var currentDLLN: DLLN<T>? = this.head!!.getDLLNNext()
        print("[")
        while (currentDLLN != this.tail) {
            if (currentDLLN!!.getDLLNData() != null) {
                if (currentDLLN.getDLLNNext() != tail) {
                    // 마지막 원소 x
                    print("${currentDLLN.getDLLNData()}, ")
                }
                else {
                    // 마지막 원소 o
                    print("${currentDLLN.getDLLNData()}]")
                }
            }

            currentDLLN = currentDLLN.getDLLNNext()
        }

        println()
    }

}