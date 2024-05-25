package com.wizian.web.mapper;

import com.wizian.web.dto.Appointment;
import java.util.List;

public interface AppointmentMapper {
    void saveAppointment(Appointment appointment);
    List<Appointment> getAllAppointments();
}