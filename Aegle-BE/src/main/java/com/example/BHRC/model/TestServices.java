package com.example.BHRC.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class TestServices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long test_service_id;

    private String test_service_name;

    private String test_service_description;

    private Double test_service_fee;

    private String test_service_status;

    @Column(nullable = false)
    private long test_service_doctor_staff; // check the variable type
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date created_date;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date updated_date;

    @PrePersist
    protected void onCreate() {
        Date now = new Date();
        this.created_date = now;
        this.updated_date = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updated_date = new Date();
    }

    // Getters and Setters
    public Long getTest_service_id() {
        return test_service_id;
    }

    public void setTest_service_id(Long test_service_id) {
        this.test_service_id = test_service_id;
    }

    public String getTest_service_name() {
        return test_service_name;
    }

    public void setTest_service_name(String test_service_name) {
        this.test_service_name = test_service_name;
    }

    public String getTest_service_description() {
        return test_service_description;
    }

    public void setTest_service_description(String test_service_description) {
        this.test_service_description = test_service_description;
    }

    public Double getTest_service_fee() {
        return test_service_fee;
    }

    public void setTest_service_fee(Double test_service_fee) {
        this.test_service_fee = test_service_fee;
    }

    public String getTest_service_status() {
        return test_service_status;
    }

    public void setTest_service_status(String test_service_status) {
        this.test_service_status = test_service_status;
    }

    public Long getTest_service_doctor_staff() {
        return test_service_doctor_staff;
    }

    public void setTest_service_doctor_staff(Long test_service_doctor_staff) {
        this.test_service_doctor_staff = test_service_doctor_staff;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public Date getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(Date updated_date) {
        this.updated_date = updated_date;
    }
}
