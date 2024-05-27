package com.wizian.web.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.wizian.web.dto.PsycounDTO;

@Mapper
@Repository
public interface PsycounDAO {

	public int submitAppointment(PsycounDTO psycounDTO);

}