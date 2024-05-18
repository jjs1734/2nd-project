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
		System.out.println("서비스: 아이디값" + memberDTO.LOGIN_ID + memberDTO.PW);
		System.out.println("서비스 String pw값 : " + pw);
		System.out.println("서비스 DTO pw값 : " + memberDTO.PW);
		if (memberDTO.PW.equals(pw)) {
			System.out.println("서비스 비밀번호 일치 확인 테스트: " + memberDTO.LOGIN_ID);
			return memberDTO.LOGIN_ID;
		}
		return null;
	}
	
	
}
