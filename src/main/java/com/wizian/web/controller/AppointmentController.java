package com.wizian.web.controller;

import com.wizian.web.dto.Appointment;
import com.wizian.web.dto.EventData;
import com.wizian.web.dto.PsycounDTO;
import com.wizian.web.service.AppointmentService;

import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


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
    
    @ResponseBody
    @PostMapping("/submitAppointment")
    public ResponseEntity<Map<String, Object>> submitAppointment(HttpSession session, @RequestBody PsycounDTO psycounDTO) {
        
        String userId = (String) session.getAttribute("userId");
        System.out.println("@@@" + userId);
        
        psycounDTO.setUserId(userId);
        System.out.println("컨트롤러 넘어옴?" + psycounDTO.getDate());
        System.out.println(psycounDTO.getEmail());
        System.out.println(psycounDTO.getDate1());
        System.out.println(psycounDTO.getNa());
        System.out.println(psycounDTO.getPhone());
        System.out.println(psycounDTO.getTime());
        System.out.println(psycounDTO.isAgree());
        
        int result = appointmentService.submitAppointment(psycounDTO);
        System.out.println("@@" + result);

        Map<String, Object> response = new HashMap<>();
        response.put("success", result == 1);

        System.out.println("Response: " + response);  // 응답 출력
        return ResponseEntity.ok(response);
    }
    
    
}
