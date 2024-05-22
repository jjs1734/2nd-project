package com.wizian.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wizian.web.dao.AdminDAO;
import com.wizian.web.dao.BoardDAO;
import com.wizian.web.dao.GroupDAO;
import com.wizian.web.dto.BoardDTO;
import com.wizian.web.dto.GroupDTO;

@Service
public class AdminService {

	@Autowired
	private AdminDAO adminDAO;

	public List<Map<String, Object>> studentList() {
		return adminDAO.studentList();
	}

	public List<Map<String, Object>> getGcounList() {
		return adminDAO.getGcounList();
	}

	public List<Map<String, Object>> getGcounStudList(String gcounCd) {
		return adminDAO.getGcounStudList(gcounCd);
	}

	public List<Map<String, Object>> getEcounList() {
		return adminDAO.getEcounList();
	}

	public List<Map<String, Object>> getBoardListByStudentNo(String studentNo) {
		return adminDAO.getBoardListByStudentNo(studentNo);
	}

	public BoardDTO getPostDetail(int postId) {
		return adminDAO.getPostDetail(postId);
	}

	public List<BoardDTO> getReplies(int postId) {
		return adminDAO.getReplies(postId);
	}
	
	public int getIncompleteConsultCount(String studentNo) {
        return adminDAO.countIncompletePostsByStudentNo(studentNo);
    }
	
	



}