package com.wizian.web.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
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

import com.wizian.web.dto.EventData;
import com.wizian.web.service.EmploymentService;

import jakarta.servlet.http.HttpSession;

import com.google.gson.JsonObject; // Gson의 JsonObject를 import합니다.


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
	public String employment(HttpSession session, Model model) {
		
		String userId = (String) session.getAttribute("userId");
		
		if(userId != null) {
			model.addAttribute("userId", userId);
			//System.out.println("로그인되어있음");
			List<Map<String, Object>> list = employmentService.boardList();
			model.addAttribute("boardList", list);
			return "employment";
		} else {
			System.out.println("세션이 존재하지 않음");
			return "main";
		}
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
	    System.out.println("첫번째 employApply");
	    List<Map<String, Object>> list = employmentService.selectEmpCoun(cslNo);
	    System.out.println("employApply 에러나는 부분" + list);
	    model.addAttribute("selectEmpCoun", list);
	    return "empApply"; // 절대 경로로 수정
	}
	
	@GetMapping("/employApply")
	public String selectEmpCal(@RequestParam("CSL_NO") String cslNo, Model model) {
		System.out.println("두번째 employApply");
		System.out.println("두번째 CSL_NO : " + cslNo);
		List<Map<String, Object>> list = employmentService.selectEmpCoun(cslNo);
		Map<String, Object> list12 = employmentService.calSelectApply(cslNo);
		System.out.println(list12);
		model.addAttribute("selectEmpCoun", list);
		model.addAttribute("selectEmpCoun1", list12);
		return "empApply"; // 절대 경로로 수정
	}
	
	// 처음 empCal웹 페이지 열 때, 상담사 스케줄 띄우기 or empApply.html의 <a>태그에서 href할때 오는 곳  원본
	/*
	@GetMapping("/empCal")
	public String empCal(@RequestParam("CSL_NO") String cslNo, Model model) {
		System.out.println(cslNo);
		//String cslNoo = employmentService.updateCal(cslNo);
		List<Map<String, Object>> cslNoo = employmentService.updateCal(cslNo);
		System.out.println(cslNoo);
		model.addAttribute("cslNoo", cslNoo);
		return "empCal";
	}
	*/

	@GetMapping("/empCal")
	public String empCal(@RequestParam("CSL_NO") String cslNo, Model model) {
		System.out.println(cslNo);
		//String cslNoo = employmentService.updateCal(cslNo);
		List<Map<String, Object>> cslNoo = employmentService.updateCal(cslNo);
		System.out.println(cslNoo);
		model.addAttribute("cslNoo", cslNoo);
		return "empCal";
	}
	
	/* 원본
	@PostMapping("/empCalList")
	public String empCalList(@RequestParam("cslNum") String cslNo1, Model model) {
	    List<Map<String, Object>> cslNum1 = employmentService.empCalList(cslNo1);

	    System.out.println(cslNum1);
	    model.addAttribute("cslNum1", cslNum1);

	    return "empCal";
	}
	*/
	
	@PostMapping("/empCalList")
	@ResponseBody // JSON 형식으로 데이터를 반환하기 위해 사용
	public List<Map<String, Object>> empCalList(@RequestParam("cslNum") String cslNo1) {
		System.out.println("/empCalList는" + employmentService.empCalList(cslNo1));
	    return employmentService.empCalList(cslNo1);
	}

	// 처음 empCal웹 페이지 열 때, 상담사 스케줄 띄우기 or empApply.html의 <a>태그에서 href할때 오는 곳   수정중 (DB에서 DATE가 DATE타입일경우)
	/*
	@GetMapping("/empCal")
	public String empCal(@RequestParam("CSL_NO") String cslNo, Model model) {
	    System.out.println(cslNo);
	    List<Map<String, Object>> cslNoo = employmentService.updateCal(cslNo);

	    // DB에서 가져온 날짜 형식을 FullCalendar에서 사용하는 ISO 8601 형식으로 변환하고 empCounCd를 반영하여 start와 end 값을 생성합니다.
	    List<Map<String, Object>> convertedDates = new ArrayList<>();
	    for (Map<String, Object> date : cslNoo) {
	        Map<String, Object> convertedDate = new HashMap<>();
	        String originalDate = (String) date.get("DATE");
	        String empCounCd = (String) date.get("EMP_COUN_CD");

	        // EMP_COUN_CD를 이용하여 start와 end 값을 생성합니다.
	        LocalDateTime startDateTime = LocalDateTime.parse(originalDate + "T" + empCounCd + ":00:00");
	        LocalDateTime endDateTime = startDateTime.plusHours(1); // 예약은 1시간 단위로 설정합니다.

	        // LocalDateTime 객체를 ISO 8601 형식으로 변환합니다.
	        String startIsoDate = startDateTime.atZone(ZoneId.systemDefault()).toInstant().toString();
	        String endIsoDate = endDateTime.atZone(ZoneId.systemDefault()).toInstant().toString();
	        
	        convertedDate.put("start", startIsoDate);
	        convertedDate.put("end", endIsoDate);
	        convertedDates.add(convertedDate);
	    }

	    System.out.println(convertedDates);
	    model.addAttribute("cslNoo", convertedDates);
	    return "empCal";
	}
	*/
	
	/*
	// 처음 empCal웹 페이지 열 때, 상담사 스케줄 띄우기 or empApply.html의 <a>태그에서 href할때 오는 곳   수정중 (DB에서 DATE가 CHAR타입일경우_mapper에서 DATE타입으로 변경해서 가져와야됨)
		@GetMapping("/empCal")
		public String empCal(@RequestParam("CSL_NO") String cslNo, Model model) {
		    System.out.println(cslNo);
		    List<Map<String, Object>> cslNoo = employmentService.updateCal(cslNo);
	
		    // DB에서 가져온 날짜 형식을 FullCalendar에서 사용하는 ISO 8601 형식으로 변환하고 empCounCd를 반영하여 start와 end 값을 생성합니다.
		    List<Map<String, Object>> convertedDates = new ArrayList<>();
		 // DateTimeFormatter를 생성하여 ISO 8601 형식으로 변환합니다.
		    DateTimeFormatter isoFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

		    for (Map<String, Object> date : cslNoo) {
		        Map<String, Object> convertedDate = new HashMap<>();
		        String originalDate = (String) date.get("DATE"); // CHAR 타입으로 저장된 날짜 값
		        String empCounCd = (String) date.get("EMP_COUN_CD");

		        // CHAR 타입으로 저장된 날짜 값을 LocalDateTime으로 변환
		        LocalDateTime startDateTime = LocalDateTime.parse(originalDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")); // 날짜 형식에 맞게 포맷 지정
		        startDateTime = startDateTime.withHour(Integer.parseInt(empCounCd)); // EMP_COUN_CD를 시간으로 설정
		        LocalDateTime endDateTime = startDateTime.plusHours(1); // 예약은 1시간 단위로 설정합니다.

		        // LocalDateTime 객체를 ISO 8601 형식으로 변환합니다.
		        String startIsoDate = startDateTime.format(isoFormatter);
		        String endIsoDate = endDateTime.format(isoFormatter);
		        
		        convertedDate.put("start", startIsoDate);
		        convertedDate.put("end", endIsoDate);
		        
		        convertedDates.add(convertedDate);
		    }

	
		    System.out.println("CONVERT : " + convertedDates);
		    model.addAttribute("cslNoo", convertedDates);
		    return "empCal";
		}
	 */

	
	/* 이벤트(예약) 후 다시 empApply.html로 보내기  _ 원본*/
	@PostMapping("/insertEmpCal")
	@ResponseBody
	public ResponseEntity<String> fullCal(@RequestBody EventData eventData) {
	    System.out.println("ajax작동" + eventData);
	    String empCounCd = eventData.getEmpCounCd();
	    String start = eventData.getStart(); // start 값을 추가
	    String dateOnly = start.substring(0, 10); // "2024-05-15"만 추출
	    String title = eventData.getTitle();
	    String CSL_NO = eventData.getCSL_NO();
	    System.out.println("CSL_NO" + CSL_NO);
	    System.out.println("title" + title);
	    
	    EventData evenData = new EventData();
	    evenData.setEmpCounCd(empCounCd); // empCounCd 설정
	    evenData.setDateOnly(dateOnly);// start 설정
	    evenData.setTitle(title); // USR_CM 설정 (상담 내용)
	    evenData.setCSL_NO(CSL_NO); // USR_CM 설정 (상담 내용)
	    String redirectUrl = "/employApply";
	    
	    //System.out.println(empCounCd);
	    employmentService.insertEmpCal(evenData);
	    System.out.println(evenData);
	    //return ResponseEntity.ok("{\"status\": \"success\"}");   // response에 success를 보내준다.
	    //return ResponseEntity.ok("{\"redirect\": \"/employApply\"}");   // response에 success를 보내준다.
	    //return ResponseEntity.ok().body("{\"success\": true, \"redirect\": \"" + redirectUrl + "\"}");
	    return ResponseEntity.ok().body("{\"success\": true, \"redirect\": \"" + redirectUrl + "\", \"dateOnly\": \"" + dateOnly + "\"}");
	}
	
	/* 이벤트(예약) 후 다시 empApply.html로 보내기  _ 원본
	@PostMapping("/insertEmpCal")
	@ResponseBody
	public String fullCal(@RequestBody EventData eventData) {
		System.out.println("ajax작동" + eventData);
		String empCounCd = eventData.getEmpCounCd();
		String start = eventData.getStart(); // start 값을 추가
		String dateOnly = start.substring(0, 10); // "2024-05-15"만 추출
		String title = eventData.getTitle();
		String CSL_NO = eventData.getCSL_NO();
		System.out.println("CSL_NO" + CSL_NO);
		System.out.println("title" + title);
		
		EventData evenData = new EventData();
		evenData.setEmpCounCd(empCounCd); // empCounCd 설정
		evenData.setDateOnly(dateOnly);// start 설정
		evenData.setTitle(title); // USR_CM 설정 (상담 내용)
		evenData.setCSL_NO(CSL_NO); // USR_CM 설정 (상담 내용)
		String redirectUrl = "/employApply";
		
		//System.out.println(empCounCd);
		employmentService.insertEmpCal(evenData);
		System.out.println(evenData);
		//return ResponseEntity.ok("{\"status\": \"success\"}");   // response에 success를 보내준다.
		//return ResponseEntity.ok("{\"redirect\": \"/employApply\"}");   // response에 success를 보내준다.
		//return ResponseEntity.ok().body("{\"success\": true, \"redirect\": \"" + redirectUrl + "\"}");
		return "";
	}
	*/
	// 이벤트(예약) 후 다시 empApply.html로 보내기  _ 수정중
	/*
	@PostMapping("/insertEmpCal")
	@ResponseBody
	public String fullCal(@RequestBody EventData eventData) {
		System.out.println("Ajax 작동" + eventData);
		List<Map<String, Object>> resCal = employmentService.resCal(eventData);
		return "empApply";
	}
	*/
	
}