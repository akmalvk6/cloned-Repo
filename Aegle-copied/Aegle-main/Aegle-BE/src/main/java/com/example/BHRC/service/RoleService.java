package com.example.BHRC.service;

import com.example.BHRC.model.Roles;
import com.example.BHRC.repository.RolesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RolesRepository roleRepository;

    public RoleService(RolesRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Roles> getAllRoles() {
        return roleRepository.findAll();
    }

    public Roles saveRole(Roles user) {
        return roleRepository.save(user);
    }

    public Roles getRoleByName(String roleName) {
        return roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
    }

    public Roles getRoleById(Long roleId) {
        return roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found for ID: " + roleId));
    }
}
