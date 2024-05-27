package com.wizian.web.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PsycounDTO {
	private String key;
    private String studNo;
    private String visitResYmd;
    private String visitResCd;
    private String conVisitYmd;
    private String conVisitCd;
    private String counProCd;
    private String psyExam;

    // Getter and Setter
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getStudNo() {
        return studNo;
    }

    public void setStudNo(String studNo) {
        this.studNo = studNo;
    }

    public String getVisitResYmd() {
        return visitResYmd;
    }

    public void setVisitResYmd(String visitResYmd) {
        this.visitResYmd = visitResYmd;
    }

    public String getVisitResCd() {
        return visitResCd;
    }

    public void setVisitResCd(String visitResCd) {
        this.visitResCd = visitResCd;
    }

    public String getConVisitYmd() {
        return conVisitYmd;
    }

    public void setConVisitYmd(String conVisitYmd) {
        this.conVisitYmd = conVisitYmd;
    }

    public String getConVisitCd() {
        return conVisitCd;
    }

    public void setConVisitCd(String conVisitCd) {
        this.conVisitCd = conVisitCd;
    }

    public String getCounProCd() {
        return counProCd;
    }

    public void setCounProCd(String counProCd) {
        this.counProCd = counProCd;
    }

    public String getPsyExam() {
        return psyExam;
    }

    public void setPsyExam(String psyExam) {
        this.psyExam = psyExam;
    }
}
