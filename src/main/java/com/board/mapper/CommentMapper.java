package com.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.domain.CommentDTO;

@Mapper
public interface CommentMapper {

	//댓글 등록
	public int insertComment(CommentDTO params);

	//댓글 상세내용 조회
	public CommentDTO selectCommentDetail(Long idx);

	//댓글 수정
	public int updateComment(CommentDTO params);

	//댓글 삭제(실제로 삭제하는게 아닌 delete_yn 컬럼의 상태를 Y로 지정한다.)
	public int deleteComment(Long idx);

	//특정 게시글에 포함된 댓글목록 조회
	public List<CommentDTO> selectCommentList(CommentDTO params);

	//특정 게시글에 포함된 댓글개수 조회 
	public int selectCommentTotalCount(CommentDTO params);

}
