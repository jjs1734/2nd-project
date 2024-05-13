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

import com.wizian.web.service.EmploymentService;


@Controller
public class EmploymentController {
	
	@Autowired
	private EmploymentService employmentService;
	
	/*
	 * @GetMapping("/employment") public String index() {
	 * 
	 * return "employment";
	 * 
	 * }
	 */
	
	@GetMapping("/employment")
	public String employment(Model model) {
		
		List<Map<String, Object>> list = employmentService.boardList();
		model.addAttribute("boardList", list);
		return "employment";
	}
	
	@GetMapping("/empCounProfile")
	public String empCounProfile(Model model) {
		
		List<Map<String, Object>> list = employmentService.empCounProfile();
		model.addAttribute("empCounProfile", list);
		return "empCounProfile";
	}
			
	@PostMapping("/employApply")
	public String selectEmpCoun(@RequestParam("CSL_NO") String cslNo, Model model) {
	    System.out.println(cslNo);
	    List<Map<String, Object>> list = employmentService.selectEmpCoun(cslNo);
	    System.out.println(list);
	    model.addAttribute("selectEmpCoun", list);
	    return "empApply"; // 절대 경로로 수정
	}
	
	@GetMapping("/empCal")
	public String empCal() {
		return "empCal";
	}
	
	@PostMapping("/fullcalendar")
	@ResponseBody
	public String fullCal() {
		return "fullcalendar";
	}

}