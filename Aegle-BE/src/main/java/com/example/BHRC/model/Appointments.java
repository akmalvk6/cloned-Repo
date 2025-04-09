package com.example.BHRC.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "appointments")
public class Appointments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentBookingId;

    @Enumerated(EnumType.STRING)
    private AppointmentType appointmentType;

    private LocalDate appointmentBookingDate;
    private LocalTime appointmentBookingTime;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    private LocalDate createdDate;
    private LocalDate updatedDate;

    public Long getAppointmentBookingId() {
        return appointmentBookingId;
    }

    public void setAppointmentBookingId(Long appointmentBookingId) {
        this.appointmentBookingId = appointmentBookingId;
    }

    public AppointmentType getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(AppointmentType appointmentType) {
        this.appointmentType = appointmentType;
    }

    public LocalDate getAppointmentBookingDate() {
        return appointmentBookingDate;
    }

    public void setAppointmentBookingDate(LocalDate appointmentBookingDate) {
        this.appointmentBookingDate = appointmentBookingDate;
    }

    public LocalTime getAppointmentBookingTime() {
        return appointmentBookingTime;
    }

    public void setAppointmentBookingTime(LocalTime appointmentBookingTime) {
        this.appointmentBookingTime = appointmentBookingTime;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }
}

