package com.wizian.web.controller;

import com.wizian.web.dto.Appointment;
import com.wizian.web.dto.EventData;
import com.wizian.web.dto.PsycounDTO;
import com.wizian.web.service.AppointmentService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


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
    
    @PostMapping("/submitAppointment")
    public String submitAppointment(HttpSession session, @RequestBody PsycounDTO psycounDTO) {
        
    	String userId = (String) session.getAttribute("userId");
    	System.out.println("@@@" + userId);
    	
    	psycounDTO.setUserId(userId);
    	System.out.println("컨트롤러 넘어옴?" + psycounDTO.getDate());
    	System.out.println(psycounDTO.getEmail());
    	System.out.println(psycounDTO.getDate());
    	System.out.println(psycounDTO.getNa());
    	System.out.println(psycounDTO.getPhone());
    	System.out.println(psycounDTO.getTime());
    	
    	appointmentService.submitAppointment(psycounDTO);
    	
    	return "main";
    }
    
    
}
