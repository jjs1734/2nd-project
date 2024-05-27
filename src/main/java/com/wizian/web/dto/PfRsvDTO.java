package com.wizian.web.dto;

import java.util.Date;

import lombok.Data;

@Data
public class PfRsvDTO {
	private String STUD_NO, PF_NO, PF_COUN_RSVT_TIME, PF_CONTENTS, PF_COUN_TIME_CD, PF_COUN_COMMENT, PF_COUN_STATE_CD;
	private Date PF_COUN_RSVT_YMD, PF_COUN_YMD;
	private int PFS_NO, PF_COUN_NO;
}
