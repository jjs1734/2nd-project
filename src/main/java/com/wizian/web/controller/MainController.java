package com.wizian.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.wizian.web.service.GroupService;

@Controller
public class MainController {
	
	@Autowired
	private GroupService groupService;
	
	@GetMapping("/main")
	public String main(Model model){
		
		List<String> mainGroupImg = groupService.mainGroupImg(); 
		model.addAttribute("mainGroupImg", mainGroupImg);
		
		return "main";
	}

	@GetMapping("/maintest")
	public String maintest(){
		return "maintest";
	}

}
