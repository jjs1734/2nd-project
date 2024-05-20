package com.wizian.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wizian.web.dao.PsycounDAO;

@Service
public class PsycounService {

	@Autowired
    private PsycounDAO psycounDAO;

    
}
