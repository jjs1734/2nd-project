package com.wizian.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentDTO {
    private String name;
    private String studentId;
    private String department;
    private String phoneNumber;
    private String email;
    private String date; // Format: yyyy.MM.dd
    private String time; // Format: HH:mm

    // Getters and Setters
}
