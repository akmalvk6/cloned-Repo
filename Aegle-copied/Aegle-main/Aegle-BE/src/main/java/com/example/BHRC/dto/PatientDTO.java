package com.example.BHRC.dto;

import com.example.BHRC.model.MedicalHistory;
import com.example.BHRC.model.Patient;

import java.util.Date;

public class PatientDTO {

    private Long patientId;
    private String patientFirstName;
    private String patientLastName;
    private Integer patientAge;
    private String patientBloodGroup;
    private String patientAddress;
    private String pincode;
    private String state;
    private String nationality;
    private String phoneNumber;
    private String patientEmail;
    private Date createdDate;
    private Date updatedDate;

    public PatientDTO() {}

    // Constructor to initialize fields from Patient entity
    public PatientDTO(Patient patient) {
        this.patientId = patient.getPatientId();
        this.patientFirstName = patient.getPatientFirstName();
        this.patientLastName = patient.getPatientLastName();
        this.patientAge = patient.getPatientAge();
        this.patientBloodGroup = patient.getPatientBloodGroup();
        this.patientAddress = patient.getPatientAddress();
        this.pincode = patient.getPincode();
        this.state = patient.getState();
        this.nationality = patient.getNationality();
        this.phoneNumber = patient.getPhoneNumber();
        this.patientEmail = patient.getPatientEmail();
        this.createdDate = patient.getCreatedDate();
        this.updatedDate = patient.getUpdatedDate();
    }



    // Getters and setters
    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public Integer getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(Integer patientAge) {
        this.patientAge = patientAge;
    }

    public String getPatientBloodGroup() {
        return patientBloodGroup;
    }

    public void setPatientBloodGroup(String patientBloodGroup) {
        this.patientBloodGroup = patientBloodGroup;
    }

    public String getPatientAddress() {
        return patientAddress;
    }

    public void setPatientAddress(String patientAddress) {
        this.patientAddress = patientAddress;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}
