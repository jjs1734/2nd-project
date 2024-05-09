package com.wizian.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.wizian.web.service.GroupService;

@Controller
public class GroupController {
	
	@Autowired
	private GroupService groupService;
	
	@GetMapping("/group")
	public String group(Model model){
		List<Map<String, String>> gcList = groupService.geList();
		return "group";
	}

}
