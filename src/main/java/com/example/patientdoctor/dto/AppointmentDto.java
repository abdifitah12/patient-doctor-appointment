package com.example.patientdoctor.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class AppointmentDto {
    private Long id;
    private Long doctorId;
    private String doctorName;
    private String doctorSpecialty;
    private Long patientId;
    private String patientName;
    private LocalDateTime appointmentDate;

}
