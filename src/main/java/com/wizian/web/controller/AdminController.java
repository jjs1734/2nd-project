package com.wizian.web.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wizian.web.dto.AdminDTO;
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
	public String gcoun(Model model) {
		
		List<Map<String, Object>> getGcounCslList = adminService.getGcountCslList();
		model.addAttribute("cslList" ,getGcounCslList);
		
		
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
	public String getEcounStudModify(@RequestParam("") int counNum){
		
		return "as";
	}
	
//	@ResponseBody
//	@PostMapping("/admin/gcounEnroll")
//	public int gcounEnroll(AdminDTO adminDTO) {
//		System.out.println("컨트롤러 실행");
//		int gcounEnroll = adminService.gcounEnroll(adminDTO);
//		
//		return gcounEnroll;
//	}
	
	@ResponseBody
	@PostMapping("/admin/gcounEnroll")
	public int gcounEnroll( @RequestParam("CSL_NO") String cslNo,
            @RequestParam("GCOUN_NM") String gcounNm,
            @RequestParam("GCOUN_DT") LocalDateTime gcounDt,
            @RequestParam("GCOUN_DTL_CN") String place,
            @RequestParam("GCOUN_BGNG_DT") LocalDate gcounBgngDt,
            @RequestParam("GCOUN_DDLN_DT") LocalDate gcounDdlnDt,
            @RequestParam("GCOUN_LMT_COUNT") int gcounLmtCount,
            @RequestPart("file") MultipartFile file) throws Exception {
		
		// DTO 객체 생성 및 설정
        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setCSL_NO(cslNo);
        adminDTO.setGCOUN_NM(gcounNm);
        adminDTO.setGCOUN_DT(gcounDt);
        adminDTO.setGCOUN_DTL_CN(place);
        adminDTO.setGCOUN_BGNG_DT(gcounBgngDt);
        adminDTO.setGCOUN_DDLN_DT(gcounDdlnDt);
        adminDTO.setGCOUN_LMT_COUNT(gcounLmtCount);
		
		// 저장 경로 지정
        String path = System.getProperty("user.dir") + "/src/main/resources/static/gcounFiles";
        
		File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        
        // 랜덤 이름 생성
        UUID uuid = UUID.randomUUID();
        
        // 파일 이름 생성
        String fileName = uuid + "_" + file.getOriginalFilename();
        
        File saveFile = new File(path, fileName);
        file.transferTo(saveFile);
        
        adminDTO.setGCOUN_FILENM(fileName);
        adminDTO.setGCOUN_CONTS_CN("/gcounFiles/" + fileName);
        
        int gcounEnroll = adminService.gcounEnroll(adminDTO);
		
        return gcounEnroll;
	}
}
