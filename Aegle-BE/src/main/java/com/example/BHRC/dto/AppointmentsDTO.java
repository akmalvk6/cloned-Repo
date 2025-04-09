package com.example.BHRC.dto;
import com.example.BHRC.model.AppointmentType;
import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentsDTO {

    private Long appointmentBookingId;
    private AppointmentType appointmentType;
    private LocalDate appointmentBookingDate;
    private LocalTime appointmentBookingTime;
    private Long patientId;
    private Long doctorId;
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

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
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





