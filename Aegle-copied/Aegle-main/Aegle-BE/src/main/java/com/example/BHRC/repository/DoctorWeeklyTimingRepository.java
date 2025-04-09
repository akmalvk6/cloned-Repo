package com.example.BHRC.repository;


import com.example.BHRC.model.DoctorWeeklyTiming;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorWeeklyTimingRepository extends JpaRepository<DoctorWeeklyTiming, Long> {

    List<DoctorWeeklyTiming> findByDoctorDoctorId(Long doctorId);

    List<DoctorWeeklyTiming> findByDoctorDoctorIdAndWeekNo(Long doctorId, Integer weekNo);

    List<DoctorWeeklyTiming> findByWeekNo(Integer weekNumber);
}
