package com.example.patientdoctor.service;

import com.example.patientdoctor.dto.PatientDto;

import java.util.List;

public interface PatientService {
    PatientDto savePatient(PatientDto patientDto);
    List<PatientDto> getAllPatients();
    PatientDto getPatientById(Long id);
    PatientDto updatePatient(Long id, PatientDto patientDto);
    void deletePatient(Long id);
}


