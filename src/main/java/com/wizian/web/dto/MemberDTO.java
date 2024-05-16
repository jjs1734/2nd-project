package com.wizian.web.dto;


import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MemberDTO {
	private String USER_NO, LOGIN_ID, PW, FIR_YN, ADMIN_YN;
	private int PW_ERR_CNT;
	private LocalDateTime RCT_ACC_DT;
}
