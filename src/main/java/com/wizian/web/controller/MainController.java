package com.wizian.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wizian.web.service.GroupService;
import com.wizian.web.service.MainService;
import com.wizian.web.util.Util;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
	
	@Autowired
	private GroupService groupService;
	
	@Autowired
	private MainService mainService;
	
	@Autowired
	private Util util;
	
	@GetMapping("/main")
	public String main(HttpSession session, Model model){
		
		String userId = (String) session.getAttribute("userId");
		
		if(userId != null) {
			model.addAttribute("userId", userId);
		} else {
			System.out.println("세션이 존재하지 않음");
		}
		
		
		List<String> mainGroupImg = groupService.mainGroupImg(); 
		model.addAttribute("mainGroupImg", mainGroupImg);
		
		return "main";
	}
	
	
	@GetMapping("/mypage")
	public String mypage(HttpSession session, Model model) {
		
		String userId = (String) session.getAttribute("userId");
		//String grade = (String) session.getAttribute("grade");
		System.out.println(userId);
		//System.out.println(grade);
		if (userId == null) {
			return "redirect:/login";
		}
//		if (grade != "학생") {
//			return "redirect:/main";
//		}
		
		Map<String, Object> selectMyInfo = groupService.selectMyInfo(userId);
		System.out.println(selectMyInfo);
		model.addAttribute("selectMyInfo", selectMyInfo);
		
		return "mypage";
	}
	
	
	@PostMapping("/updateMyInfo")
	public ResponseEntity<String> updateMyInfo(HttpSession session, @RequestBody Map<String, String> data) {
	    // 받은 데이터 출력
		String userId = (String) session.getAttribute("userId");
		System.out.println("@@");
		System.out.println(data);
		String key = data.get("key");
	    String value = data.get("value");
	    //System.out.println("Received data: key=" + key + ", value=" + value);
	    //groupService.updateMyInfo(key, value);
	    // 여기서 데이터를 이용하여 업데이트 로직을 수행합니다.
		//groupService.updateUserInfo(data);
		System.out.println(userId);
		
		if ("STUD_EMAIL_ADDR".equals(key)) {
			groupService.updateEmail(userId, value);
			System.out.println("!!");
	    } else if ("STUD_GEN".equals(key)) {
	    	groupService.updateGender(userId, value);
	    } else if ("TELNO".equals(key)){
	    	groupService.updateTelNo(userId, value);
	    }
	    return ResponseEntity.ok("Update successful");
	}
	 
	@PostMapping("/updateMyInfoEml")
    public ResponseEntity<String> updateMyInfoEml(HttpSession session, @RequestBody String value) {
		String userId = (String) session.getAttribute("userId");
		System.out.println(userId);
        System.out.println("Received email: " + value);
        groupService.updateEmail(userId, value);
        System.out.println("!!!");
        return ResponseEntity.ok("Email update successful");
    }

    @PostMapping("/updateMyInfoTel")
    public ResponseEntity<String> updateMyInfoTel(HttpSession session, @RequestBody String value) {
    	String userId = (String) session.getAttribute("userId");
        System.out.println("Received telephone number: " + value);
        groupService.updateTelNo(userId, value);
        return ResponseEntity.ok("Telephone number update successful");
    }

    @PostMapping("/updateMyInfoGen")
    public ResponseEntity<String> updateMyInfoGen(HttpSession session, @RequestBody String value) {
    	String userId = (String) session.getAttribute("userId");
        System.out.println("Received gender: " + value);
        groupService.updateGender(userId, value);
        return ResponseEntity.ok("Gender update successful");
    }
	
	@GetMapping("/intro")
	public String intro() {	
		return "intro";
	}
	
	@GetMapping("/mycounlist")
	public String myCounList(HttpSession session, Model model) {
		String userId = (String) session.getAttribute("userId");	
		if (userId == null) {
			return "redirect:/login";
		}
		
		System.out.println(userId);
		
		model.addAttribute(userId, userId);
		return "mycounlist";
	}
	
	@ResponseBody
	@PostMapping("/allCounList")
	public /*List<Map<String, Object>>*/void allCounList(@RequestParam("selectedValue") String selectedValue, @RequestParam("userId") String userId){
		System.out.println(selectedValue);
		System.out.println(userId);
		int selectValue = util.str2Int(selectedValue);
		List<Map<String, Object>> counList = new ArrayList<Map<String,Object>>();
		
		switch (selectValue) {
        case 1: // 교수상담
        	counList = mainService.pfCounList(userId);
            break;
        case 2: // 집단상담
        	counList = mainService.gCounList(userId);
            break;
        case 3: // 취업상담
        	counList = mainService.eCounList(userId);
            break;
        case 4: // 심리상담
        	counList = mainService.psyCounList(userId);
            break;
        case 5: // 개인상담
        	counList = mainService.inCounList(userId);
            break;
        default:
            break;
    }
		
		
		
		
		//return counList;
	}
	
	
}
