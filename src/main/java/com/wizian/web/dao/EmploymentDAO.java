package com.wizian.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.wizian.web.dto.EventData;

@Repository
@Mapper
public interface EmploymentDAO {
	
	public List<Map<String, Object>> boardList();

	public List<Map<String, Object>> empCounProfile();

	public List<Map<String, Object>> selectEmpCoun(String cslNo);

	/* public ResponseEntity<String> insertEmpCal(String empCounCd); */
	/* void insertEmpCal(String empCounCd, String dateOnly); */
	void insertEmpCal(EventData evenData);


}