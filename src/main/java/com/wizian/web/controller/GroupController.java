package com.wizian.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wizian.web.dto.GroupDTO;
import com.wizian.web.service.GroupService;

@Controller
public class GroupController {
	
	@Autowired
	private GroupService groupService;
	
	@GetMapping("/group")
	public String group(Model model){
		
		List<Map<String, String>> groupList = groupService.groupList(); 
		
		model.addAttribute("groupList", groupList);
		
		return "group";
	}
	
	@ResponseBody
	@PostMapping("/groupEnroll")
	public List<Map<String, Object>> groupEnroll(@RequestParam("gcounCD") String gcounCD, @RequestParam("studNum") String studNum) {
		
		System.out.println("엔롤 컨트롤러 실행");
		List<Map<String, Object>> groupEnroll = groupService.groupEnroll(gcounCD, studNum);
		
		return groupEnroll;
	}
}