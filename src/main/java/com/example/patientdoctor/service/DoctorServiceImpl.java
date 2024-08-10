package com.example.patientdoctor.service;

import com.example.patientdoctor.dto.DoctorDto;
import com.example.patientdoctor.dto.PatientDto;
import com.example.patientdoctor.entity.Doctor;
import com.example.patientdoctor.exception.ResourceNotFoundException;

import com.example.patientdoctor.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public DoctorDto saveDoctor(DoctorDto doctorDto) {
        Doctor doctor = new Doctor();
        doctor.setName(doctorDto.getName());
        doctor.setSpecialty(doctorDto.getSpecialty());
        doctor = doctorRepository.save(doctor);
        doctorDto.setId(doctor.getId());
        return doctorDto;
    }

    @Override
    public List<DoctorDto> getAllDoctors() {
        return doctorRepository.findAll().stream()
                .map(doctor -> {
                    DoctorDto dto = new DoctorDto();
                    dto.setId(doctor.getId());
                    dto.setName(doctor.getName());
                    dto.setSpecialty(doctor.getSpecialty());
                    dto.setPatients(doctor.getPatients().stream().map(patient -> {
                        PatientDto patientDto = new PatientDto();

                        patientDto.setName(patient.getName());
                        patientDto.setAge(patient.getAge());
                        patientDto.setAilment(patient.getAilment());
                        patientDto.setDoctorId(doctor.getId());
                        return patientDto;
                    }).collect(Collectors.toList()));
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public DoctorDto getDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));
        DoctorDto dto = new DoctorDto();
        dto.setId(doctor.getId());
        dto.setName(doctor.getName());
        dto.setSpecialty(doctor.getSpecialty());
        dto.setPatients(doctor.getPatients().stream().map(patient -> {
            PatientDto patientDto = new PatientDto();

            patientDto.setName(patient.getName());
            patientDto.setAge(patient.getAge());
            patientDto.setAilment(patient.getAilment());
            patientDto.setDoctorId(doctor.getId());
            return patientDto;
        }).collect(Collectors.toList()));
        return dto;
    }

    @Override
    public DoctorDto updateDoctor(Long id, DoctorDto doctorDto) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));
        doctor.setName(doctorDto.getName());
        doctor.setSpecialty(doctorDto.getSpecialty());
        doctor = doctorRepository.save(doctor);
        doctorDto.setId(doctor.getId());
        return doctorDto;
    }

    @Override
    public void deleteDoctor(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));
        doctorRepository.delete(doctor);
    }
}
