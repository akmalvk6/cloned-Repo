package com.example.BHRC.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;

public class DoctorWeeklyTimingDTO {

    private Long doctorWeeklyTimingId;

    @NotNull(message = "Week number is required")
    @Min(value = 1, message = "Week number must be between 1 and 7")
    @Max(value = 7, message = "Week number must be between 1 and 7")
    private Integer weekNo;

    @NotNull(message = "Start time is required")
    private LocalTime startTime;

    @NotNull(message = "End time is required")
    private LocalTime endTime;

    @NotNull(message = "Doctor ID is required")
    private Long doctorId;

    public DoctorWeeklyTimingDTO() {
    }

    public DoctorWeeklyTimingDTO(Long doctorWeeklyTimingId, Integer weekNo, LocalTime startTime,
                                 LocalTime endTime, Long doctorId) {
        this.doctorWeeklyTimingId = doctorWeeklyTimingId;
        this.weekNo = weekNo;
        this.startTime = startTime;
        this.endTime = endTime;
        this.doctorId = doctorId;
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

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }
}