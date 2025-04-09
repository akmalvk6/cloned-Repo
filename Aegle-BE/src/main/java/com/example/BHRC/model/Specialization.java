package com.example.BHRC.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Specialization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long specialization_id;

    private String specialization_name;

    @Lob
    @Column(name = "specialization_svg", columnDefinition = "LONGTEXT")
    private String specialization_svg;

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

    public Long getSpecialization_id() {
        return specialization_id;
    }

    public void setSpecialization_id(Long specialization_id) {
        this.specialization_id = specialization_id;
    }

    public String getSpecialization_name() {
        return specialization_name;
    }

    public void setSpecialization_name(String specialization_name) {
        this.specialization_name = specialization_name;
    }

    public String getSpecialization_svg() {
        return specialization_svg;
    }

    public void setSpecialization_svg(String specialization_svg) {
        this.specialization_svg = specialization_svg;
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
