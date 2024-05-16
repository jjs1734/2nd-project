package com.wizian.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wizian.web.dao.GroupDAO;
import com.wizian.web.dao.MemberDAO;
import com.wizian.web.dto.GroupDTO;


@Service
public class MemberService {
	
	@Autowired
	private MemberDAO memberDAO;

	
}