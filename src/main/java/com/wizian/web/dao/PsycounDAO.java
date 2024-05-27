package com.wizian.web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.wizian.web.dto.PsycounDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PsycounDAO {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;

    public List<PsycounDTO> findAll() {
        String sql = "SELECT * FROM PSY_EXAM_VISIT_REQUEST";
        return jdbcTemplate.query(sql, new PsyExamVisitRequestRowMapper());
    }

    private static final class PsyExamVisitRequestRowMapper implements RowMapper<PsycounDTO> {
        @Override
        public PsycounDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            PsycounDTO request = new PsycounDTO();
            request.setKey(rs.getString("key"));
            request.setStudNo(rs.getString("stud_no"));
            request.setVisitResYmd(rs.getString("visit_res_ymd"));
            request.setVisitResCd(rs.getString("visit_res_cd"));
            request.setConVisitYmd(rs.getString("con_visit_ymd"));
            request.setConVisitCd(rs.getString("con_visit_cd"));
            request.setCounProCd(rs.getString("coun_pro_cd"));
            request.setPsyExam(rs.getString("psy_exam"));
            return request;
        }
    }
}
