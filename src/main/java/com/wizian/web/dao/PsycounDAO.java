package com.wizian.web.dao;

import com.wizian.web.dto.PsycounDTO;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PsycounDAO {
    void insertPsyExamVisitRequest(PsycounDTO dto);

    void updatePsyExamVisitRequest(PsycounDTO dto);

    List<PsycounDTO> getAllRequests();
}