package com.example.BHRC.model;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "admin_and_staff")
public class AdminAndStaff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_staff_Id")
    private Long adminStaffId;


    @Column(name = "admin_staff_first_name")
    private String adminStaffFirstName;

    @Column(name = "admin_staff_last_name")
    private String adminStaffLastName;

    @Lob
    @Column(name = "admin_staff_address")
    private String adminStaffAddress;

    @Column(name = "admin_staff_pincode")
    private String adminStaffPincode;

    @Column(name = "admin_staff_state")
    private String adminStaffState;

    @Column(name = "admin_staff_nationality")
    private String adminStaffNationality;

    @Column(name = "admin_staff_number", length = 15)
    private String adminStaffPhoneNumber;

    @Column(name = "admin_staff_email")
    private String adminStaffEmail;

    @OneToOne
    @JoinColumn(name = "role_id") // Foreign key in admin_and_staff table
    private Roles role;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", updatable = false)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_date")
    private Date updatedDate;


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

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    @PrePersist
    public void prePersist() {
        this.createdDate = new Date();
        this.updatedDate = this.createdDate;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedDate = new Date();
    }
}
