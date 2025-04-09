package com.example.BHRC.dto;

import com.example.BHRC.model.MedicalHistory;
import com.example.BHRC.model.Patient;
import jakarta.persistence.*;

import java.util.Date;

public class MedicalHistoryDTO {

    private Long medicalHistoryId;
    private Integer consultationType;
    private String prescription;
    private String additionalNotes;
    private Date createdDate;
    private Date updatedDate;
    private Long patientId;
    private PatientDTO patient;
    public Long getMedicalHistoryId() {
        return medicalHistoryId;
    }

    public void setMedicalHistoryId(Long medicalHistoryId) {
        this.medicalHistoryId = medicalHistoryId;
    }

    public Integer getConsultationType() {
        return consultationType;
    }

    public void setConsultationType(Integer consultationType) {
        this.consultationType = consultationType;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
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

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public PatientDTO getPatient() {
        return patient;
    }

    public void setPatient(PatientDTO patient) {
        this.patient = patient;
    }

    public MedicalHistoryDTO(){};
    public MedicalHistoryDTO(MedicalHistory medicalHistory) {
        this.medicalHistoryId = medicalHistory.getMedicalHistoryId();
        this.consultationType = medicalHistory.getConsulationType();
        this.prescription = medicalHistory.getPrescription();
        this.additionalNotes = medicalHistory.getAdditionalNotes();
        this.createdDate = medicalHistory.getCreatedDate();
    }
}
