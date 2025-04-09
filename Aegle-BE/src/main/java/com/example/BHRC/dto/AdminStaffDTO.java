package com.example.BHRC.dto;

import com.example.BHRC.model.AdminAndStaff;
import com.example.BHRC.model.Roles;

import java.util.Date;

public class AdminStaffDTO {

    private Long adminStaffId;
    private String adminStaffFirstName;
    private String adminStaffLastName;
    private String adminStaffAddress;
    private String adminStaffPincode;
    private String adminStaffState;
    private String adminStaffNationality;
    private String adminStaffPhoneNumber;
    private String adminStaffEmail;
    private Date createdDate;
    private Date updatedDate;
    private Integer roleType;
    private Roles roles;

    public Long getAdminStaffId() {
        return adminStaffId;
    }

    public void setAdminStaffId(Long adminStaffId) {
        this.adminStaffId = adminStaffId;
    }

    public String getAdminStaffFirstName() {
        return adminStaffFirstName;
    }

    public void setAdminStaffFirstName(String adminStaffFirstName) {
        this.adminStaffFirstName = adminStaffFirstName;
    }

    public String getAdminStaffLastName() {
        return adminStaffLastName;
    }

    public void setAdminStaffLastName(String adminStaffLastName) {
        this.adminStaffLastName = adminStaffLastName;
    }

    public String getAdminStaffAddress() {
        return adminStaffAddress;
    }

    public void setAdminStaffAddress(String adminStaffAddress) {
        this.adminStaffAddress = adminStaffAddress;
    }

    public String getAdminStaffPincode() {
        return adminStaffPincode;
    }

    public void setAdminStaffPincode(String adminStaffPincode) {
        this.adminStaffPincode = adminStaffPincode;
    }

    public String getAdminStaffState() {
        return adminStaffState;
    }

    public void setAdminStaffState(String adminStaffState) {
        this.adminStaffState = adminStaffState;
    }

    public String getAdminStaffNationality() {
        return adminStaffNationality;
    }

    public void setAdminStaffNationality(String adminStaffNationality) {
        this.adminStaffNationality = adminStaffNationality;
    }

    public String getAdminStaffPhoneNumber() {
        return adminStaffPhoneNumber;
    }

    public void setAdminStaffPhoneNumber(String adminStaffPhoneNumber) {
        this.adminStaffPhoneNumber = adminStaffPhoneNumber;
    }

    public String getAdminStaffEmail() {
        return adminStaffEmail;
    }

    public void setAdminStaffEmail(String adminStaffEmail) {
        this.adminStaffEmail = adminStaffEmail;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public AdminStaffDTO() {
    }

    public AdminStaffDTO(AdminAndStaff adminAndStaff) {
        this.adminStaffId = adminAndStaff.getAdminStaffId();
        this.adminStaffFirstName = adminAndStaff.getAdminStaffFirstName();
        this.adminStaffLastName = adminAndStaff.getAdminStaffLastName();
        this.adminStaffAddress = adminAndStaff.getAdminStaffAddress();
        this.adminStaffPincode = adminAndStaff.getAdminStaffPincode();
        this.adminStaffState = adminAndStaff.getAdminStaffState();
        this.adminStaffNationality = adminAndStaff.getAdminStaffNationality();
        this.adminStaffPhoneNumber = adminAndStaff.getAdminStaffPhoneNumber();
        this.adminStaffEmail = adminAndStaff.getAdminStaffEmail();
        this.createdDate = adminAndStaff.getCreatedDate();
        this.updatedDate = adminAndStaff.getUpdatedDate();

        // Mapping Role information
        if (adminAndStaff.getRole() != null) {
            this.roles = adminAndStaff.getRole();
            this.roleType = adminAndStaff.getRole().getRoleType();

        }
    }

}
