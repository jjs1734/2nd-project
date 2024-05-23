package com.wizian.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.swing.plaf.multi.MultiFileChooserUI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wizian.web.dto.PfRsvDTO;
import com.wizian.web.dto.ProfessorDTO;
import com.wizian.web.dto.UserDTO;
import com.wizian.web.service.ProfessorService;
import com.wizian.web.service.UserService;
import com.wizian.web.util.Util;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProfessorController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProfessorService pfService;
	
	@Autowired
	private Util util;

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
		//System.out.println("교수이름: "+ pfInfo.getPF_NM());
		//System.out.println("학과: "+ pfInfo.getSCSBJT_NM());
		model.addAttribute("pfInfo", pfInfo);
		List<Map<String, Object>> pfList = pfService.pfList(depart);
		model.addAttribute("pfList", pfList);
		
		return "pfCoun";
	}
	
	@GetMapping("/pfCalenderTest")
	public String pfCalenderTest() {
		return "pfCalenderTest";
	}
	
	
	  @GetMapping("/pfCoun2") public String pfCoun2(HttpSession session, Model model) { 
		  //세션 체크 
		  String userId = (String) session.getAttribute("userId");
		  //System.out.println(userId); 
		  if (userId == null) return "redirect:/login";
		  
		  //로그인한 학생 정보 
		  UserDTO userInfo = userService.userInfo(userId);
		  model.addAttribute("userInfo", userInfo); // 사용자 정보
		  
		  //교수리스트 전송 
		  //System.out.println(userInfo.getPF_NO()); 
		  String pf_no = userInfo.getPF_NO(); String depart = userInfo.getSCSBJT_NM();
		  
		  ProfessorDTO pfInfo = pfService.pfInfo(pf_no);//지도교수 정보
		  //System.out.println("교수이름: "+ pfInfo.getPF_NM());
		  //System.out.println("학과: "+ pfInfo.getSCSBJT_NM());
		  model.addAttribute("pfInfo", pfInfo); 
		  List<Map<String, Object>> pfList = pfService.pfList(depart); 
		  model.addAttribute("pfList", pfList); 
		  return "pfCoun2"; 
	  }
	 
	
	@PostMapping("/getSData")
	@ResponseBody
	public List<Map<String, Object>> getSchedule(@RequestParam("pf_no")String pf_no) {
		//System.out.println("전달 받은 교수 번호: "+pf_no);
		return pfService.getScList(pf_no);
	};
	
	@PostMapping("/submitPFCoun")
	@ResponseBody
	public void pfCoun(
		@RequestParam("studentNo") String std_no,
		@RequestParam("professorNo") String pf_no,
		@RequestParam("pfs_no") String pfs_no,
		@RequestParam("selectedDate") String selectDate,
		@RequestParam("counselingContent") String pfContents,
		@RequestParam(value="files", required = false) MultipartFile[] files){
		//pfs_no 스케쥴 번호의 PF_SC_DEL '0'으로 update
		System.out.println("교수 스케쥴 번호: " + pfs_no);
		int pfsNo = util.str2Int(pfs_no);
		//int deleteCount = pfService.pfDelUpdate(pfsNo);
		//System.out.println("업데이트 성공한 행의 개수 : " + deleteCount);
		
		//상담 신청 테이블에 일부 7가지 데이터를 insert 하기
		//데이터 변환
		String[] dateTime = selectDate.split(" ");
		String dateStr = dateTime[0];
		//System.out.println(dateStr);
		String time = dateTime[1].substring(0,2);
		//System.out.println(time);
		
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = dateFormat.parse(dateStr);
			System.out.println(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		//데이터 저장
		PfRsvDTO pfRsv = new PfRsvDTO();
		pfRsv.setPF_NO(pf_no);
		pfRsv.setSTUD_NO(std_no);
		pfRsv.setPF_COUN_RSVT_YMD(date);
		pfRsv.setPF_COUN_RSVT_TIME(time);
		pfRsv.setPFS_NO(pfsNo);
		pfRsv.setPF_CONTENTS(pfContents);
		System.out.println(pf_no);
		System.out.println(std_no);
		System.out.println(date);
		System.out.println(time);
		System.out.println(pfsNo);
		System.out.println(pfContents);
		
		int pfCounInsertCoun=pfService.pfCounReservation(pfRsv);
		System.out.println("신청 성공: " + pfCounInsertCoun);
		
		//위 메소드 실행했을 때 생성되는 pk값 받아볼 수 있나 찾아보자
		//다른 방법있는지 고민해볼것.
		//files 파싱해서 파일DB에 삽입
		//if (files != null && files.length > 0) {
	   // } else {
	    //    System.out.println("No files uploaded.");
	    //}
		
	}
	
}
