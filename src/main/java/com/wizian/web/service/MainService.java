package com.wizian.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wizian.web.dao.MainDAO;

@Service
public class MainService {
	
	@Autowired
	private MainDAO mainDAO;

	public List<Map<String, Object>> pfCounList(String userId) {
		return mainDAO.pfCounList(userId);
	}

	public List<Map<String, Object>> gCounList(String userId) {
		return mainDAO.gCounList(userId);
	}

	public List<Map<String, Object>> eCounList(String userId) {
		return mainDAO.eCounList(userId);
	}

	public List<Map<String, Object>> psyCounList(String userId) {
		return mainDAO.psyCounList(userId);
	}

	public List<Map<String, Object>> inCounList(String userId) {
		return mainDAO.inCounList(userId);
	}

	public int updateState(int counNum) {
		return mainDAO.updateState(counNum);
	}

	public int selectPfsno(int counNum) {
		return mainDAO.selectPfsno(counNum);
	}
	
	public void updatePfsc(int pfsno) {
		mainDAO.updatePfsc(pfsno);
	}
	
	public void updatePfsc0(int pfsno) {
		mainDAO.updatePfsc0(pfsno);
	}


}
