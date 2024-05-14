package com.wizian.web.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
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

import com.wizian.web.User;
import com.wizian.web.dto.EventData;
import com.wizian.web.dto.ScheduleDto;
import com.wizian.web.dto.UserDTO;
import com.wizian.web.service.EmploymentService;


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
	public String employment(Model model) {
		
		List<Map<String, Object>> list = employmentService.boardList();
		model.addAttribute("boardList", list);
		return "employment";
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
	    List<Map<String, Object>> list = employmentService.selectEmpCoun(cslNo);
	    System.out.println(list);
	    model.addAttribute("selectEmpCoun", list);
	    return "empApply"; // 절대 경로로 수정
	}
	
	@GetMapping("/empCal")
	public String empCal() {
		return "empCal";
	}
	
	@PostMapping("/insertEmpCal")
	@ResponseBody
	public ResponseEntity<String> fullCal(@RequestBody EventData eventData) {
	    System.out.println("ajax작동" + eventData);
	    String empCounCd = eventData.getEmpCounCd();
	    String start = eventData.getStart(); // start 값을 추가
	    
	    String dateOnly = start.substring(0, 10); // "2024-05-15"만 추출
	    //System.out.println(empCounCd);
	    employmentService.insertEmpCal(empCounCd, dateOnly);
	    //System.out.println(employmentService.insertEmpCal(empCounCd));
	    return ResponseEntity.ok("{\"status\": \"success\"}");   // response에 success를 보내준다.
	}
	
	/*
	@PostMapping("/insertEmpCal")
	@ResponseBody
	public void fullCal(@RequestBody EventData eventData) {
	    System.out.println("ajax작동" + eventData);
	    String empCounCd = eventData.getEmpCounCd();
	    employmentService.insertEmpCal(empCounCd);
	    // 작업이 성공했음을 나타내는 응답을 보냄
	}*/
	
	
	
	/*
	@PostMapping("/insertEmpCal")
    @ResponseBody
    public String addEvent(@RequestBody List<Map<String, Object>> param) throws Exception {
    	 
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",Locale.KOREA);
 
        for (int i = 0; i < param.size(); i++) {
 
            String username = (String) param.get(i).get("title");
            String startDateString = (String) param.get(i).get("start");
            String endDateString = (String) param.get(i).get("end");
 
            LocalDateTime startDate = LocalDateTime.parse(startDateString, dateTimeFormatter);
            LocalDateTime endDate = LocalDateTime.parse(endDateString, dateTimeFormatter);
 
            UserDTO userDTO = UserDTO.builder()
                    .username(username)
                    .build();
 
            String user = employmentService.saveUser(userDTO);
            User userEntity = userRepository.findById(user).get();
 
            ManagerHopeTimeDto managerHopeTimeDto = ManagerHopeTimeDto.builder()
                    .user(userEntity)
                    .managerHopeDateStart(startDate)
                    .managerHopeDateEnd(endDate)
                    .build();
 
            employmentService.saveManagerHopeTime(managerHopeTimeDto);
        }
        return "/full-calendar/calendar-admin-update";
        }
*/


}