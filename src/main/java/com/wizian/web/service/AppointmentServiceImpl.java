package com.wizian.web.service;

import com.wizian.web.mapper.AppointmentMapper;
import com.wizian.web.dto.AppointmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private AppointmentMapper appointmentMapper;

    @Override
    public void saveAppointment(AppointmentDTO appointmentDTO) {
        appointmentMapper.insertAppointment(appointmentDTO);
    }

    @Override
    public List<AppointmentDTO> getAllAppointments() {
        return appointmentMapper.getAllAppointments();
    }
}