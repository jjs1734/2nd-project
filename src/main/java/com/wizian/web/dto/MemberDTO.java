package com.wizian.web.dto;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {
	
	public String USER_NO, LOGIN_ID, PW, FIR_YN, ADMIN_YN; 
	public int PW_ERR_CNT;
	public LocalDateTime RCT_ACC_DT;
	
}
