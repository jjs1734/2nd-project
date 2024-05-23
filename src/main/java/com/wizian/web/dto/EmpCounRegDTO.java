package com.wizian.web.dto;

import lombok.Data;

@Data
public class EmpCounRegDTO {
	
	private int CAL_NUM;  // 추가된 부분
	private String empDate;
	private String empContent;
    private String empTime;
    private String CSL_NO;
    private String userId;  // 추가된 부분(로그인 ID=학번)
    
}