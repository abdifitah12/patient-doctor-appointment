package com.example.patientdoctor.dto;

import lombok.Data;

import java.util.List;

@Data
public class DoctorDto {
    private Long id;
    private String name;
    private String specialty;
    private List<PatientDto> patients;

    // getters and setters
}
