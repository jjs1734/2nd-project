package com.wizian.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wizian.web.dto.EventData;
import com.wizian.web.dto.UserDTO;
import com.wizian.web.service.EmploymentService;
import com.wizian.web.service.MemberService;
import com.wizian.web.service.UserService;

import ch.qos.logback.classic.pattern.Util;
import jakarta.servlet.http.HttpSession;


@Controller
public class EmploymentController {
	
	@Autowired
	private EmploymentService employmentService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private com.wizian.web.util.Util util;
	
	@GetMapping("/employment")
	public String employment(HttpSession session, Model model) {
		
		String userId = (String) session.getAttribute("userId");
		
		if(userId != null) {
			model.addAttribute("userId", userId);
			//System.out.println("로그인되어있음");
			List<Map<String, Object>> list = employmentService.loginInfo(userId);
			System.out.println("로그인 정보를 받아옵니다. " + list);
			model.addAttribute("boardList", list);
			return "employment";
		} else {
			System.out.println("세션이 존재하지 않음");
			return "main";
		}
	}
	
	@GetMapping("/empCounProfile")
	public String empCounProfile(HttpSession session, Model model) {
			
		List<Map<String, Object>> list = employmentService.empCounProfile();
		System.out.println(list);
		model.addAttribute("empCounProfile", list);
		return "empCounProfile";
	}
			
	@PostMapping("/employApply")
	public String selectEmpCoun(HttpSession session, @RequestParam("CSL_NO") String cslNo, Model model) {
		String userId = (String) session.getAttribute("userId");
		Map<String,Object> empUserInfo = userService.empUserInfo(userId);
		
		 // 전화번호를 변환합니다.
	    String phoneNumber = (String) empUserInfo.get("TELNO");
	    String formattedPhoneNumber = util.formatPhoneNumber(phoneNumber);

	    // 변환된 전화번호를 다시 empUserInfo에 추가합니다.
	    empUserInfo.put("TELNO", formattedPhoneNumber);
		
		System.out.println(cslNo);
	    System.out.println("첫번째 employApply" + userId);
	    Map<String, Object> list = employmentService.selectEmpCoun(cslNo);
	    System.out.println("employApply 에러나는 부분" + list);
	    model.addAttribute("empUserInfo", empUserInfo);
	    model.addAttribute("selectEmpCoun", list);
	    model.addAttribute("userId", userId);
	    return "empApply"; // 절대 경로로 수정
	}
	
	@GetMapping("/employApply")
	public String selectEmpCal(@RequestParam("CSL_NO") String cslNo, Model model) {
		System.out.println("두번째 employApply");
		System.out.println("두번째 CSL_NO : " + cslNo);
		Map<String, Object> list = employmentService.selectEmpCoun(cslNo);
		Map<String, Object> list12 = employmentService.calSelectApply(cslNo);
		System.out.println(list12);
		model.addAttribute("selectEmpCoun", list);
		model.addAttribute("selectEmpCoun1", list12);
		return "empApply"; // 절대 경로로 수정
	}
	
	@GetMapping("/empCal")
	public String empCal(@RequestParam("CSL_NO") String cslNo, Model model) {
		System.out.println(cslNo);
		//String cslNoo = employmentService.updateCal(cslNo);
		List<Map<String, Object>> cslNoo = employmentService.updateCal(cslNo);
		System.out.println(cslNoo);
		model.addAttribute("cslNoo", cslNoo);
		return "empCal";
	}
	
	@PostMapping("/empCalList")
	@ResponseBody // JSON 형식으로 데이터를 반환하기 위해 사용
	public List<Map<String, Object>> empCalList(@RequestParam("cslNum") String cslNo1) {
		System.out.println("/empCalList는" + employmentService.empCalList(cslNo1));
	    return employmentService.empCalList(cslNo1);
	}

	/* empApply에서 제출 버튼 누를때 db에 입력(최종) */
	@PostMapping("/insertApply")
	public String insertApply(HttpSession session, UserDTO userDTO, @ModelAttribute EventData eventData) {
		System.out.println("제출 잘 됐니1?" + eventData.getEmpDate());
		System.out.println("제출 잘 됐니2?" + eventData.getEmpContent());
		System.out.println("제출 잘 됐니3?" + eventData.getEmpTime());
		System.out.println("제출 잘 됐니4?" + eventData.getCSL_NO());
		
		String userId = (String) session.getAttribute("userId");
		System.out.println(userId);
		//Map<String, Object> empUserInfo = memberService.empUserInfo(userId);
		//System.out.println("나와야 하는 값들임" + empUserInfo);
		
		if(userId != null) {
			eventData.setUserId(userId);
			employmentService.insertCalFinal(eventData);
			employmentService.insertTabFinal(eventData);
			System.out.println("입력 완료");
			return "employment";
		} else {
			return "login";
		}
		
	}
}