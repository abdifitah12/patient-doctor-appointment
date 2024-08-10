package com.example.patientdoctor.service;

import com.example.patientdoctor.dto.DoctorDto;

import java.util.List;

public interface DoctorService {
    DoctorDto saveDoctor(DoctorDto doctorDto);
    List<DoctorDto> getAllDoctors();
    DoctorDto getDoctorById(Long id);
    DoctorDto updateDoctor(Long id, DoctorDto doctorDto);
    void deleteDoctor(Long id);
}


