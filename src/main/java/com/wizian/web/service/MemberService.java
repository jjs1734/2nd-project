package com.wizian.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wizian.web.dao.MemberDAO;
import com.wizian.web.dto.MemberDTO;

@Service
public class MemberService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	public String login(String id, String pw) {
		MemberDTO memberDTO = memberDAO.getMemberById(id);
		if (memberDTO.PW.equals(pw)) {
			return memberDTO.LOGIN_ID;
		}
		return null;
	}
	
	
}
