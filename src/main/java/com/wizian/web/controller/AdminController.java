package com.wizian.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	// 취업상담
	@GetMapping("/admin/ecoun")
	public String ecoun() {
		
		return "admin/ecoun";
	}
	
	@ResponseBody
	@GetMapping("/admin/getEcounList")
	public List<Map<String, Object>> getEcounList() {
		List<Map<String, Object>> getEcounList = adminService.getEcounList();
		System.out.println(getEcounList);
		
		return getEcounList;
	}
	
	@ResponseBody
	@PostMapping("/admin/getEcounStudList")
	public List<Map<String, Object>> getEcounStudList(@RequestParam("cslNo") String cslNo) {
		
		List<Map<String, Object>> getEcounStudList = adminService.getEcounStudList(cslNo);
		return getEcounStudList;
	}
	
	@ResponseBody
	@PostMapping("/admin/getEcounStudModify")
	public void getEcounStudModify(@RequestParam("counNum") int counNum,
            @RequestParam(value = "fieldName", required = false) String fieldName,
            @RequestParam(value = "fieldValue", required = false) String fieldValue) {

		
	Map<String, Object> map = new HashMap<>();
	
	if (fieldName.equals("COUN_CN")) {
		map.put("counNum", counNum);
		map.put("fieldValue", fieldValue);
		adminService.updateCounCn(map);
		//System.out.println("삽입성공1");
	}else if(fieldName.equals("EMP_COUN_YMD")) {
		map.put("counNum", counNum);
		map.put("fieldValue", fieldValue);
		adminService.updateCounYmd(map);
		//System.out.println("삽입성공2");
	}else if(fieldName.equals("EMP_COUN_CD")) {
		map.put("counNum", counNum);
		map.put("fieldValue", fieldValue);
		adminService.updateCounCd(map);
		//System.out.println("삽입성공3");
	}else {
		//EMP_STTS_CD
		map.put("counNum", counNum);
		map.put("fieldValue", fieldValue);
		adminService.updateSttsCd(map);
		//System.out.println("삽입성공4");
	}
	}

	//System.out.println(counNum);
	//System.out.println(fieldName);
	//System.out.println(fieldValue);
	
	 @PostMapping("/admin/registerEmpCounselor")
	    @ResponseBody
	    public String registerCounselor(@RequestBody Map<String, String> formData) {
	        String cd = formData.get("cd");
	        String cslNo = formData.get("cslNo");
	        String cslNm = formData.get("cslNm");
	        String email = formData.get("email");
	        String mobile = formData.get("mobile");
	        String scsbjtNm = formData.get("scsbjtNm");
	        
	        System.out.println(cd);
	        System.out.println(cslNm);
	        System.out.println(formData);
	        adminService.registerCounselor(formData);
	        adminService.registerEmpCounPro(formData);

	        // 데이터베이스에 상담사 등록 로직 추가
	        	
	        return "success";
	    }
	
}