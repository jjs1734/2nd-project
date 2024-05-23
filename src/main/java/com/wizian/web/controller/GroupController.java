package com.wizian.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wizian.web.dto.GroupDTO;
import com.wizian.web.dto.UserDTO;
import com.wizian.web.service.GroupService;
import com.wizian.web.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class GroupController {
	
	@Autowired
	private GroupService groupService;
	@Autowired
	private UserService userService;
	
	@GetMapping("/group")
	public String group(HttpSession session, Model model){
		
		List<Map<String, String>> groupList = groupService.groupList(); 
		
		String userId = (String) session.getAttribute("userId");
		String grade = (String) session.getAttribute("grade");
		
		System.out.println(userId);
		if (userId == null) return "redirect:/login"; // 로그인 세션이 없다면 로그인 페이지로 이동
		if (!grade.equals("학생")) return "redirect:/main";
		
		UserDTO userInfo = userService.userInfo(userId);
		
		model.addAttribute("groupList", groupList); // 집단 상담 목록
		model.addAttribute("userInfo", userInfo); // 사용자 정보
		
		return "group";
	}
	
	@ResponseBody
	@PostMapping("/groupEnroll")
	public int groupEnroll(GroupDTO groupDto, HttpSession session) {
		
		String userId = (String) session.getAttribute("userId");
		String grade = (String) session.getAttribute("grade");
		int result = 0;
		
		if(userId != null && grade.equals("학생")) {
			result = groupService.groupEnroll(groupDto);
		}
		
		return result;
	}
}