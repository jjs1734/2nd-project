package com.wizian.web.dto;

import lombok.Data;

@Data
public class EcounAdDTO {
	
	// 취업 상담
	private String cd, CSL_NO, cslNo, cslNm, email, mobile, scsbjtNm;
	private String ECOUN_FILENM, ECOUN_CONTS_CN; // 파일 업로드
	
}