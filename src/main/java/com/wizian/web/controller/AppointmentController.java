package com.wizian.web.controller;

import com.wizian.web.dto.Appointment;
import com.wizian.web.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AppointmentController {

    private final AppointmentService appointmentService;
    
    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }
    
    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("appointment", new Appointment());
        return "form";
    }

    @PostMapping("/submit")
    public String submitForm(@ModelAttribute Appointment appointment) {
        appointmentService.saveAppointment(appointment);
        return "redirect:/pcoun";
    }

    @GetMapping("/pcoun")
    public String viewAppointments(Model model) {
        model.addAttribute("appointments", appointmentService.getAllAppointments());
        return "pcoun";
    }
}
