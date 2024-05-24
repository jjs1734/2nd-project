package com.wizian.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wizian.web.dao.AdminDAO;
import com.wizian.web.dao.BoardDAO;
import com.wizian.web.dao.GroupDAO;
import com.wizian.web.dto.BoardDTO;
import com.wizian.web.dto.EcounAdDTO;
import com.wizian.web.dto.AdminDTO;
import com.wizian.web.dto.GroupDTO;
import com.wizian.web.dto.PfRsvDTO;

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

	public List<Map<String, Object>> getBoardListByStudentNo(String studentNo) {
		return adminDAO.getBoardListByStudentNo(studentNo);
	}

	public BoardDTO getPostDetail(int postId) {
		return adminDAO.getPostDetail(postId);
	}

	public List<BoardDTO> getReplies(int postId) {
		return adminDAO.getReplies(postId);
	}
	
	public List<Map<String, Object>> getEcounStudList(String cslNo) {
		return adminDAO.getEcounStudList(cslNo);
	}

	public void updateCounCn(Map<String, Object> map) {
		adminDAO.updateCounCn(map);
	}

	public void updateCounYmd(Map<String, Object> map) {
		adminDAO.updateCounYmd(map);
	}

	public void updateCounCd(Map<String, Object> map) {
		adminDAO.updateCounCd(map);
	}

	public void updateSttsCd(Map<String, Object> map) {
		adminDAO.updateSttsCd(map);
	}

	public void registerCounselor(Map<String, String> formData) {
		adminDAO.registerCounselor(formData);
	}

	public void registerEmpCounPro(EcounAdDTO ecounAdDTO) {
		System.out.println(ecounAdDTO);
		adminDAO.registerEmpCounPro(ecounAdDTO);
	}


	public int gcounEnroll(AdminDTO adminDTO) {
		return adminDAO.gcounEnroll(adminDTO);
	}
	
	public int getIncompleteConsultCount(String studentNo) {
        return adminDAO.countIncompletePostsByStudentNo(studentNo);
    }

	public List<Map<String, Object>> counselorList() {
		return adminDAO.counselorList();
	}

	public List<Map<String, Object>> getGcounCslList() {
		return adminDAO.getGcounCslList();
	}

	public int ecounEnroll(EcounAdDTO ecounAdDTO) {
		return adminDAO.ecounEnroll(ecounAdDTO);
	public List<Map<String, Object>> getPfcounList() {
		return adminDAO.getPfcounList();
	}

	public int pfCounEnroll(PfRsvDTO pfRsv) {
		return adminDAO.pfCounEnroll(pfRsv);
	}

	public void pfCmtUpdate(Map<String, Object> map) {
		adminDAO.pfCmtupdate(map);
	}

	public void pfCounDateUpdate(Map<String, Object> map) {
		adminDAO.pfCounDateUpdate(map);
	}

	public void pfCounTimeUpdate(Map<String, Object> map) {
		adminDAO.pfCounTimeUpdate(map);
	}

	public void pfStateUpdate(Map<String, Object> map) {
		adminDAO.pfStateUpdate(map);
	}
	
	



}