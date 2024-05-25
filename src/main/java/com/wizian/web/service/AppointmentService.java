package com.wizian.web.service;

import com.wizian.web.dto.Appointment; 
import com.wizian.web.repository.AppointmentRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Transactional
    public void saveAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
        appointmentRepository.flush(); // 영속성 컨텍스트의 변경 사항을 즉시 데이터베이스에 반영
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }
}