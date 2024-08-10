package com.example.patientdoctor.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String specialty;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<com.example.patientdoctor.model.Patient> patients;

}
