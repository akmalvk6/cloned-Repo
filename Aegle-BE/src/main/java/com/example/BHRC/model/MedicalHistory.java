package com.example.BHRC.model;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "medical_history")
public class MedicalHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medical_history_id")
    private Long medicalHistoryId;


    @Column(name = "consultation_type")
    private Integer consulationType;

    @Lob
    @Column(name = "prescription")
    private String prescription;

    @Lob
    @Column(name = "additional_notes")
    private String additionalNotes;


    @Column(name = "created_date", updatable = false)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_date")
    private Date updatedDate;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    public Long getMedicalHistoryId() {
        return medicalHistoryId;
    }

    public void setMedicalHistoryId(Long medicalHistoryId) {
        this.medicalHistoryId = medicalHistoryId;
    }

    public Integer getConsulationType() {
        return consulationType;
    }

    public void setConsulationType(Integer consulationType) {
        this.consulationType = consulationType;
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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @PrePersist
    public void prePersist() {
        this.createdDate = new Date();
        this.updatedDate = this.createdDate;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedDate = new Date();
    }
}
