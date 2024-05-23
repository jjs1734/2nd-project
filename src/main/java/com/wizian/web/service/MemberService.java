package com.wizian.web.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wizian.web.dao.MemberDAO;
import com.wizian.web.dto.MemberDTO;

@Service
public class MemberService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	public MemberDTO login(String id, String pw) {
		MemberDTO memberDTO = memberDAO.getMemberById(id);
		if (memberDTO.getPW().equals(pw)) {
			return memberDTO;
		}
		
		return null;
	}
	
	// 취업상담: 신진수
	public Map<String, Object> empUserInfo(String userId) {
		return memberDAO.empUserInfo(userId);
	}

	
}
