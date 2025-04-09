package com.example.BHRC.model;

import jakarta.persistence.*;
import java.time.LocalTime;
import java.time.LocalDateTime;

@Entity
@Table(name = "doctor_weekly_timings")
public class DoctorWeeklyTiming {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorWeeklyTimingId;

    @Column(nullable = false)
    private Integer weekNo;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    @Column
    private LocalDateTime updatedDate;

    @PrePersist
    protected void onCreate() {
        createdDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDate = LocalDateTime.now();
    }

    public DoctorWeeklyTiming() {
    }

    public DoctorWeeklyTiming(Long doctorWeeklyTimingId, Integer weekNo, LocalTime startTime,
                              LocalTime endTime, Doctor doctor, LocalDateTime createdDate,
                              LocalDateTime updatedDate) {
        this.doctorWeeklyTimingId = doctorWeeklyTimingId;
        this.weekNo = weekNo;
        this.startTime = startTime;
        this.endTime = endTime;
        this.doctor = doctor;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public Long getDoctorWeeklyTimingId() {
        return doctorWeeklyTimingId;
    }

    public void setDoctorWeeklyTimingId(Long doctorWeeklyTimingId) {
        this.doctorWeeklyTimingId = doctorWeeklyTimingId;
    }

    public Integer getWeekNo() {
        return weekNo;
    }

    public void setWeekNo(Integer weekNo) {
        this.weekNo = weekNo;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }
}