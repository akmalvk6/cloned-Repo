package com.example.BHRC.repository;

import com.example.BHRC.model.MedicalHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;

public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, Long> {

    @Query("SELECT mh FROM MedicalHistory mh WHERE " +
            "(mh.patient.id = :patientId) ")
    Page<MedicalHistory> searchMedicalHistory(@Param("patientId") Long patientId,
                                              Pageable pageable);


}
