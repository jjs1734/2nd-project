package com.wizian.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.wizian.web.service.GroupService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
	
	@Autowired
	private GroupService groupService;
	
	@GetMapping("/main")
	public String main(HttpSession session, Model model){
		
		String userId = (String) session.getAttribute("userId");
		
		if(userId != null) {
			model.addAttribute("userId", userId);
		} else {
			System.out.println("세션이 존재하지 않음");
		}
		
		
		List<String> mainGroupImg = groupService.mainGroupImg(); 
		model.addAttribute("mainGroupImg", mainGroupImg);
		
		return "main";
	}
	
	
	@GetMapping("/mypage")
	public String mypage(HttpSession session, Model model) {
		
		String userId = (String) session.getAttribute("userId");
		System.out.println(userId);
		Map<String, Object> selectMyInfo = groupService.selectMyInfo(userId);
		model.addAttribute("selectMyInfo", selectMyInfo);
		
		return "mypage";
	}
	
	@GetMapping("/intro")
	public String intro() {
		
		
		return "intro";
	}
}
