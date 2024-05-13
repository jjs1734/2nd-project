package com.wizian.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wizian.web.dao.EmploymentDAO;

@Service
public class EmploymentService {
	
	@Autowired
	private EmploymentDAO employmentDAO;
	
	public List<Map<String, Object>> boardList() {
		return employmentDAO.boardList();
	}

	public List<Map<String, Object>> empCounProfile() {
		return employmentDAO.empCounProfile();
	}

	public List<Map<String, Object>> selectEmpCoun(String cslNo) {
		System.out.println("22222");
		return employmentDAO.selectEmpCoun(cslNo);
	}
}