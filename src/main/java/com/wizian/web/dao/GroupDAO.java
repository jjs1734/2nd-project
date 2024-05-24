package com.wizian.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.wizian.web.dto.GroupDTO;

@Repository
@Mapper
public interface GroupDAO {

	List<Map<String, String>> groupList();

	List<String> mainGroupImg();

	int groupEnroll(GroupDTO groupDto);

	Map<String, Object> selectMyInfo(String userId);

	void updateEmail(String userId, String value);

	void updateGender(String userId, String value);

	void updateTelNo(String userId, String value);

}