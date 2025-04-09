package com.example.BHRC.repository;

import com.example.BHRC.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Roles, Long> {
    Optional<Roles> findByRoleName(String roleName);

    @Query("SELECT r FROM Roles r WHERE r.roleType = ?1")
    Roles getByRoleType(Integer roleType);

}
