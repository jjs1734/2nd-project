package com.wizian.web.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wizian.web.dao.UserDAO;
import com.wizian.web.dto.UserDTO;


@Service
public class UserService {
	
	@Autowired
	private UserDAO userDAO;

	public UserDTO userInfo(String userId) {
		return userDAO.userInfo(userId);
	}

	public Map<String, Object> empUserInfo(String userId) {
		return userDAO.empUserInfo(userId);
	}

	
}