package com.wizian.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wizian.web.dao.AdminDAO;
import com.wizian.web.dao.GroupDAO;
import com.wizian.web.dto.AdminDTO;
import com.wizian.web.dto.GroupDTO;


@Service
public class AdminService {
	
	@Autowired
	private AdminDAO adminDAO;

	public List<Map<String, Object>> studentList() {
		return adminDAO.studentList();
	}

	public List<Map<String, Object>> getGcounList() {
		return adminDAO.getGcounList();
	}

	public List<Map<String, Object>> getGcounStudList(String gcounCd) {
		return adminDAO.getGcounStudList(gcounCd);
	}

	public List<Map<String, Object>> getEcounList() {
		return adminDAO.getEcounList();
	}

	public List<Map<String, Object>> getGcountCslList() {
		return adminDAO.getGcounCslList();
	}

	public int gcounEnroll(AdminDTO adminDTO) {
		return adminDAO.gcounEnroll(adminDTO);
	}


	
}