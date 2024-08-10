package com.example.patientdoctor.controller;

import com.example.patientdoctor.dto.PatientDto;
import com.example.patientdoctor.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping
    public PatientDto createPatient(@RequestBody @Valid PatientDto patientDto) {
        return patientService.savePatient(patientDto);
    }



    @GetMapping
    public List<PatientDto> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public PatientDto getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }

    @PutMapping("/{id}")
    public PatientDto updatePatient(@PathVariable Long id, @RequestBody @Valid PatientDto patientDto) {
        return patientService.updatePatient(id, patientDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}
