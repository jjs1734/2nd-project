package com.wizian.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.wizian.web.dao.BoardDAO;
import com.wizian.web.dto.BoardDTO;
import com.wizian.web.util.Util; // 이 클래스의 정의가 필요

@Service
public class BoardService {
	
	@Autowired
	private BoardDAO boardDAO;

	@Autowired
	private Util util; // Util 클래스를 사용하려면, 이 클래스를 정의하고 빈으로 등록해야 함

	
	
	public List<BoardDTO> boardList() {
		return boardDAO.boardList();
	}

	public List<BoardDTO> getBoards(int pageNum, int pageSize) {
        int offset = (pageNum - 1) * pageSize;
        return boardDAO.selectPagedBoards(pageSize, offset);
    }
	public int getTotalBoardCount() {
	    return boardDAO.countAllBoards(); // 전체 게시물 수를 반환하는 DAO 메소드
	}
	
	public BoardDTO detail(int no) {
		return boardDAO.detail(no);
	}

	public List<BoardDTO> board(int boardNo){
		return boardDAO.board(boardNo);
	}
	
	public int write(Map<String, Object> map) {
		map.put("mid", "apple"); // DB에 저장할 사용자 ID
		map.put("ip", util.getIP()); // 사용자 IP 가져오기
		
		return boardDAO.write(map);
			
}
	
	 public int submitReply(Map<String, Object> replyData) {
	        return boardDAO.submitReply(replyData);
	    }
	 
	 
	 public List<BoardDTO> getRepliesForPost(int postId) {
	        return boardDAO.getReplies(postId);
	    }
	
}