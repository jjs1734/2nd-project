package com.wizian.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wizian.web.dao.PsycounDAO;
import com.wizian.web.dto.PsycounDTO;

@Service
public class PsycounService {

	@Autowired
    private PsycounDAO dao;

    public List<PsycounDTO> getAllVisitRequests() {
        return dao.findAll();
    }
    
}
