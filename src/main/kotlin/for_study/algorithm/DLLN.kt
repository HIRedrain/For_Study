/*
* Algorithm - DLLN (Doubly Linked List Node)
* 알고리즘 - 리스트 공부
* 최초 작성 일자 : 2025.07.07
*
* ========================================================
* 프로그램 수정 / 보완 이력
* ========================================================
* 날짜        수정 / 보완 내용
* ========================================================
* 2025.07.12  최초 작성 : 이중 연결 리스트
* 2025.07.12  함수 이름 수정 (Prev 관련 함수 - N 추가)
* ========================================================
*/

package for_study.algorithm

class DLLN<T> {
    var data: T? = null // 값
    var next: DLLN<T>? = null // 다음 노드
    var prev: DLLN<T>? = null // 이전 노드

    constructor() { // 기본 생성자
        this.data = null
        this.next = null
        this.prev = null
    }

    constructor(data: T) { // 생성자
        this.data = data
        this.next = null
        this.prev = null
    }

    fun getDLLNData(): T? { // data 값 반환
        return data as T
    }

    fun setDLLNData(data: T?) { // data 설정
        this.data = data
    }

    fun getDLLNNext(): DLLN<T>? { // next 반환
        return this.next
    }

    fun setDLLNNext(next: DLLN<T>?) { // next 설정
        this.next = next!! // !! : Not-null assertion operator (double bang) - nullable 타입을 강제로 non-null 타입으로 변환 시 사용
    }

    fun getDLLNPrev(): DLLN<T>? { // prev 반환
        return this.prev
    }

    fun setDLLNPrev(prev: DLLN<T>?) { // prev 설정
        this.prev = prev!!
    }

}