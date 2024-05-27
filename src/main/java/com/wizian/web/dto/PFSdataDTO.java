package com.wizian.web.dto;

import java.util.Date;

import lombok.Data;

@Data
public class PFSdataDTO {
	private int PFS_NO;
	private String PF_NO, PF_TIME_CD, PF_SC_DEL;
	private Date PF_DATE;
}
