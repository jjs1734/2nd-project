package com.wizian.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wizian.web.dao.GroupDAO;
import com.wizian.web.dto.GroupDTO;


@Service
public class GroupService {
	
	@Autowired
	private GroupDAO groupDAO;

	public List<Map<String, String>> groupList() {
		return groupDAO.groupList();
	}

	public List<String> mainGroupImg() {
		return groupDAO.mainGroupImg();
	}

	public int groupEnroll(GroupDTO groupDto) {
		return groupDAO.groupEnroll(groupDto);
	}

	
}