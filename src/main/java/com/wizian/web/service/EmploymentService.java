package com.wizian.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.wizian.web.dao.EmploymentDAO;
import com.wizian.web.dto.EventData;

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
	/*
	public ResponseEntity<String> insertEmpCal(String empCounCd) {
		System.out.println(empCounCd);
		return employmentDAO.insertEmpCal(empCounCd);
	}*/
	
    public void insertEmpCal(String empCounCd, String dateOnly) {
    	System.out.println(empCounCd);
    	System.out.println(dateOnly);
        employmentDAO.insertEmpCal(empCounCd, dateOnly);
    }
}