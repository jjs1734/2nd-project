package com.wizian.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.wizian.web.dto.BoardDTO;
	
	@Repository
	@Mapper
	
	public interface BoardDAO{
		
		List<BoardDTO> boardList();
		BoardDTO detail(int no); 
		List<BoardDTO> board(int boardNo);
		int write(Map<String,Object> map);
	}
	

