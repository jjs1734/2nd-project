package com.wizian.web.service;

import java.util.List;

import com.wizian.web.dto.AppointmentDTO;

public interface AppointmentService {
    void saveAppointment(AppointmentDTO appointmentDTO);
    List<AppointmentDTO> getAllAppointments();
}