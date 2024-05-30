package com.wizian.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wizian.web.service.PsycounService;

@RestController
@RequestMapping("/api/psy-exam-visit-requests")
public class PsyApiController {

	@Autowired
	private PsycounService service;
	
}