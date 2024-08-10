package com.example.patientdoctor.model;


import com.example.patientdoctor.entity.Doctor;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    private String ailment;


    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    // getters and setters
}

