package com.wizian.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wizian.web.dto.MemberDTO;
import com.wizian.web.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/login")
	public String login(HttpSession session, Model model) {
		String userId = (String) session.getAttribute("userId");
		
		if(userId != null) { // 로그인 된 상태
		
			return "redirect:/main";
		}
		
		return "login";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam("id") String id, @RequestParam("pw") String pw, HttpSession session) {
		//System.out.println("아이디 입력값: " + id + " 비밀번호 입력값 : " + pw);
		MemberDTO user = memberService.login(id, pw);
		if(user.getLOGIN_ID() == null) {
			//System.out.println("로그인 실패");
			return "redirect:/login";
		}
		session.setAttribute("userId", user.getLOGIN_ID());
		//세션에 등급 저장
		session.setAttribute("grade", user.getADMIN_YN());
		
		session.setAttribute("userNo", user.getUSER_NO());

		if(user.getADMIN_YN().equals("학생")) {
			return "redirect:/main";
		} else {
			return "redirect:/admin/main";
		}

	}
	
	@PostMapping("/logout")
	public String logout(HttpSession session) {
		String grade = (String) session.getAttribute("grade");
		if (grade.equals("학생")) {
			session.invalidate();
			return "redirect:/main";
		} else {
			session.invalidate();
			return "redirect:/admin/main";
		}
	
	}
	
}