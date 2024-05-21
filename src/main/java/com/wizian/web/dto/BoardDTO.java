package com.wizian.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDTO {
	//
	public int PST_SN; // 게시글 일련번호
	public int BBS_SN; // 게시판 일련번호

	// String 타입 필드
	public String PST_CAT; // 게시글 주제
	public String PST_TTL; // 게시글 제목
	public String PST_CN; // 게시글 내용
	public String WRITER; // 작성자
	public String WRITER_ID; // 작성자 ID
	public String PSTG_YMD; // 게시일
	public String MDFCN_YMD; // 수정일
	public String PST_COMP; // 게시 완료 여부
	public int PST_DEL; // 게시글 삭제 여부
	// 롬복 어노테이션을 사용하여 모든 게터와 세터를 자동 생성

	public String name;
	public String email;

}
