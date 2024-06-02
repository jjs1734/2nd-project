package com.wizian.web.dao;

import com.wizian.web.dto.AppointmentDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AppointmentDAO {
    void insertAppointment(AppointmentDTO appointmentDTO);
}
