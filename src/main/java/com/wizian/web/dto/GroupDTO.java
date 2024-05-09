package com.wizian.web.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class GroupDTO {
	
	public String GCOUN_CD, CSL_NO, GCOUN_NM, GCOUNT_LMT_COUNT, GCOUN_COUNTS_CN, GCOUN_DTL_CN; 
	public int GCOUN_REG_COUNT, GCOUN_ATND_COUNT;
	public LocalDateTime GCOUN_DT, GCOUN_BGNG_DT, GCOUN_DDLN_DT;
	
}
