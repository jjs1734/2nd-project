package com.wizian.web.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.wizian.web.dto.PsycounDTO;

@Mapper
public interface PsycounMapper {
	/*
	 * @Insert("INSERT INTO PSY_EXAM_VISIT_REQUEST (stud_no, visit_res_ymd, visit_res_cd) VALUES (#{studNo}, #{visitResYmd}, #{visitResCd})"
	 * ) void insertPsycoun(PsycounDTO psycounDTO);
	 */
}