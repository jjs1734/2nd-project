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

	public Map<String, Object> selectEmpCoun(String cslNo) {
		return employmentDAO.selectEmpCoun(cslNo);
	}
	/*
	public ResponseEntity<String> insertEmpCal(String empCounCd) {
		System.out.println(empCounCd);
		return employmentDAO.insertEmpCal(empCounCd);
	}*/
	/*
	    public void insertEmpCal(String empCounCd, String dateOnly) {
	    	System.out.println(empCounCd);
	    	System.out.println(dateOnly);
	        employmentDAO.insertEmpCal(empCounCd, dateOnly);
	    }*/

		public void insertEmpCal(EventData evenData) {
			employmentDAO.insertEmpCal(evenData);
		}

		public List<Map<String, Object>> updateCal(String cslNo) {
			return employmentDAO.updateCal(cslNo);
		}

		public List<Map<String, Object>> resCal(EventData eventData) {
			return employmentDAO.resCal(eventData);
		}

		public List<Map<String, Object>> empCalList(String cslNo1) {
			return employmentDAO.empCalList(cslNo1);
		}

		public Map<String, Object> calSelectApply(String cslNo) {
			return employmentDAO.calSelectApply(cslNo);
		}

		public void insertApplyFinal(EventData eventData) {
			employmentDAO.insertApplyFinal(eventData);
			
		}
}