package com.wizian.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wizian.web.dao.BigJinDAO;

import jakarta.websocket.server.ServerEndpoint;

@Service
public class BigJinService {
	
	@Autowired
	private BigJinDAO homeDAO;
	
	public List<Map<String, Object>> boardList() {
		return homeDAO.boardList();
	}

}
