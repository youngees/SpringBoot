package com.board;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import com.board.domain.BoardDTO;
import com.board.mapper.BoardMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootTest
class MapperTests {

	@Autowired
	private BoardMapper boardMapper;

	/*
	 * testOfInsert : 게시글 생성을 처리하는 메서드. 테이블 구조화 클래스인 BoardDTO의 객체를 생성하고, set 메서드를 이용해서 게시글 제목, 내용, 작성자를 지정한다.
	 * 				BoardMapper 인터페이스의 insertBoard 메서드의 인자로 게시글 정보가 저장된 params를 전달한다.
	*/
	@Test
	public void testOfInsert() {
		BoardDTO params = new BoardDTO();
		params.setTitle("1번 게시글 제목");
		params.setContent("1번 게시글 내용");
		params.setWriter("테스터");

		int result = boardMapper.insertBoard(params);
		System.out.println("결과는 " + result + "입니다.");
	}
	
	/*
	 * testOfSelectDetail : BoardDTO 타입의 객체 변수의 board에 selectBoardDetail 메서드의 결과를 저장한다. 
	 * 					인자로 지정된 (long) 1은 앞에서 추가한 1번 게시글의 PK에 해당하는 idx를 의미한다. try 문에서는 board에 저장된 게시글 정보를 
	 * 					Jackson 라이브러리를 이용해서 JSON 문자열로 변경한 뒤에 콘솔에 출력한다. 
	*/
	@Test
	public void testOfSelectDetail() {
		BoardDTO board = boardMapper.selectBoardDetail((long) 1);
		try {
			//String boardJson = new ObjectMapper().writeValueAsString(board);
            String boardJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(board);

			System.out.println("=========================");
			System.out.println(boardJson);
			System.out.println("=========================");

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * testOfUpdate : 게시글 생성과 마찬가지로 BoardDTO 객체를 생성하고, set 메서드를 이용해 수정할 제목, 내용, 작성자, 게시글 번호를 지정한다.
	 * 				result에는 UPDATE쿼리가 실행된 횟수가 저장되며, if문 안의 로직은 testSelectDetail 메서드와 동일하다. 
	 * 				게시글이 수정되면 수정한 게시글 정보를 JSON 문자열로 출력한다. 
	*/
	@Test
	public void testOfUpdate() {
		BoardDTO params = new BoardDTO();
		params.setTitle("1번 게시글 제목을 수정합니다.");
		params.setContent("1번 게시글 내용을 수정합니다.");
		params.setWriter("홍길동");
		params.setIdx((long) 1);

		int result = boardMapper.updateBoard(params);
		if (result == 1) {
			BoardDTO board = boardMapper.selectBoardDetail((long) 1);
			try {
				//String boardJson = new ObjectMapper().writeValueAsString(board);
                String boardJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(board);

				System.out.println("=========================");
				System.out.println(boardJson);
				System.out.println("=========================");

			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * testOfDelete : deleteBoard 메서드의 인자로 게시글 번호(idx)를 지정한다. 
	*/
	@Test
	public void testOfDelete() {
		int result = boardMapper.deleteBoard((long) 1);
		if (result == 1) {
			BoardDTO board = boardMapper.selectBoardDetail((long) 1);
			try {
				//String boardJson = new ObjectMapper().writeValueAsString(board);
                String boardJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(board);

				System.out.println("=========================");
				System.out.println(boardJson);
				System.out.println("=========================");

			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
	}
	
	//게시글 여러개 작성
	@Test
	public void testMultipleInsert() {
		for (int i = 2; i <= 50; i++) {
			BoardDTO params = new BoardDTO();
			params.setTitle(i + "번 게시글 제목");
			params.setContent(i + "번 게시글 내용");
			params.setWriter(i + "번 게시글 작성자");
			boardMapper.insertBoard(params);
		}
	}
	
	/*
	 * testSelectList : boardTotalCount에 삭제되지 않은 전체 게시글의 수를 저장해서 카운팅 하고, 게시글이 한 개 이상이면 제네릭 타입의 변수인 boardList에
 * 					selectBoardList메서드의 실행 결과에 해당하는 게시글 목록을 저장한다. if문에서는 스프링에서 지원해주는 CollectionsUtil의 isEmpty 메서드를 이용해서
 * 					boardList가 비어있지 않은지 체크하고 forEach를 실행해서 boardlist에 저장된 게시글의 순서대로 게시글 제목, 내용, 작성자를 출력한다. 
	*/
//	@Test
//	public void testSelectList() {
//		int boardTotalCount = boardMapper.selectBoardTotalCount();
//		if (boardTotalCount > 0) {
//			List<BoardDTO> boardList = boardMapper.selectBoardList();
//			if (CollectionUtils.isEmpty(boardList) == false) {
//				for (BoardDTO board : boardList) {
//					System.out.println("=========================");
//					System.out.println(board.getTitle());
//					System.out.println(board.getContent());
//					System.out.println(board.getWriter());
//					System.out.println("=========================");
//				}
//			}
//		}
//	}

}