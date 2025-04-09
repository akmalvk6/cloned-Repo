package com.example.BHRC.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.BHRC.model.Doctor;
import com.example.BHRC.repository.DoctorRepository;
import java.time.LocalDateTime;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id).orElseThrow(() -> new RuntimeException("Doctor not found"));
    }

    public Doctor addDoctor(Doctor doctor) {
        doctor.setCreatedDate(LocalDateTime.now());
        doctor.setUpdatedDate(LocalDateTime.now());
        return doctorRepository.save(doctor);
    }

    public Doctor updateDoctor(Long id, Doctor doctorDetails) {
        Doctor doctor = getDoctorById(id);

        doctor.setDoctorName(doctorDetails.getDoctorName());
        doctor.setDoctorSvg(doctorDetails.getDoctorSvg());
        doctor.setQualification(doctorDetails.getQualification());
        doctor.setYearOfExp(doctorDetails.getYearOfExp());
        doctor.setDoctorFee(doctorDetails.getDoctorFee());
        doctor.setDoctorsTimingId(doctorDetails.getDoctorsTimingId());
        doctor.setSpecializationId(doctorDetails.getSpecializationId());
        doctor.setUpdatedDate(LocalDateTime.now());

        return doctorRepository.save(doctor);
    }

    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }
}
