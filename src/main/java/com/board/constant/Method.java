package com.board.constant;

/*
 * REST API 방식에서 사용할 수 있는 대표적인 HTTP 요청 메서드이다
 * (GET-자원조회, POST-자원생성, PUT-자원수정, PATCH-자원수정, DELETE-자원삭제)
 * 
 * Resource : 리소스는 서비스를 제공하는 시스템의 자원을 의미한다. REST API에서 URI는 다음의 표와 같이 명사를 사용해서 자원만을 표현해야 한다.
 * 		insert, update와 같은 동사가 사용되면 REST 방식이 아니다. 무조건 HTTP 요청 메서드로 정의한다!
 * 		Ex] Post/board/insert => X 잘못된 표현 X
 * 			Post/board => O 올바른 표현 O
 * 
 * ※슬래시(/)를 사용해서 계층 관계를 나타낸다. URI의 마지막에 슬래시가 포함되면 다음 계층이 있는것으로 오해할 수 있기 때문에
 * 	URI 끝 부분에는 슬래시를 붙이면 안된다. 
 * 	또한 URI가 길어질때 밑줄보다는 하이픈 사용을 권장한다. (밑줄은 잘 안보이기 때문에)
*/

public enum Method {

	GET, POST, PUT, PATCH, DELETE

}