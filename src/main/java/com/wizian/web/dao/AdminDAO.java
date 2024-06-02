package com.wizian.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.wizian.web.dto.BoardDTO;
import com.wizian.web.dto.PfRsvDTO;
import com.wizian.web.dto.EcounAdDTO;
import com.wizian.web.dto.PFSdataDTO;
import com.wizian.web.dto.AdminDTO;

@Repository
@Mapper
public interface AdminDAO {

	List<Map<String, Object>> studentList();

	List<Map<String, Object>> gcounStudList();

	List<Map<String, Object>> getGcounList();

	List<Map<String, Object>> getGcounList2(String userId);

	List<Map<String, Object>> getGcounStudList(String gcounCd);

	List<Map<String, Object>> getEcounList();

	List<Map<String, Object>> getBoardListByStudentNo(String studentNo);
	
	 int countIncompletePostsByStudentNo(String studentNo);

	void updateCounCn(Map<String, Object> map);

	void updateCounYmd(Map<String, Object> map);

	void updateCounCd(Map<String, Object> map);

	void updateSttsCd(Map<String, Object> map);

	void registerCounselor(Map<String, String> formData);

	int registerEmpCounPro(EcounAdDTO ecounAdDTO);

	BoardDTO getPostDetail(int postId);
	
	List<BoardDTO> getReplies(int postId);
	
	List<Map<String, Object>> getEcounStudList(String cslNo);
	
	List<Map<String, Object>> getGcounCslList();

	int gcounEnroll(AdminDTO adminDTO);

	List<Map<String, Object>> counselorList();
	
	
	void toggleCompletionStatus(int postId);

	int ecounEnroll(EcounAdDTO ecounAdDTO);
	
	List<Map<String, Object>> getPfcounList();
	
	List<Map<String, Object>> getPfcounList(@Param("userId") String userId);

	int pfCounEnroll(PfRsvDTO pfRsv);

	void pfCmtupdate(Map<String, Object> map);

	void pfCounDateUpdate(Map<String, Object> map);

	void pfCounTimeUpdate(Map<String, Object> map);

	void pfStateUpdate(Map<String, Object> map);

	List<Map<String, Object>> getGcounCslList2(String userId);
	
	List<Map<String, Object>> getPfList();
	
	List<Map<String, Object>> getPfList(@Param("userNo") String userNo);

	List<Map<String, Object>> getPfscList(String pfNo);

	int pfscEnroll(PFSdataDTO pfsDTO);

	void pfNmUpdate(Map<String, Object> map);

	void pfTelUpdate(Map<String, Object> map);

	void pfEmailUpdate(Map<String, Object> map);

	void pfNcdUpdate(Map<String, Object> map);

	int updateStatus(Map<String, Object> map);

	void pcounYmd(Map<String, Object> map);

	void pcounCd(Map<String, Object> map);

	void pcounProCd(Map<String, Object> map);

	void pcounExam(Map<String, Object> map);

	



}