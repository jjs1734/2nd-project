package com.wizian.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.wizian.web.service.EmploymentService;

@Controller
public class EmploymentController {
	
	@Autowired
	private EmploymentService employmentService;
	
	@GetMapping("/employment")
	public String index() {

		return "employment";
		
	}
	
	@GetMapping("/empApply")
	public String empApply(Model model) {
		List<Map<String, Object>> list = employmentService.boardList();
		return "empApply";
	}
	
}