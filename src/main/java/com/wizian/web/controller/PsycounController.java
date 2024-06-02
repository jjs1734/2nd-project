package com.wizian.web.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wizian.web.dto.PsycounDTO;
import com.wizian.web.service.PsycounService;

@RestController
@RequestMapping("/api")
public class PsycounController {

    @Autowired
    private PsycounService psycounService;

    @PostMapping("/savePsyExamVisitRequest")
    public ResponseEntity<String> savePsyExamVisitRequest(@RequestBody List<PsycounDTO> psycounDTOList) {
        try {
        	// 데이터 수신 로그
            System.out.println("Received Data: " + psycounDTOList);
        	
            // 중복 학번 제거
            Map<String, PsycounDTO> uniqueMap = new LinkedHashMap<>();
            for (PsycounDTO dto : psycounDTOList) {
                uniqueMap.put(dto.getStudNo(), dto);
            }
            List<PsycounDTO> uniqueList = new ArrayList<>(uniqueMap.values());

            psycounService.saveAll(uniqueList);
            return ResponseEntity.ok("Success");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving data");
        }
    }
    
    @GetMapping("/psycoun/getAllRequests")
    public ResponseEntity<List<PsycounDTO>> getAllRequests() {
        List<PsycounDTO> requests = psycounService.getAllRequests();
        return ResponseEntity.ok(requests);
    }
}