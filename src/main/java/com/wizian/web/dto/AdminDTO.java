package com.wizian.web.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class AdminDTO {
	
	private String STUD_NO, USER_NO, PF_NO, NTN_CD, SCSBJT_NM, STUD_NM, TELNO, STUD_EML_ADDR, COL_REG, STUD_BRDT, STUD_GEN, NTN_NM, PF_NM;

	// 집단 상담
	private String GCOUN_CD, GCOUN_STA_NM, GCOUN_ATND_YN, GCOUN_STUD_RV, GCOUN_CSL_RV, GCOUN_DTL_CN;
	private int GCOUN_LMT_COUNT, BBS_SN;
	private String CSL_NO, GCOUN_NM, GCOUN_PROG_STA_NM;
	
	private LocalDateTime GCOUN_DT, GCOUN_CURRENT_DT;
	private LocalDate GCOUN_BGNG_DT, GCOUN_DDLN_DT;
	private String GCOUN_FILENM, GCOUN_CONTS_CN; // 파일 업로드
	//
	
	
	private String CSL_NM;
	
	
	
}
