package com.wizian.web.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ScheduleDto {
    private String empCounCd;
    private LocalDateTime start;
    private LocalDateTime end;

    // Getter와 Setter 메서드들

    // 생성자

    // 기타 필요한 메서드들
}