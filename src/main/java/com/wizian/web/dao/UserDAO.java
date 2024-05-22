package com.wizian.web.dao;


import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.wizian.web.dto.UserDTO;

@Repository
@Mapper
public interface UserDAO {

	UserDTO userInfo(String userId);

	Map<String, Object> empUserInfo(String userId);


}