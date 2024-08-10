package com.example.patientdoctor.controller;
import com.example.patientdoctor.dto.AppointmentDto;
import com.example.patientdoctor.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    public AppointmentDto createAppointment(@RequestBody @Valid AppointmentDto appointmentDto) {
        return appointmentService.createAppointment(appointmentDto);
    }



    @GetMapping
    public List<AppointmentDto> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/{id}")
    public AppointmentDto getAppointmentById(@PathVariable Long id) {
        return appointmentService.getAppointmentById(id);
    }

    @PutMapping("/{id}")
    public AppointmentDto updateAppointment(@PathVariable Long id, @RequestBody @Valid AppointmentDto appointmentDto) {
        return appointmentService.updateAppointment(id, appointmentDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }
}
