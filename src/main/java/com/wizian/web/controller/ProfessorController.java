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

import com.wizian.web.dto.ProfessorDTO;
import com.wizian.web.dto.UserDTO;
import com.wizian.web.service.ProfessorService;
import com.wizian.web.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProfessorController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProfessorService pfService;

	@GetMapping("/pfCoun")
	public String pfCounRequest(HttpSession session, Model model){
		//세션 체크
		String userId = (String) session.getAttribute("userId");
		System.out.println(userId);
		if (userId == null) return "redirect:/login";
		
		//로그인한 학생 정보
		UserDTO userInfo = userService.userInfo(userId);
		model.addAttribute("userInfo", userInfo); // 사용자 정보
		
		//교수리스트 전송
		System.out.println(userInfo.getPF_NO());
		String pf_no = userInfo.getPF_NO();
		String depart = userInfo.getSCSBJT_NM();
		
		ProfessorDTO pfInfo = pfService.pfInfo(pf_no);//지도교수 정보
		System.out.println("교수이름: "+ pfInfo.getPF_NM());
		System.out.println("학과: "+ pfInfo.getSCSBJT_NM());
		model.addAttribute("pfInfo", pfInfo);
		List<Map<String, Object>> pfList = pfService.pfList(depart);
		model.addAttribute("pfList", pfList);
		
		return "pfCoun";
	}
	
	@GetMapping("/pfCalenderTest")
	public String pfCalenderTest() {
		return "pfCalenderTest";
	}
	
	/*
	 * @GetMapping("/pfCoun2") public String pfCoun2(HttpSession session, Model
	 * model) { //세션 체크 String userId = (String) session.getAttribute("userId");
	 * //System.out.println(userId); if (userId == null) return "redirect:/login";
	 * 
	 * //로그인한 학생 정보 UserDTO userInfo = userService.userInfo(userId);
	 * model.addAttribute("userInfo", userInfo); // 사용자 정보
	 * 
	 * //교수리스트 전송 //System.out.println(userInfo.getPF_NO()); String pf_no =
	 * userInfo.getPF_NO(); String depart = userInfo.getSCSBJT_NM();
	 * 
	 * ProfessorDTO pfInfo = pfService.pfInfo(pf_no);//지도교수 정보
	 * //System.out.println("교수이름: "+ pfInfo.getPF_NM());
	 * //System.out.println("학과: "+ pfInfo.getSCSBJT_NM());
	 * model.addAttribute("pfInfo", pfInfo); List<Map<String, Object>> pfList =
	 * pfService.pfList(depart); model.addAttribute("pfList", pfList); return
	 * "pfCoun2"; }
	 */
	
	@PostMapping("/getSData")
	@ResponseBody
	public List<Map<String, Object>> getSchedule(@RequestParam("pf_no")String pf_no) {
		//System.out.println("전달 받은 교수 번호: "+pf_no);
		return pfService.getScList(pf_no);
	};
	
}
