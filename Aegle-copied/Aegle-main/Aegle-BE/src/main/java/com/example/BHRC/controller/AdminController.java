package com.example.BHRC.controller;
import com.example.BHRC.dto.AdminStaffDTO;
import com.example.BHRC.dto.GenericResponse;
import com.example.BHRC.dto.MedicalHistoryDTO;
import com.example.BHRC.dto.SearchDTO;
import com.example.BHRC.model.Roles;
import com.example.BHRC.service.AdminStaffService;
import com.example.BHRC.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/admin/")
public class AdminController {

    @Autowired
    AdminStaffService adminStaffService;

    private final RoleService roleService;

    public AdminController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public List<Roles> getAllRoles() {
        return roleService.getAllRoles();
    }

    @PostMapping
    public Roles createUser(@RequestBody Roles role) {
        return roleService.saveRole(role);
    }

    @PostMapping("listAdminsAndStaff")
    public GenericResponse listAdminsAndStaff(@RequestBody SearchDTO searchDTO){
        return  adminStaffService.listAllAdminsAndStaff(searchDTO);
    }

    @GetMapping("getAdminStaffDetailsById/{adminStaffId}")
    public GenericResponse getAdminStaffDetailsById(@PathVariable Long adminStaffId){
        return  adminStaffService.getAdminStaffDetailsById(adminStaffId);
    }

    @PostMapping("createOrUpdateAdminStaff")
    public GenericResponse createOrUpdateAdminStaff(@RequestBody AdminStaffDTO adminStaffDTO){
        return  adminStaffService.createOrUpdateAdminStaff(adminStaffDTO);
    }

}
