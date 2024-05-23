package com.wizian.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wizian.web.dao.ProfessorDAO;
import com.wizian.web.dto.PfRsvDTO;
import com.wizian.web.dto.ProfessorDTO;

@Service
public class ProfessorService {
	@Autowired
	private ProfessorDAO pfDAO;

	public ProfessorDTO pfInfo(String pf_no) {
		return pfDAO.pfInfo(pf_no);
	}

	public List<Map<String, Object>> pfList(String depart) {
		return pfDAO.pfList(depart);
	}

	public List<Map<String, Object>> getScList(String pf_no) {
		return pfDAO.getScList(pf_no);
	}

	public int pfDelUpdate(int pfs_no) {
		return pfDAO.pfDelUpdate(pfs_no);
	}

	public int pfCounReservation(PfRsvDTO pfRsv) {
		return pfDAO.pfCounReservation(pfRsv);
	}
	
	
}
