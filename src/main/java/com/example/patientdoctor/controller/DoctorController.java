package com.example.patientdoctor.controller;

import com.example.patientdoctor.dto.DoctorDto;
import com.example.patientdoctor.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping
    public DoctorDto createDoctor(@RequestBody @Valid DoctorDto doctorDto) {
        return doctorService.saveDoctor(doctorDto);
    }

    @GetMapping
    public List<DoctorDto> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("/{id}")
    public DoctorDto getDoctorById(@PathVariable Long id) {
        return doctorService.getDoctorById(id);
    }

    @PutMapping("/{id}")
    public DoctorDto updateDoctor(@PathVariable Long id, @RequestBody @Valid DoctorDto doctorDto) {
        return doctorService.updateDoctor(id, doctorDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }
}
