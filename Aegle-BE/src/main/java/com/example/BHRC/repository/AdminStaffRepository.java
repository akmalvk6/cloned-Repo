package com.example.BHRC.repository;

import com.example.BHRC.model.AdminAndStaff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdminStaffRepository extends JpaRepository<AdminAndStaff,Long> {

    @Query("SELECT a FROM AdminAndStaff a " +
            "LEFT JOIN a.role r " +
            "WHERE (:keyword IS NULL OR :keyword = '' OR " +
            "LOWER(a.adminStaffFirstName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(a.adminStaffState) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
            "AND (:roleType IS NULL OR r.roleType = :roleType) ")
    Page<AdminAndStaff> searchAdminStaffWithFilters(@Param("keyword") String keyword,
                                                    @Param("roleType") Integer roleType,
                                                    Pageable pageable);

    @Query("SELECT COUNT(a) FROM AdminAndStaff a " +
            "LEFT JOIN a.role r " +
            "WHERE (:keyword IS NULL OR :keyword = '' OR " +
            "LOWER(a.adminStaffFirstName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(a.adminStaffState) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
            "AND (:roleType IS NULL OR r.roleType = :roleType) ")
    Long countAdminStaffWithFilters(@Param("keyword") String keyword,
                                    @Param("roleType") Integer roleType);

}
