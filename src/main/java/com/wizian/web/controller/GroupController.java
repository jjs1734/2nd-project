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
	public ResponseEntity<String> groupEnroll(@RequestBody GroupDTO groupDto) {
		
		groupService.groupEnroll(groupDto);
		
		return ResponseEntity.ok("컨트롤러 성공");
	}
}