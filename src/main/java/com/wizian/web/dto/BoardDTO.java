package com.wizian.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDTO {
    // int 타입 필드
    private int PST_SN;       // 게시글 일련번호
    private int BBS_SN;       // 게시판 일련번호

    // String 타입 필드
    private String PST_CAT; // 게시글 주제 
    private String PST_TTL;   // 게시글 제목
    private String PST_CN;    // 게시글 내용    
    private String WRITER;   // 작성자
    private String WRITER_ID; // 작성자 ID
    private String PSTG_YMD;  // 게시일
    private String MDFCN_YMD; // 수정일
    private String PST_COMP;  // 게시 완료 여부
    private String PST_DEL;   // 게시글 삭제 여부
    // 롬복 어노테이션을 사용하여 모든 게터와 세터를 자동 생성
}
