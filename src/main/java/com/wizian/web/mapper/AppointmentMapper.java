package com.wizian.web.mapper;

import com.wizian.web.dto.AppointmentDTO;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AppointmentMapper {
    void insertAppointment(AppointmentDTO appointmentDTO);

	List<AppointmentDTO> getAllAppointments();
}