package com.example.patientdoctor.service;

import com.example.patientdoctor.dto.PatientDto;
import com.example.patientdoctor.entity.Doctor;
import com.example.patientdoctor.exception.ResourceNotFoundException;
import com.example.patientdoctor.model.Patient;
import com.example.patientdoctor.repository.DoctorRepository;
import com.example.patientdoctor.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public PatientDto savePatient(PatientDto patientDto) {
        if (patientDto.getDoctorId() == null) {
            throw new IllegalArgumentException("Doctor ID must not be null");
        }

        Doctor doctor = doctorRepository.findById(patientDto.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with ID: " + patientDto.getDoctorId()));

        Patient patient = new Patient();
        patient.setName(patientDto.getName());
        patient.setAge(patientDto.getAge());
        patient.setAilment(patientDto.getAilment());
        patient.setDoctor(doctor);

        Patient savedPatient = patientRepository.save(patient);

        PatientDto savedPatientDto = new PatientDto();
        savedPatientDto.setId(savedPatient.getId());
        savedPatientDto.setName(savedPatient.getName());
        savedPatientDto.setAge(savedPatient.getAge());
        savedPatientDto.setAilment(savedPatient.getAilment());
        savedPatientDto.setDoctorId(doctor.getId());

        return savedPatientDto;
    }



    @Override
    public List<PatientDto> getAllPatients() {
        return patientRepository.findAll().stream()
                .map(patient -> {
                    PatientDto dto = new PatientDto();

                    dto.setName(patient.getName());
                    dto.setAge(patient.getAge());
                    dto.setAilment(patient.getAilment());
                    dto.setDoctorId(patient.getDoctor().getId());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public PatientDto getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
        PatientDto dto = new PatientDto();

        dto.setName(patient.getName());
        dto.setAge(patient.getAge());
        dto.setAilment(patient.getAilment());
        dto.setDoctorId(patient.getDoctor().getId());
        return dto;
    }

    @Override
    public PatientDto updatePatient(Long id, PatientDto patientDto) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
        patient.setName(patientDto.getName());
        patient.setAge(patientDto.getAge());
        patient.setAilment(patientDto.getAilment());
        Doctor doctor = doctorRepository.findById(patientDto.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));
        patient.setDoctor(doctor);
        patientRepository.save(patient);

        return patientDto;
    }

    @Override
    public void deletePatient(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
        patientRepository.delete(patient);
    }
}
