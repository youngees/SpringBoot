package com.board.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.board.adapter.GsonLocalDateTimeAdapter;
import com.board.domain.CommentDTO;
import com.board.service.CommentService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@RestController
public class CommentController {

	@Autowired
	private CommentService commentService;

	/*
	 * RequestBody : 클라이언트가 입력한 것들을 JSON문자열로 전송하면 서버는 JSON문자열을 파라미터로 받는데, RequestBody는 전달받은 JSON 문자열을 객체로 변환한다.
	 * 				객체로 변환된 JSON은 DTO 클래스의 객체인 params에 매핑된다.  
	 * @PathVariable : @RequestParam과 유사한 기능, REST 방식에서 리소스를 표현하는데 사용된다. URI에 파라미터로 전달받을 변수를 지정할 수 있다.
	*/
	@RequestMapping(value = { "/comments", "/comments/{idx}" }, method = { RequestMethod.POST, RequestMethod.PATCH })
	public String registerComment(@PathVariable(value = "idx", required = false) Long idx, @RequestBody final CommentDTO params) {

		JsonObject jsonObj = new JsonObject();

		try {
			boolean isRegistered = commentService.registerComment(params);
			jsonObj.addProperty("result", isRegistered);

		} catch (DataAccessException e) {
			jsonObj.addProperty("message", "데이터베이스 처리 과정에 문제가 발생하였습니다.");

		} catch (Exception e) {
			jsonObj.addProperty("message", "시스템에 문제가 발생하였습니다.");
		}

		return jsonObj.toString();
	}

	@GetMapping(value = "/comments/{boardIdx}")
	public JsonObject getCommentList(@PathVariable("boardIdx") Long boardIdx, @ModelAttribute("params") CommentDTO params) {

		JsonObject jsonObj = new JsonObject();

		List<CommentDTO> commentList = commentService.getCommentList(params);
		if (CollectionUtils.isEmpty(commentList) == false) {
			Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter()).create();
			JsonArray jsonArr = gson.toJsonTree(commentList).getAsJsonArray();
			jsonObj.add("commentList", jsonArr);
		}

		return jsonObj;
	}

	@DeleteMapping(value = "/comments/{idx}")
	public JsonObject deleteComment(@PathVariable("idx") final Long idx) {

		JsonObject jsonObj = new JsonObject();

		try {
			boolean isDeleted = commentService.deleteComment(idx);
			jsonObj.addProperty("result", isDeleted);

		} catch (DataAccessException e) {
			jsonObj.addProperty("message", "데이터베이스 처리 과정에 문제가 발생하였습니다.");

		} catch (Exception e) {
			jsonObj.addProperty("message", "시스템에 문제가 발생하였습니다.");
		}

		return jsonObj;
	}

}
