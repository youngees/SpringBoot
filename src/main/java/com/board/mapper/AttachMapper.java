package com.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.domain.AttachDTO;

@Mapper
public interface AttachMapper {
	
	//파일정보 저장
	public int insertAttach(List<AttachDTO> attachList);

	//파일 상세정보 저장
	public AttachDTO selectAttachDetail(Long idx);

	//파일삭제
	public int deleteAttach(Long boardIdx);
	
	//특정 게시글에 포함된 파일목록 조회
	public List<AttachDTO> selectAttachList(Long boardIdx);

	//특정게시글에 포함된 파일개수 조회
	public int selectAttachTotalCount(Long boardIdx);

	//게시글 삭제 취소 
	public int undeleteAttach(List<Long> idxs);

}