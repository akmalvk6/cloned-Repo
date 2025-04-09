package com.example.BHRC.dto;

public class DoctorDTO {

    private Long doctorId;
    private String doctorName;
    private String doctorSvg;
    private String qualification;
    private int yearOfExp;
    private double doctorFee;
    private Long doctorsTimingId;
    private Long specializationId;

    public DoctorDTO() {
    }

    public DoctorDTO(Long doctorId, String doctorName, String doctorSvg, String qualification, int yearOfExp, double doctorFee, Long doctorsTimingId, Long specializationId) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.doctorSvg = doctorSvg;
        this.qualification = qualification;
        this.yearOfExp = yearOfExp;
        this.doctorFee = doctorFee;
        this.doctorsTimingId = doctorsTimingId;
        this.specializationId = specializationId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorSvg() {
        return doctorSvg;
    }

    public void setDoctorSvg(String doctorSvg) {
        this.doctorSvg = doctorSvg;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public int getYearOfExp() {
        return yearOfExp;
    }

    public void setYearOfExp(int yearOfExp) {
        this.yearOfExp = yearOfExp;
    }

    public double getDoctorFee() {
        return doctorFee;
    }

    public void setDoctorFee(double doctorFee) {
        this.doctorFee = doctorFee;
    }

    public Long getDoctorsTimingId() {
        return doctorsTimingId;
    }

    public void setDoctorsTimingId(Long doctorsTimingId) {
        this.doctorsTimingId = doctorsTimingId;
    }

    public Long getSpecializationId() {
        return specializationId;
    }

    public void setSpecializationId(Long specializationId) {
        this.specializationId = specializationId;
    }
}
