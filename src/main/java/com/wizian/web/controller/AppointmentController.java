package com.wizian.web.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wizian.web.dto.AppointmentDTO;
import com.wizian.web.service.AppointmentService;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
	
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/create")
    public ResponseEntity<Void> createAppointment(
        @RequestParam("name") String name,
        @RequestParam("studentId") String studentId,
        @RequestParam("department") String department,
        @RequestParam("phoneFront") String phoneFront,
        @RequestParam("phoneBack1") String phoneBack1,
        @RequestParam("phoneBack2") String phoneBack2,
        @RequestParam("email") String email,
        @RequestParam("date") String date,
        @RequestParam("time") String time
    ) {
        // 로그 추가
        System.out.println("Received request to create appointment:");
        System.out.println("Name: " + name);
        System.out.println("Student ID: " + studentId);
        System.out.println("Department: " + department);
        System.out.println("Phone Front: " + phoneFront);
        System.out.println("Phone Back 1: " + phoneBack1);
        System.out.println("Phone Back 2: " + phoneBack2);
        System.out.println("Email: " + email);
        System.out.println("Date: " + date);
        System.out.println("Time: " + time);

        // 전화번호 합치기
        String phoneNumber = phoneFront + phoneBack1 + phoneBack2;
        System.out.println("Phone Number: " + phoneNumber);
        
        // 날짜 형식 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy. MM. dd.");
        LocalDate parsedDate = LocalDate.parse(date.trim(), formatter);
        String formattedDate = parsedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println("Formatted Date: " + formattedDate);

        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setName(name);
        appointmentDTO.setStudentId(studentId);
        appointmentDTO.setDepartment(department);
        appointmentDTO.setPhoneNumber(phoneNumber);
        appointmentDTO.setEmail(email);
        appointmentDTO.setDate(formattedDate);
        appointmentDTO.setTime(time);

        try {
            appointmentService.saveAppointment(appointmentDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            System.err.println("Error while saving appointment: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping
    public List<AppointmentDTO> getAppointments() {
        return appointmentService.getAllAppointments();
    }
}
