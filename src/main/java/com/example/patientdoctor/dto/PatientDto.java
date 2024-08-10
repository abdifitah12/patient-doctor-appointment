package com.example.patientdoctor.dto;

import lombok.Data;

@Data
public class PatientDto {
    private Long id;
    private String name;
    private int age;
    private String ailment;
    private Long doctorId;

    // getters and setters
}

