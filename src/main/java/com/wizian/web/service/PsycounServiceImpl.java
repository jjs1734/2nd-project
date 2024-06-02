package com.wizian.web.service;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wizian.web.dao.PsycounDAO;
import com.wizian.web.dto.PsycounDTO;

@Service
public class PsycounServiceImpl implements PsycounService {

    @Autowired
    private PsycounDAO psycounDAO;

    @Override
    @Transactional
    public void saveAll(List<PsycounDTO> psycounDTOList) {
        for (PsycounDTO dto : psycounDTOList) {
            try {
                psycounDAO.insertPsyExamVisitRequest(dto);
            } catch (DuplicateKeyException e) {
                // Duplicate entry 처리: 기존 항목 업데이트
                psycounDAO.updatePsyExamVisitRequest(dto);
            }
        }
    }
    
    @Override
    public List<PsycounDTO> getAllRequests() {
        return psycounDAO.getAllRequests();
    }
}

