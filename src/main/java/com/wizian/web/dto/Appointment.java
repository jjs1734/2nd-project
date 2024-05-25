package com.wizian.web.dto;

import jakarta.persistence.Entity; 
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Appointment {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String studentId;
    private String department;
    private String phone;
    private String email;
    private LocalDateTime appointmentDate;
    private boolean agree;


    public Appointment() {}

    public Appointment(String name, String studentId, String department, String phone, String email, LocalDateTime appointmentDate, boolean agree) {
        this.name = name;
        this.studentId = studentId;
        this.department = department;
        this.phone = phone;
        this.email = email;
        this.appointmentDate = appointmentDate;
        this.agree = agree;
    }

 // Getter 및 Setter 메서드 생략
}
