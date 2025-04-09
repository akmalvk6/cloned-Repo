package com.example.BHRC.repository;

import com.example.BHRC.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PatientRepository extends JpaRepository<Patient,Long> {

    @Query("SELECT p FROM Patient p WHERE " +
            "(:keyword IS NULL OR :keyword = '' OR " +
            "LOWER(p.patientFirstName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.patientLastName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.patientEmail) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.pincode) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
            "AND (:age IS NULL OR p.patientAge = :age) " +
            "AND (:bloodGroup IS NULL OR p.patientBloodGroup = :bloodGroup) " +
            "AND (:state IS NULL OR p.state = :state) ")
    Page<Patient> searchPatientsWithFilters(@Param("keyword") String keyword,
                                            @Param("age") Integer age,
                                            @Param("bloodGroup") String bloodGroup,
                                            @Param("state") String state,
                                            Pageable pageable);


    @Query("SELECT p FROM Patient p WHERE p.patientId = ?1")
    Patient getPatientById(Long patientId);

    @Query("SELECT COUNT(p) FROM Patient p WHERE " +
            "(:keyword IS NULL OR :keyword = '' OR " +
            "LOWER(p.patientFirstName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.patientLastName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.patientEmail) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.pincode) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
            "AND (:age IS NULL OR p.patientAge = :age) " +
            "AND (:bloodGroup IS NULL OR p.patientBloodGroup = :bloodGroup) " +
            "AND (:state IS NULL OR p.state = :state) ")
    long countPatientsWithFilters(@Param("keyword") String keyword,
                                  @Param("age") Integer age,
                                  @Param("bloodGroup") String bloodGroup,
                                  @Param("state") String state);

}
