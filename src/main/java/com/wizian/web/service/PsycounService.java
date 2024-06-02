package com.wizian.web.service;


import java.util.List;

import com.wizian.web.dto.PsycounDTO;

public interface PsycounService {
    void saveAll(List<PsycounDTO> psycounDTOList);

	List<PsycounDTO> getAllRequests();
}