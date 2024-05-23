package com.wizian.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.wizian.web.dto.BoardDTO;

@Repository
@Mapper

public interface BoardDAO {

	List<Map<String, Object>> counProfile();

	List<BoardDTO> boardList();

	BoardDTO detail(int no);

	List<BoardDTO> board(int boardNo);

	int write(Map<String, Object> map);

	int submitReply(Map<String, Object> map);

	List<BoardDTO> getReplies(int postId);

	int countAllBoards(@Param("bbsSn") int bbsSn, @Param("userId") String userId);

	List<BoardDTO> selectPagedBoards(@Param("bbsSn") int bbsSn, @Param("userId") String userId,
			@Param("pageSize") int pageSize, @Param("offset") int offset);

	List<String> getCounselors();
	
	 List<BoardDTO> findByPstComp(@Param("pstComp") String pstComp);
}
