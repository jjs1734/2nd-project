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

import com.wizian.web.service.AdminService;

@Controller
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/admin/main")
	public String mypage() {
		
		return "admin/main";
	}
	
	@GetMapping("/admin/gcoun")
	public String gcoun() {
		
		return "admin/gcoun";
	}
	
	@GetMapping("/admin/student")
	public String student() {
		
		
		return "/admin/student";
	}
	
	@ResponseBody
	@GetMapping("/admin/studentList")
	public List<Map<String, Object>> readData() {
		List<Map<String, Object>> studentList = adminService.studentList();
		return studentList;
	}
	
	@ResponseBody
	@PostMapping("/admin/getGcounStudList")
	public List<Map<String, Object>> getGcounStudList(@RequestParam("gcounCd") String gcounCd) {
		
		List<Map<String, Object>> getGcounStudList = adminService.getGcounStudList(gcounCd);
		return getGcounStudList;
	}
	
	@ResponseBody
	@GetMapping("/admin/getGcounList")
	public List<Map<String, Object>> getGcounList() {
		List<Map<String, Object>> getGcounList = adminService.getGcounList();
		
		return getGcounList;
	}
}
