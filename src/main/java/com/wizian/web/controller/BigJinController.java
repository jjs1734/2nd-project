package com.wizian.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wizian.web.dto.BigJinDTO;
import com.wizian.web.service.BigJinService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class BigJinController {
	
	@Autowired
	private BigJinService homeService;
	
	@GetMapping("/home")
	public String home(Model model) {
		model.addAttribute("test", "테스트입니다.");
		List<Map<String, Object>> list = homeService.boardList();
		
		return "home";
	}
}