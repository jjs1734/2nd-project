package com.wizian.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.wizian.web.dto.BoardDTO;
import com.wizian.web.dto.PfRsvDTO;
import com.wizian.web.dto.EcounAdDTO;
import com.wizian.web.dto.AdminDTO;

@Repository
@Mapper
public interface AdminDAO {

	List<Map<String, Object>> studentList();

	List<Map<String, Object>> gcounStudList();

	List<Map<String, Object>> getGcounList();

	List<Map<String, Object>> getGcounStudList(String gcounCd);

	List<Map<String, Object>> getEcounList();

	List<Map<String, Object>> getBoardListByStudentNo(String studentNo);
	
	 int countIncompletePostsByStudentNo(String studentNo);

	void updateCounCn(Map<String, Object> map);

	void updateCounYmd(Map<String, Object> map);

	void updateCounCd(Map<String, Object> map);

	void updateSttsCd(Map<String, Object> map);

	void registerCounselor(Map<String, String> formData);

	void registerEmpCounPro(EcounAdDTO ecounAdDTO);

	 BoardDTO getPostDetail(int postId);
	 List<BoardDTO> getReplies(int postId);
	List<Map<String, Object>> getEcounStudList(String cslNo);
	
	List<Map<String, Object>> getGcounCslList();

	int gcounEnroll(AdminDTO adminDTO);

	List<Map<String, Object>> counselorList();

	int ecounEnroll(EcounAdDTO ecounAdDTO);
	List<Map<String, Object>> getPfcounList();

	int pfCounEnroll(PfRsvDTO pfRsv);

	void pfCmtupdate(Map<String, Object> map);

	void pfCounDateUpdate(Map<String, Object> map);

	void pfCounTimeUpdate(Map<String, Object> map);

	void pfStateUpdate(Map<String, Object> map);

	List<Map<String, Object>> getGcounCslList2(String userId);

	
}