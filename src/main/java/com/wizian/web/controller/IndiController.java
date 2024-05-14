package com.wizian.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndiController {
	
	@GetMapping("/indicoun")
	public String indiCoun() {
		return "indicoun";
		
	}
	
	
	// "indicoun_ongoing" 경로에 대한 요청 처리
		@GetMapping("/indicoun_ongoing")
		public String indicounOngoing() {
			return "indicoun_ongoing";
		}
}