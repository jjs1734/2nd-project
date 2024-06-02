package com.wizian.web.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.hibernate.resource.transaction.internal.SynchronizationRegistryStandardImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wizian.web.dto.AdminDTO;
import com.wizian.web.dto.BoardDTO;
import com.wizian.web.dto.PfRsvDTO;
import com.wizian.web.dto.EcounAdDTO;
import com.wizian.web.dto.PFSdataDTO;
import com.wizian.web.service.AdminService;
import com.wizian.web.service.BoardService;
import com.wizian.web.service.MainService;

import jakarta.servlet.http.HttpSession;

import jakarta.servlet.http.HttpSession;
import com.wizian.web.util.Util;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class AdminController {
	
	@Autowired
	private BoardService boardService;
	@Autowired
	private MainService mainService;
	@Autowired
	private AdminService adminService;
	@Autowired
	private Util util;
	
	@GetMapping({"/admin/main", "/admin"})
	public String mypage() {
		
		return "admin/main";
	}
	
	@GetMapping("/admin/gcoun")
	public String gcoun(Model model, HttpSession session) {
		
		String userId = (String) session.getAttribute("userId");
		String grade = (String) session.getAttribute("grade");
		System.out.println(userId);
		System.out.println(grade);
		
		if(userId == null || grade.equals("학생") ) {
			return "redirect:/admin/main";
		}
		
		if (userId != null) {
			if (grade.equals("관리자")) {
				List<Map<String, Object>> getGcounCslList = adminService.getGcounCslList();
				model.addAttribute("cslList" ,getGcounCslList);
			} else if (grade.equals("상담사")) {
				List<Map<String, Object>> getGcounCslList2 = adminService.getGcounCslList2(userId);
				model.addAttribute("cslList", getGcounCslList2);
			} else if (grade.equals("교수")) {
				return "redirect:/admin/main";
			}
			
		}
		
		return "admin/gcoun";
	}
	
	@GetMapping("/admin/student")
	public String student(HttpSession session) {
		String grade = (String) session.getAttribute("grade");
		
		if (grade.equals("상담사")||grade.equals("관리자")||grade.equals("교수")) {
			return "admin/student";
		} else {
			return "redirect:/admin/main";
		}
		
	}
	
	@ResponseBody
	@GetMapping("/admin/studentList")
	public List<Map<String, Object>> readData() {
		List<Map<String, Object>> studentList = adminService.studentList();
		return studentList;
	}
	
	@GetMapping("/admin/counselor")
	public String counselor(HttpSession session) {
		String grade = (String) session.getAttribute("grade");
		
		if (grade.equals("상담사")||grade.equals("관리자")) {
			return "/admin/counselor";
		} else {
			return "redirect:/admin/main";
		}
		
		
	}
	
	//교수 관리
	@GetMapping("/admin/professor")
	public String professor(HttpSession session) {
		String grade = (String) session.getAttribute("grade");
		
		if (grade.equals("교수")||grade.equals("관리자")) {
			return "admin/professor";
		} else {
			return "redirect:/admin/main";
		}
	}
	
	@ResponseBody
	@GetMapping("/admin/getPfList")
	public List<Map<String, Object>> getPfList(HttpSession session) {
		String userNo = (String) session.getAttribute("userNo");
		System.out.println("계정번호" + userNo);
		String grade = (String) session.getAttribute("grade");
		System.out.println("계정등급: "+ grade);
		if("교수".equals(grade)) {
			System.out.println("등급 교수에 해당하는 교수리스트가 실행됩니다.");
			List<Map<String, Object>> getPfList = adminService.getPfList(userNo);
			return getPfList;
		} else {
			System.out.println("등급 관리자에 해당하는 교수리스트가 실행됩니다.");
			List<Map<String, Object>> getPfList = adminService.getPfList();
			return getPfList;			
		}
	}
	
	@ResponseBody
	@PostMapping("/admin/getPfscList")
	public List<Map<String, Object>> getPfscList(@RequestParam("PF_NO") String pfNo) {
		System.out.println(pfNo);
		List<Map<String, Object>> getPfscList = adminService.getPfscList(pfNo);
		return getPfscList;
	}
	
	@ResponseBody
	@PostMapping("/admin/pfscEnroll")
	public int pfscEnroll(
		@RequestParam("PF_NO") String pfNo,
		@RequestParam("PF_DT") String pfDT){
		System.out.println("");
		System.out.println(pfNo);		
		System.out.println(pfDT);
		
		//date 타입으로 변환
		String[] dateTime = pfDT.split("T");
		String dateStr = dateTime[0];
		System.out.println(dateStr);
		String time = dateTime[1].substring(0,2);
		System.out.println(time);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = dateFormat.parse(dateStr);
			System.out.println(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		//DTO에 저장
		PFSdataDTO pfsDTO = new PFSdataDTO();
		pfsDTO.setPF_NO(pfNo);
		pfsDTO.setPF_DATE(date);
		pfsDTO.setPF_TIME_CD(time);
		
		int pfscResult = adminService.pfscEnroll(pfsDTO);
		if (pfscResult==1) { System.out.println("성공");
		} else {System.out.println("실패");}
		
		return pfscResult;
	}
	
	@ResponseBody
	@PostMapping("/admin/pfModify")
	public void pfModify(
		@RequestParam("counNum") String counNum,
        @RequestParam(value = "fieldName", required = false) String fieldName,
        @RequestParam(value = "fieldValue", required = false) String fieldValue) {
		Map<String, Object> map = new HashMap<>();
			System.out.println(fieldName);
			System.out.println(fieldValue);
		if (fieldName.equals("PF_NM")) {
			map.put("counNum", counNum);
			map.put("fieldValue", fieldValue);
			adminService.pfNmUpdate(map);
			//System.out.println("삽입성공1");
		}else if(fieldName.equals("PF_TELNO")) {
			map.put("counNum", counNum);
			map.put("fieldValue", fieldValue);
			adminService.pfTelUpdate(map);
			//System.out.println("삽입성공2");
		}else if(fieldName.equals("PF_EMAIL")) {
			map.put("counNum", counNum);
			map.put("fieldValue", fieldValue);
			adminService.pfEmailUpdate(map);
			//System.out.println("삽입성공3");
		}else {
			map.put("counNum", counNum);
			map.put("fieldValue", fieldValue);
			adminService.pfNcdUpdate(map);
			//System.out.println("삽입성공4");
		}
	}
	
	
	//지도교수 상담
	@GetMapping("/admin/pfcoun")
	public String pfcoun(HttpSession session) {
		String grade = (String) session.getAttribute("grade");
		
		if (grade.equals("교수")||grade.equals("관리자")) {
			return "admin/pfcoun";
		} else {
			return "redirect:/admin/main";
		}
		
	}
	
	@ResponseBody
	@PostMapping("/admin/pfcounEnroll")
	public int pfcounEnroll(
		@RequestParam("PF_NO") String pfNo,
		@RequestParam("STUD_NO") String studNo,
		@RequestParam("PF_CONTENTS") String pfContents,
		@RequestParam("PF_COUN_DT") String pfcounDT,
		@RequestParam("PF_COMMENT") String pfComm,
		@RequestParam("PF_COUN_STATE_CD") String pfStCD){
		//System.out.println(pfNo);
		//System.out.println(studNo);
		//System.out.println(pfContents);
		//System.out.println(pfcounDT);
		//System.out.println(pfComm);
		//System.out.println(pfStCD);
		
		//date 타입으로 변환
		String[] dateTime = pfcounDT.split("T");
		String dateStr = dateTime[0];
		System.out.println(dateStr);
		String time = dateTime[1].substring(0,2);
		System.out.println(time);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = dateFormat.parse(dateStr);
			System.out.println(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		//DTO에 저장
		PfRsvDTO pfRsv = new PfRsvDTO();
		pfRsv.setPF_NO(pfNo);
		pfRsv.setSTUD_NO(studNo);
		pfRsv.setPF_CONTENTS(pfContents);
		pfRsv.setPF_COUN_RSVT_YMD(date);
		pfRsv.setPF_COUN_RSVT_TIME(time);
		pfRsv.setPF_COUN_YMD(date);
		pfRsv.setPF_COUN_TIME_CD(time);
		pfRsv.setPF_COUN_COMMENT(pfComm);
		pfRsv.setPF_COUN_STATE_CD(pfStCD);
		
		int pfCounResult = adminService.pfCounEnroll(pfRsv);
		if (pfCounResult==1) { System.out.println("성공");
		} else {System.out.println("실패");}
		//메소드 실행
		
		
		
		return pfCounResult;
	}
	
	@ResponseBody
	@GetMapping("/admin/getPfcounList")
	public List<Map<String, Object>> getPfcounList(HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		System.out.println("계정아이디: " + userId);
		String grade = (String) session.getAttribute("grade");
		System.out.println("계정등급: "+ grade);
		if("교수".equals(grade)) {
			System.out.println("등급 교수에 해당하는 상담리스트가 실행됩니다.");
			List<Map<String, Object>> getPfcounList = adminService.getPfcounList(userId);
			return getPfcounList;
		} else {
			System.out.println("등급 관리자에 해당하는 상담리스트가 실행됩니다.");
			List<Map<String, Object>> getPfcounList = adminService.getPfcounList();
			return getPfcounList;
		}
	}
	
	@ResponseBody
	@PostMapping("/admin/pfcounModify")
	public void pfcounModify(
		@RequestParam("counNum") int counNum,
        @RequestParam(value = "fieldName", required = false) String fieldName,
        @RequestParam(value = "fieldValue", required = false) String fieldValue) {
		Map<String, Object> map = new HashMap<>();
			System.out.println(fieldName);
			System.out.println(fieldValue);
		if (fieldName.equals("PF_COUN_COMMENT")) {
			map.put("counNum", counNum);
			map.put("fieldValue", fieldValue);
			adminService.pfCmtUpdate(map);
			//System.out.println("삽입성공1");
		}else if(fieldName.equals("PF_COUN_YMD")) {
			map.put("counNum", counNum);
			map.put("fieldValue", fieldValue);
			adminService.pfCounDateUpdate(map);
			//System.out.println("삽입성공2");
		}else if(fieldName.equals("PF_COUN_TIME_CD")) {
			map.put("counNum", counNum);
			map.put("fieldValue", fieldValue);
			adminService.pfCounTimeUpdate(map);
			//System.out.println("삽입성공3");
		}else {
			//PF_STATE_CD
			map.put("counNum", counNum);
			map.put("fieldValue", fieldValue);
			adminService.pfStateUpdate(map);
			//fieldValue값이 60이면 mainService의 메소두 두개 실행 
			if (fieldValue.equals("60")) {
				int pfsno = mainService.selectPfsno(counNum);
				mainService.updatePfsc(pfsno);
				System.out.println(pfsno +" 번 스케쥴의 사용 가능 여부를 1로 업데이트 합니다.");
			} else {
				int pfsno = mainService.selectPfsno(counNum);
				mainService.updatePfsc0(pfsno);
				System.out.println(pfsno +" 번 스케쥴의 사용 가능 여부를 0으로 업데이트 합니다.");
			}
			//System.out.println("삽입성공4");
		}
	}
	
	
	@ResponseBody
	@GetMapping("/admin/counselorList")
	public List<Map<String, Object>> counselorList(){
		
		List<Map<String, Object>> counselorList = adminService.counselorList();
		return counselorList;
	}
	
	@ResponseBody
	@PostMapping("/admin/getGcounStudList")
	public List<Map<String, Object>> getGcounStudList(@RequestParam("gcounCd") String gcounCd) {
		
		List<Map<String, Object>> getGcounStudList = adminService.getGcounStudList(gcounCd);
		return getGcounStudList;
	}
	
	@ResponseBody
	@GetMapping("/admin/getGcounList")
	public List<Map<String, Object>> getGcounList(HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		String grade = (String) session.getAttribute("grade");
		
		List<Map<String, Object>> getGcounList;
		
		if (grade.equals("관리자")) {
			getGcounList = adminService.getGcounList();
		} else {
			getGcounList = adminService.getGcounList2(userId);
		}
		
		return getGcounList;
	}
	
	// 취업상담
	@GetMapping("/admin/ecoun")
	public String ecoun(HttpSession session) {		
		String grade = (String) session.getAttribute("grade");
		if (grade.equals("상담사")||grade.equals("관리자")) {
			return "admin/ecoun";
		} else {
			return "redirect:/admin/main";
		}
	}
	
	//개인상담
	@GetMapping("/admin/indicoun")
	public String indicoun(HttpSession session) {	
		String grade = (String) session.getAttribute("grade");
		
		if (grade.equals("상담사")||grade.equals("관리자")) {
			return "admin/indicoun";
		} else {
			return "redirect:/admin/main";
		}
		
	
	}
	
	
	@ResponseBody
	@GetMapping("/admin/getEcounList")
	public List<Map<String, Object>> getEcounList() {
		List<Map<String, Object>> getEcounList = adminService.getEcounList();
		System.out.println(getEcounList);
		
		return getEcounList;
	}
	
	
	//개인상담 보드리스트 
	@ResponseBody
    @GetMapping("/admin/boardList")
    public List<Map<String, Object>> getBoardList(@RequestParam("studentNo") String studentNo) {
        List<Map<String, Object>> boardList = adminService.getBoardListByStudentNo(studentNo);
        return boardList;
    }
	
	
	
	
	//개인상담 포스트디테일
	 @ResponseBody
	    @GetMapping("/admin/postDetail")
	    public Map<String, Object> getPostDetail(@RequestParam("postId") int postId) {
	        Map<String, Object> response = new HashMap<>();
	        BoardDTO detail = adminService.getPostDetail(postId);
	        List<BoardDTO> replies = adminService.getReplies(postId);

	        // BoardDTO를 Map으로 변환
	        Map<String, Object> detailMap = new HashMap<>();
	        detailMap.put("PST_SN", detail.getPST_SN());
	        detailMap.put("PST_CAT", detail.getPST_CAT());
	        detailMap.put("PST_TTL", detail.getPST_TTL());
	        detailMap.put("PST_CN", detail.getPST_CN());
	        detailMap.put("WRITER", detail.getWRITER());
	        detailMap.put("PSTG_YMD", detail.getPSTG_YMD());
	        detailMap.put("MDFCN_YMD", detail.getMDFCN_YMD());
	        detailMap.put("PST_COMP", detail.getPST_COMP());
	        detailMap.put("BBS_SN", detail.getBBS_SN());

	        response.put("detail", detailMap);
	        response.put("replies", replies);
	        return response;
	    }
	
	
	
	 @GetMapping("/admin/incompleteConsultCount")
	 public ResponseEntity<Integer> countIncompletePostsByStudentNo(@RequestParam("studentNo") String studentNo) {
	     int count = adminService.getIncompleteConsultCount(studentNo);
	     return ResponseEntity.ok(count);
	 }
	 
	 
	 // 취업 상담
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
        adminDTO.setGCOUN_CONTS_CN("gcounFiles/" + fileName);
        
        int gcounEnroll = adminService.gcounEnroll(adminDTO);
		
        return gcounEnroll;
	}
	
	@ResponseBody
	@PostMapping("/admin/updateStatus")
	public int updateStatus(@RequestParam("value") String value, 
			@RequestParam("gcounNm") String gcounNm, @RequestParam("studNo") String studNo) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("value", value);
		map.put("gcounNm", gcounNm);
		map.put("studNo", studNo);
		
		int result = adminService.updateStatus(map);
		
		return result;
	}
	
	// 심리상담
	@GetMapping("/admin/pcoun")
	public String pcoun(HttpSession session) {
		String grade = (String) session.getAttribute("grade");
		if (grade.equals("상담사")||grade.equals("관리자")) {
			return "admin/pcoun";
		} else {
			return "redirect:/admin/main";
		}
	}
	
	@ResponseBody
	@PostMapping("/admin/pcoun")
	public void pcoun(
		@RequestParam("counNum") int counNum,
        @RequestParam(value = "fieldName", required = false) String fieldName,
        @RequestParam(value = "fieldValue", required = false) String fieldValue) {
		Map<String, Object> map = new HashMap<>();
			System.out.println(fieldName);
			System.out.println(fieldValue);
		if (fieldName.equals("conVisitYmd")) {
			map.put("counNum", counNum);
			map.put("fieldValue", fieldValue);
			adminService.pcounYmd(map);
			//System.out.println("삽입성공1");
		}else if(fieldName.equals("conVisitCd")) {
			map.put("counNum", counNum);
			map.put("fieldValue", fieldValue);
			//adminService.pfCounDateUpdate(map);
			//System.out.println("삽입성공2");
		}else if(fieldName.equals("counProCd")) {
			map.put("counNum", counNum);
			map.put("fieldValue", fieldValue);
			//adminService.pfCounTimeUpdate(map);
			//System.out.println("삽입성공3");
		}else {
			//psyExam
			map.put("counNum", counNum);
			map.put("fieldValue", fieldValue);
			//adminService.pfStateUpdate(map);
			
			//System.out.println("삽입성공4");
		}
	}
	
	//취업상담
	@PostMapping("/admin/registerEmpCounselor")
    public void registerCounselor(@RequestParam("cd") String cd, @RequestParam("CSL_NO") String cslNo,
    		@RequestParam("CSL_NM") String cslNm, @RequestParam("CSL_EMAIL") String email, @RequestParam("CSL_MOBILE") String mobile, 
    		@RequestParam("SCSBJT_NM") String scsbjtNm, @RequestPart("ecounFile") MultipartFile ecounFile, HttpServletResponse response) throws Exception {
		
		String fileUrl = null;
		
		if(ecounFile != null && !ecounFile.isEmpty()) {
			fileUrl = util.fileUploadAws(ecounFile);
		}
		
		// DTO 객체 생성 및 설정
        EcounAdDTO ecounAdDTO = new EcounAdDTO();
        ecounAdDTO.setCslNo(cslNo);
        ecounAdDTO.setCslNm(cslNm);
        ecounAdDTO.setEmail(email);
        ecounAdDTO.setMobile(mobile);
        ecounAdDTO.setScsbjtNm(scsbjtNm);
        ecounAdDTO.setFileUrl(fileUrl);
        
        int result1 = adminService.ecounEnroll(ecounAdDTO);
        int result2 = adminService.registerEmpCounPro(ecounAdDTO);
        
        if (result1 == 1 && result2 == 1) {
            try {
            	System.out.println("성공 반환");
                response.getWriter().write("success"); // 성공을 나타내는 문자열을 반환
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                System.out.println("실패 반환");
                response.getWriter().write("false"); // 실패를 나타내는 문자열을 반환
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
	
	
	
	@ResponseBody
	@PostMapping("/admin/toggleCompletionStatus")
	public ResponseEntity<Void> toggleCompletionStatus(@RequestParam("postId") int postId) {
	    System.out.println("Toggling completion status for post: " + postId);
	    adminService.toggleCompletionStatus(postId);
	    return ResponseEntity.ok().build();  // HTTP 200 OK로 응답
	}

	 
	 
	 @PostMapping("admin/submitReply")
		public String submitReply(@RequestBody Map<String, Object> map, HttpSession session) {
		    String userId = (String) session.getAttribute("userId");
		    if (userId == null) {
		        return "redirect:/login";
		    }
		    map.put("writer", userId);
		    map.put("pstg_ymd", LocalDate.now().toString());
		    map.put("bbs_sn", 1); // BBS_SN 값을 1로 고정
		    int result = boardService.submitReply(map);
		    return "redirect:/indiboard_detail?no=" + map.get("postId");
		}
	 

	 
}
