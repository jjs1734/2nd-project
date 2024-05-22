package com.wizian.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.wizian.web.dto.ProfessorDTO;

@Repository
@Mapper
public interface ProfessorDAO {

	ProfessorDTO pfInfo(String pf_no);

	List<Map<String, Object>> pfList(String depart);

	List<Map<String, Object>> getScList(String pf_no);

}
