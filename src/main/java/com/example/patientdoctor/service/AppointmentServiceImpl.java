package com.example.patientdoctor.service;



import com.example.patientdoctor.dto.AppointmentDto;
import com.example.patientdoctor.entity.Appointment;
import com.example.patientdoctor.entity.Doctor;
import com.example.patientdoctor.exception.ResourceNotFoundException;

import com.example.patientdoctor.model.Patient;
import com.example.patientdoctor.repository.AppointmentRepository;
import com.example.patientdoctor.repository.DoctorRepository;
import com.example.patientdoctor.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public AppointmentDto createAppointment(AppointmentDto appointmentDto) {
        Appointment appointment = new Appointment();
        Doctor doctor = doctorRepository.findById(appointmentDto.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));
        Patient patient = patientRepository.findById(appointmentDto.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setAppointmentDate(appointmentDto.getAppointmentDate());
        appointment = appointmentRepository.save(appointment);
        appointmentDto.setId(appointment.getId());
        return appointmentDto;
    }



    @Override
    public List<AppointmentDto> getAllAppointments() {
        return appointmentRepository.findAll().stream().map(appointment -> {
            AppointmentDto dto = new AppointmentDto();
            dto.setId(appointment.getId());
            dto.setDoctorId(appointment.getDoctor().getId());
            dto.setDoctorName(appointment.getDoctor().getName());
            dto.setDoctorSpecialty(appointment.getDoctor().getSpecialty());
            dto.setPatientId(appointment.getPatient().getId());
            dto.setPatientName(appointment.getPatient().getName());
            dto.setAppointmentDate(appointment.getAppointmentDate());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public AppointmentDto getAppointmentById(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));
        AppointmentDto dto = new AppointmentDto();
        dto.setId(appointment.getId());
        dto.setDoctorId(appointment.getDoctor().getId());
        dto.setPatientId(appointment.getPatient().getId());
        dto.setAppointmentDate(appointment.getAppointmentDate());
        return dto;
    }

    @Override
    public AppointmentDto updateAppointment(Long id, AppointmentDto appointmentDto) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));
        Doctor doctor = doctorRepository.findById(appointmentDto.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));
        Patient patient = patientRepository.findById(appointmentDto.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setAppointmentDate(appointmentDto.getAppointmentDate());
        appointment = appointmentRepository.save(appointment);
        appointmentDto.setId(appointment.getId());
        return appointmentDto;
    }

    @Override
    public void deleteAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));
        appointmentRepository.delete(appointment);
    }
}
