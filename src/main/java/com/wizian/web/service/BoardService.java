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

    public List<Map<String, Object>> counProfile() {
        return boardDAO.counProfile();
    }
    
    public List<String> getCounselors() {
        return boardDAO.getCounselors();
    }
    
    

    public List<BoardDTO> boardList() {
        return boardDAO.boardList();
    }

    public List<BoardDTO> getBoards(int bbsSn, String userId, int pageSize, int offset) {
        System.out.println("getBoards - bbsSn: " + bbsSn + ", userId: " + userId + ", pageSize: " + pageSize + ", offset: " + offset);
        return boardDAO.selectPagedBoards(bbsSn, userId, pageSize, offset);
    }

    public int getTotalBoardCount(int bbsSn, String userId) {
        System.out.println("getTotalBoardCount - bbsSn: " + bbsSn + ", userId: " + userId);
        return boardDAO.countAllBoards(bbsSn, userId);
    }

    public BoardDTO detail(int no) {
        return boardDAO.detail(no);
    }

    public List<BoardDTO> board(int boardNo) {
        return boardDAO.board(boardNo);
    }
    
    public int submitReply(Map<String, Object> map) {
        System.out.println("submitReply - map: " + map); // 로그 추가
        return boardDAO.submitReply(map);
    }


    public List<BoardDTO> getReplies(int postId) {
        return boardDAO.getReplies(postId);
    }

    public int write(Map<String, Object> map) {
        map.put("mid", "apple"); // DB에 저장할 사용자 ID
        map.put("ip", util.getIP()); // 사용자 IP 가져오기
        return boardDAO.write(map);
    }
}
