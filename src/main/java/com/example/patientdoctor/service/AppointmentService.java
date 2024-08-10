package com.example.patientdoctor.service;

import com.example.patientdoctor.dto.AppointmentDto;

import java.util.List;

public interface AppointmentService {
    AppointmentDto createAppointment(AppointmentDto appointmentDto);
    List<AppointmentDto> getAllAppointments();
    AppointmentDto getAppointmentById(Long id);
    AppointmentDto updateAppointment(Long id, AppointmentDto appointmentDto);
    void deleteAppointment(Long id);
}
