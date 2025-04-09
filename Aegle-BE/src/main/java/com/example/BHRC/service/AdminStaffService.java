package com.example.BHRC.service;


import com.example.BHRC.dto.*;
import com.example.BHRC.model.AdminAndStaff;
import com.example.BHRC.model.Roles;
import com.example.BHRC.repository.AdminStaffRepository;
import com.example.BHRC.repository.RolesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminStaffService {

    @Autowired
    CommonService commonService;

    @Autowired
    AdminStaffRepository adminStaffRepository;

    @Autowired
    RolesRepository roleRepository;

    private static final Logger adminStaffServiceLog = LoggerFactory.getLogger(AdminStaffService.class);

    public GenericResponse listAllAdminsAndStaff(SearchDTO searchDTO){
        GenericResponse genericResponse = new GenericResponse();
        Map<String,Object> responseData = new HashMap<>();
        try{
            int pageNumber = (searchDTO.getStart() != null) ? searchDTO.getStart() : 0;
            int pageSize = (searchDTO.getPageSize() != null) ? searchDTO.getPageSize() : 10;
            Sort sort = commonService.determineSort(searchDTO, "createdDate");
            Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
            Long count = adminStaffRepository.countAdminStaffWithFilters(searchDTO.getKeyword(), searchDTO.getRoleType());
            if(count != null && count >0){
                Page<AdminAndStaff> adminStaffList = adminStaffRepository.searchAdminStaffWithFilters(searchDTO.getKeyword(), searchDTO.getRoleType(), pageable);
                List<AdminStaffDTO> medicalHistoryDTOList = adminStaffList.stream()
                        .map(this::convertToAdminStaffDTO)
                        .collect(Collectors.toList());
                responseData.put("data",medicalHistoryDTOList);
                responseData.put("count",count);
                genericResponse.setData(responseData);
                genericResponse.setStatus(true);

            }else{
                genericResponse.setMessage("No Data Found");
                genericResponse.setStatus(true);
            }

        }catch (Exception e){
            genericResponse.setStatus(false);
            genericResponse.setError("Failed to fetch");
            adminStaffServiceLog.error(e.toString(),"listAllAdminsAndStaff{}");

        }
        return genericResponse;
    }

    public GenericResponse  getAdminStaffDetailsById(Long adminStaffId){
        GenericResponse genericResponse = new GenericResponse();
        try{
            Optional<AdminAndStaff> adminAndStaffOptional = adminStaffRepository.findById(adminStaffId);
            if (adminAndStaffOptional.isPresent()) {
                AdminAndStaff adminAndStaff = adminAndStaffOptional.get();
                AdminStaffDTO adminStaffDTO = new AdminStaffDTO(adminAndStaff);
                genericResponse.setStatus(true);
                genericResponse.setData(adminStaffDTO);
            } else {
                genericResponse.setStatus(false);
                genericResponse.setError(" Data not found with ID: " + adminStaffId);
            }
        }catch (Exception  e){
            adminStaffServiceLog.error(e.toString(),"getAdminStaffDetailsById{}");
            genericResponse.setError("Failed to fetch");
            genericResponse.setStatus(false);

        }
        return  genericResponse;
    }

    public GenericResponse createOrUpdateAdminStaff(AdminStaffDTO adminStaffDTO){
        GenericResponse genericResponse = new GenericResponse();
        try{
            if(adminStaffDTO !=null){
                AdminAndStaff adminAndStaff = convertToAdminStaffEntity(adminStaffDTO);
                adminStaffRepository.save(adminAndStaff);
                if (adminStaffDTO.getAdminStaffId() != null) {
                    genericResponse.setMessage("Data updated successfully");

                } else {
                    genericResponse.setMessage("Data created successfully");
                }
            }else{
                genericResponse.setError("Empty data recieved");
                genericResponse.setStatus(false);
            }

        }catch (Exception e){
            adminStaffServiceLog.error(e.toString(),"createOrUpdateAdminStaff{}");
            genericResponse.setStatus(false);
            genericResponse.setError("Failed to fetch");

        }
        return genericResponse;
    }


    private AdminStaffDTO convertToAdminStaffDTO(AdminAndStaff adminAndStaff) {
        if (adminAndStaff == null) {
            return null;
        }

        AdminStaffDTO adminStaffDTO = new AdminStaffDTO();

        if (adminAndStaff.getAdminStaffId() != null) {
            adminStaffDTO.setAdminStaffId(adminAndStaff.getAdminStaffId());
        }

        if (adminAndStaff.getAdminStaffFirstName() != null && !adminAndStaff.getAdminStaffFirstName().isEmpty()) {
            adminStaffDTO.setAdminStaffFirstName(adminAndStaff.getAdminStaffFirstName());
        }

        if (adminAndStaff.getAdminStaffLastName() != null && !adminAndStaff.getAdminStaffLastName().isEmpty()) {
            adminStaffDTO.setAdminStaffLastName(adminAndStaff.getAdminStaffLastName());
        }

        if (adminAndStaff.getAdminStaffAddress() != null && !adminAndStaff.getAdminStaffAddress().isEmpty()) {
            adminStaffDTO.setAdminStaffAddress(adminAndStaff.getAdminStaffAddress());
        }

        if (adminAndStaff.getAdminStaffPincode() != null && !adminAndStaff.getAdminStaffPincode().isEmpty()) {
            adminStaffDTO.setAdminStaffPincode(adminAndStaff.getAdminStaffPincode());
        }

        if (adminAndStaff.getAdminStaffState() != null && !adminAndStaff.getAdminStaffState().isEmpty()) {
            adminStaffDTO.setAdminStaffState(adminAndStaff.getAdminStaffState());
        }

        if (adminAndStaff.getAdminStaffNationality() != null && !adminAndStaff.getAdminStaffNationality().isEmpty()) {
            adminStaffDTO.setAdminStaffNationality(adminAndStaff.getAdminStaffNationality());
        }

        if (adminAndStaff.getAdminStaffPhoneNumber() != null && !adminAndStaff.getAdminStaffPhoneNumber().isEmpty()) {
            adminStaffDTO.setAdminStaffPhoneNumber(adminAndStaff.getAdminStaffPhoneNumber());
        }

        if (adminAndStaff.getAdminStaffEmail() != null && !adminAndStaff.getAdminStaffEmail().isEmpty()) {
            adminStaffDTO.setAdminStaffEmail(adminAndStaff.getAdminStaffEmail());
        }

        if (adminAndStaff.getCreatedDate() != null) {
            adminStaffDTO.setCreatedDate(adminAndStaff.getCreatedDate());
        }

        if (adminAndStaff.getUpdatedDate() != null) {
            adminStaffDTO.setUpdatedDate(adminAndStaff.getUpdatedDate());
        }

        if (adminAndStaff.getRole() != null) {
            adminStaffDTO.setRoles(adminAndStaff.getRole());

            if (adminAndStaff.getRole().getRoleType() != null) {
                adminStaffDTO.setRoleType(adminAndStaff.getRole().getRoleType());
            }
        }

        return adminStaffDTO;
    }

    private AdminAndStaff convertToAdminStaffEntity(AdminStaffDTO adminStaffDTO) {
        if (adminStaffDTO == null) {
            return null;
        }

        AdminAndStaff adminAndStaff = new AdminAndStaff();

        // Set fields from AdminStaffDTO to AdminAndStaff entity
        if (adminStaffDTO.getAdminStaffId() != null) {
            adminAndStaff.setAdminStaffId(adminStaffDTO.getAdminStaffId());
        }

        if (adminStaffDTO.getAdminStaffFirstName() != null && !adminStaffDTO.getAdminStaffFirstName().isEmpty()) {
            adminAndStaff.setAdminStaffFirstName(adminStaffDTO.getAdminStaffFirstName());
        }

        if (adminStaffDTO.getAdminStaffLastName() != null && !adminStaffDTO.getAdminStaffLastName().isEmpty()) {
            adminAndStaff.setAdminStaffLastName(adminStaffDTO.getAdminStaffLastName());
        }

        if (adminStaffDTO.getAdminStaffAddress() != null && !adminStaffDTO.getAdminStaffAddress().isEmpty()) {
            adminAndStaff.setAdminStaffAddress(adminStaffDTO.getAdminStaffAddress());
        }

        if (adminStaffDTO.getAdminStaffPincode() != null && !adminStaffDTO.getAdminStaffPincode().isEmpty()) {
            adminAndStaff.setAdminStaffPincode(adminStaffDTO.getAdminStaffPincode());
        }

        if (adminStaffDTO.getAdminStaffState() != null && !adminStaffDTO.getAdminStaffState().isEmpty()) {
            adminAndStaff.setAdminStaffState(adminStaffDTO.getAdminStaffState());
        }

        if (adminStaffDTO.getAdminStaffNationality() != null && !adminStaffDTO.getAdminStaffNationality().isEmpty()) {
            adminAndStaff.setAdminStaffNationality(adminStaffDTO.getAdminStaffNationality());
        }

        if (adminStaffDTO.getAdminStaffPhoneNumber() != null && !adminStaffDTO.getAdminStaffPhoneNumber().isEmpty()) {
            adminAndStaff.setAdminStaffPhoneNumber(adminStaffDTO.getAdminStaffPhoneNumber());
        }

        if (adminStaffDTO.getAdminStaffEmail() != null && !adminStaffDTO.getAdminStaffEmail().isEmpty()) {
            adminAndStaff.setAdminStaffEmail(adminStaffDTO.getAdminStaffEmail());
        }

        if (adminStaffDTO.getCreatedDate() != null) {
            adminAndStaff.setCreatedDate(adminStaffDTO.getCreatedDate());
        }

        if (adminStaffDTO.getUpdatedDate() != null) {
            adminAndStaff.setUpdatedDate(adminStaffDTO.getUpdatedDate());
        }

        // Fetch the Role by roleType from the RoleRepository and set it
        if (adminStaffDTO.getRoleType() != null) {
            Roles role = roleRepository.getByRoleType(adminStaffDTO.getRoleType());
            if (role != null) {
                adminAndStaff.setRole(role);
            }
        }
        return adminAndStaff;
    }



}
