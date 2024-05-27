package com.wizian.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MainDAO {

	List<Map<String, Object>> pfCounList(String userId);

	List<Map<String, Object>> gCounList(String userId);

	List<Map<String, Object>> eCounList(String userId);

	List<Map<String, Object>> psyCounList(String userId);

	List<Map<String, Object>> inCounList(String userId);

	int updateState(int counNum);


}
