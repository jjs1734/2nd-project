package com.wizian.web.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class GroupDTO {
	
	private String GCOUN_CD, CSL_NO, GCOUN_NM, GCOUNT_LMT_COUNT, GCOUN_COUNTS_CN, GCOUN_DTL_CN, STUD_NO; 
	private int GCOUN_REG_COUNT, GCOUN_ATND_COUNT;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime GCOUN_DT, GCOUN_CURRENT_DT; 
	private LocalDate GCOUN_BGNG_DT, GCOUN_DDLN_DT;
	
	private int id;
	
}
