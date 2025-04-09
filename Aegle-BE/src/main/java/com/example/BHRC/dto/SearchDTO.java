package com.example.BHRC.dto;

import java.time.LocalDate;
import java.util.Date;

public class SearchDTO {

    private String keyword;
    private Integer start;
    private Integer pageSize;
    private  Integer age;
    private String bloodGroup;
    private String state;
    private String sortBy;
    private String sortDirection;
    private Date createdDate;
    private Long patientId;
    private  Integer roleType;


    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    public SearchDTO() {}

    // Constructor with all fields
    public SearchDTO(String keyword, Integer start, Integer pageSize,Integer age,String bloodGroup,String state,String sortBy,String sortDirection) {
        this.keyword = keyword;
        this.start = start;
        this.pageSize = pageSize;
        this.age = age;
        this.bloodGroup = bloodGroup;
        this.state = state;
        this.sortBy = sortBy;
        this.sortDirection = sortDirection;
    }


}
