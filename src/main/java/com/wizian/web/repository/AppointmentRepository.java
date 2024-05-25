package com.wizian.web.repository;

import com.wizian.web.dto.Appointment;
import com.wizian.web.dto.PsycounDTO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

}