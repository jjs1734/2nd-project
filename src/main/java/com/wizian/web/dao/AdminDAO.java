package com.wizian.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.wizian.web.dto.GroupDTO;

@Repository
@Mapper
public interface AdminDAO {

	List<Map<String, Object>> studentList();


}