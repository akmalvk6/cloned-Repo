package com.example.BHRC.service;

import com.example.BHRC.controller.PatientController;
import com.example.BHRC.dto.GenericResponse;
import com.example.BHRC.dto.MedicalHistoryDTO;
import com.example.BHRC.dto.PatientDTO;
import com.example.BHRC.dto.SearchDTO;
import com.example.BHRC.model.MedicalHistory;
import com.example.BHRC.model.Patient;
import com.example.BHRC.repository.MedicalHistoryRepository;
import com.example.BHRC.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PatientService {

    private static final Logger patientServiceLog = LoggerFactory.getLogger(PatientService.class);

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    MedicalHistoryRepository medicalHistoryRepository;

    @Autowired
    CommonService commonService;

    public GenericResponse getPatientDetails(Long patientId) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            Optional<Patient> patientOptional = patientRepository.findById(patientId);
            if (patientOptional.isPresent()) {
                Patient patient = patientOptional.get();
                PatientDTO patientDTO =new PatientDTO(patient);
                genericResponse.setStatus(true);
                genericResponse.setData(patientDTO);

            } else {
                genericResponse.setStatus(false);
                genericResponse.setError("Patient not found with ID: " + patientId);
            }

        } catch (Exception e) {
            genericResponse.setStatus(false);
            genericResponse.setError("Failed to fetch");
            patientServiceLog.error(e.toString(), "getPatientDetails{}");
        }
        return genericResponse;
    }

    public GenericResponse listPatients(SearchDTO searchDTO) {
        GenericResponse genericResponse = new GenericResponse();
        Map<String,Object> responseData = new HashMap<>();
        try {
            int pageNumber = (searchDTO.getStart() != null) ? searchDTO.getStart() : 0;
            int pageSize = (searchDTO.getPageSize() != null) ? searchDTO.getPageSize() : 10;

            Sort sort = commonService.determineSort(searchDTO, "patientFirstName");
            Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
            Page<Patient> patientList = patientRepository.searchPatientsWithFilters(searchDTO.getKeyword(), searchDTO.getAge(), searchDTO.getBloodGroup(), searchDTO.getState(), pageable);
            long count = patientRepository.countPatientsWithFilters(searchDTO.getKeyword(), searchDTO.getAge(), searchDTO.getBloodGroup(), searchDTO.getState());
            if (patientList != null && !patientList.isEmpty()) {
                List<PatientDTO> patientDTOList = patientList.stream()
                        .map(this::convertToPatientDTO)
                        .collect(Collectors.toList());
                responseData.put("data",patientDTOList);
                responseData.put("count",count);
                genericResponse.setData(responseData);
            } else {
                genericResponse.setMessage("No Data Found");
            }
            genericResponse.setStatus(true);
        } catch (Exception e) {
            genericResponse.setStatus(false);
            genericResponse.setError("Failed to fetch");
            patientServiceLog.error(e.toString(), "listPatients{}");
        }
        return genericResponse;
    }

    public GenericResponse createOrUpdatePatient(PatientDTO patientDTO) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            Patient patient = convertToPatient(patientDTO);
            patientRepository.save(patient);
            if (patientDTO.getPatientId() != null) {
                genericResponse.setMessage("Patient updated successfully");

            } else {
                genericResponse.setMessage("Patient created successfully");
            }
            genericResponse.setStatus(true);
        } catch (Exception e) {
            patientServiceLog.error(e.toString(), "createNewPatient");
            genericResponse.setError("Failed to create");
            genericResponse.setStatus(false);
        }
        return genericResponse;
    }

    public GenericResponse deletePatient(Long patientId) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            patientRepository.deleteById(patientId);
            genericResponse.setMessage("Patient deleted successfully");

        } catch (Exception e) {
            genericResponse.setStatus(false);
            genericResponse.setError("Failed to delete");
            patientServiceLog.error(e.toString(), "getPatientDetails{}");
        }
        return genericResponse;
    }

    private Patient convertToPatient(PatientDTO patientDTO) {
        Patient patient = new Patient();

        if (patientDTO.getPatientId() != null) {
            patient.setPatientId(patientDTO.getPatientId());
        }

        if (patientDTO.getPatientFirstName() != null && !patientDTO.getPatientFirstName().isEmpty()) {
            patient.setPatientFirstName(patientDTO.getPatientFirstName());
        }
        if (patientDTO.getPatientLastName() != null && !patientDTO.getPatientLastName().isEmpty()) {
            patient.setPatientLastName(patientDTO.getPatientLastName());
        }
        if (patientDTO.getPatientAge() != null && patientDTO.getPatientAge() > 0) {
            patient.setPatientAge(patientDTO.getPatientAge());
        }
        if (patientDTO.getPatientBloodGroup() != null && !patientDTO.getPatientBloodGroup().isEmpty()) {
            patient.setPatientBloodGroup(patientDTO.getPatientBloodGroup());
        }
        if (patientDTO.getPatientAddress() != null && !patientDTO.getPatientAddress().isEmpty()) {
            patient.setPatientAddress(patientDTO.getPatientAddress());
        }
        if (patientDTO.getPincode() != null && !patientDTO.getPincode().isEmpty()) {
            patient.setPincode(patientDTO.getPincode());
        }
        if (patientDTO.getState() != null && !patientDTO.getState().isEmpty()) {
            patient.setState(patientDTO.getState());
        }
        if (patientDTO.getNationality() != null && !patientDTO.getNationality().isEmpty()) {
            patient.setNationality(patientDTO.getNationality());
        }
        if (patientDTO.getPhoneNumber() != null && !patientDTO.getPhoneNumber().isEmpty()) {
            patient.setPhoneNumber(patientDTO.getPhoneNumber());
        }
        if (patientDTO.getPatientEmail() != null && !patientDTO.getPatientEmail().isEmpty()) {
            patient.setPatientEmail(patientDTO.getPatientEmail());
        }
        return patient;
    }

    private PatientDTO convertToPatientDTO(Patient patient) {
        PatientDTO patientDTO = new PatientDTO();

        if (patient.getPatientId() != null) {
            patientDTO.setPatientId(patient.getPatientId());
        }
        if (patient.getPatientFirstName() != null && !patient.getPatientFirstName().isEmpty()) {
            patientDTO.setPatientFirstName(patient.getPatientFirstName());
        }
        if (patient.getPatientLastName() != null && !patient.getPatientLastName().isEmpty()) {
            patientDTO.setPatientLastName(patient.getPatientLastName());
        }
        if (patient.getPatientAge() != null && patient.getPatientAge() > 0) {
            patientDTO.setPatientAge(patient.getPatientAge());
        }
        if (patient.getPatientBloodGroup() != null && !patient.getPatientBloodGroup().isEmpty()) {
            patientDTO.setPatientBloodGroup(patient.getPatientBloodGroup());
        }
        if (patient.getPatientAddress() != null && !patient.getPatientAddress().isEmpty()) {
            patientDTO.setPatientAddress(patient.getPatientAddress());
        }
        if (patient.getPincode() != null && !patient.getPincode().isEmpty()) {
            patientDTO.setPincode(patient.getPincode());
        }
        if (patient.getState() != null && !patient.getState().isEmpty()) {
            patientDTO.setState(patient.getState());
        }
        if (patient.getNationality() != null && !patient.getNationality().isEmpty()) {
            patientDTO.setNationality(patient.getNationality());
        }
        if (patient.getPhoneNumber() != null && !patient.getPhoneNumber().isEmpty()) {
            patientDTO.setPhoneNumber(patient.getPhoneNumber());
        }
        if (patient.getPatientEmail() != null && !patient.getPatientEmail().isEmpty()) {
            patientDTO.setPatientEmail(patient.getPatientEmail());
        }

        return patientDTO;
    }



    public GenericResponse listMedicalHistoriesOfPatient(SearchDTO searchDTO) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            int pageNumber = (searchDTO.getStart() != null) ? searchDTO.getStart() : 0;
            int pageSize = (searchDTO.getPageSize() != null) ? searchDTO.getPageSize() : 10;

            Sort sort = commonService.determineSort(searchDTO, "createdDate");
            Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
            Page<MedicalHistory> medicalHistoriesList = medicalHistoryRepository.searchMedicalHistory(searchDTO.getPatientId(), pageable);
            if (medicalHistoriesList != null && !medicalHistoriesList.isEmpty()) {
                List<MedicalHistoryDTO> medicalHistoryDTOList = medicalHistoriesList.stream()
                        .map(this::convertToMedicalHistoryDTO)
                        .collect(Collectors.toList());
                genericResponse.setData(medicalHistoryDTOList);
            } else {
                genericResponse.setMessage("No Data Found");
            }
            genericResponse.setStatus(true);
        } catch (Exception e) {
            genericResponse.setStatus(false);
            genericResponse.setError("Failed to fetch");
            patientServiceLog.error(e.toString(), "listMedicalHistoriesOfPatient{}");
        }
        return genericResponse;
    }

    private MedicalHistoryDTO convertToMedicalHistoryDTO(MedicalHistory medicalHistory){
        MedicalHistoryDTO medicalHistoryDTO  = new MedicalHistoryDTO();

        if(medicalHistory.getMedicalHistoryId() != null){
            medicalHistoryDTO.setMedicalHistoryId(medicalHistory.getMedicalHistoryId());
        }
        if(medicalHistory.getAdditionalNotes() != null && !medicalHistory.getAdditionalNotes().isEmpty()){
            medicalHistoryDTO.setAdditionalNotes(medicalHistory.getAdditionalNotes());
        }
        if(medicalHistory.getConsulationType() != null){
            medicalHistoryDTO.setConsultationType(medicalHistory.getConsulationType());
        }
        if(medicalHistory.getPrescription() != null && !medicalHistory.getPrescription().isEmpty()){
            medicalHistoryDTO.setPrescription(medicalHistory.getPrescription());
        }
        if(medicalHistory.getCreatedDate() != null){
            medicalHistoryDTO.setCreatedDate(medicalHistory.getCreatedDate());
        }
        if(medicalHistory.getUpdatedDate() != null ){
            medicalHistoryDTO.setUpdatedDate(medicalHistory.getUpdatedDate());
        }
        if(medicalHistory.getPatient() != null) {
            medicalHistoryDTO.setPatientId(medicalHistory.getPatient().getPatientId());
        }
        return  medicalHistoryDTO;
    }

    public GenericResponse getOneMedicalHistory(Long medicalHistoryId){
        GenericResponse genericResponse = new GenericResponse();
        try {
            Optional<MedicalHistory> medicalHistoryOptional = medicalHistoryRepository.findById(medicalHistoryId);
            if (medicalHistoryOptional.isPresent()) {
                MedicalHistory medicalHistory = medicalHistoryOptional.get();
                MedicalHistoryDTO medicalHistoryDTO = new MedicalHistoryDTO(medicalHistory);
                PatientDTO patientDTO = new PatientDTO(medicalHistory.getPatient());
                medicalHistoryDTO.setPatient(patientDTO);
                genericResponse.setStatus(true);
                genericResponse.setData(medicalHistoryDTO);

            } else {
                genericResponse.setStatus(false);
                genericResponse.setError("Medical History not found with ID: " + medicalHistoryId);
            }
        } catch (Exception e) {
            genericResponse.setStatus(false);
            genericResponse.setError("Failed to fetch");
            patientServiceLog.error(e.toString(), "getOneMedicalHistory{}");
        }
        return genericResponse;

    }

    public GenericResponse createOrUpdateMedicalHistory(MedicalHistoryDTO medicalHistoryDTO) {
        GenericResponse genericResponse = new GenericResponse();
        try{
            if(medicalHistoryDTO !=null){
                MedicalHistory medicalHistory = convertToMedicalHistory(medicalHistoryDTO);
                Patient patient = patientRepository.getPatientById(medicalHistoryDTO.getPatientId());
                if(patient == null){
                    genericResponse.setError("No patient data available");
                    genericResponse.setStatus(false);
                    return  genericResponse;
                }
                medicalHistory.setPatient(patient);
                medicalHistoryRepository.save(medicalHistory);
                if (medicalHistoryDTO.getMedicalHistoryId() != null) {
                    genericResponse.setMessage("Medical History updated successfully");

                } else {
                    genericResponse.setMessage("Medical History added successfully");
                }
            }else{
                genericResponse.setError("Empty data recieved");
                genericResponse.setStatus(false);
            }

        }catch (Exception e){
            patientServiceLog.error(e.toString(),"createOrUpdateMedicalHistory{}");
        }

        return  genericResponse;

    }

    private MedicalHistory convertToMedicalHistory(MedicalHistoryDTO medicalHistoryDTO){
        MedicalHistory medicalHistory = new MedicalHistory();;
        if(medicalHistoryDTO.getMedicalHistoryId() != null){
            medicalHistory.setMedicalHistoryId(medicalHistoryDTO.getMedicalHistoryId());
        }
        if(medicalHistoryDTO.getAdditionalNotes() != null && !medicalHistoryDTO.getAdditionalNotes().isEmpty()){
            medicalHistory.setAdditionalNotes(medicalHistoryDTO.getAdditionalNotes());
        }
        if(medicalHistoryDTO.getConsultationType() != null){
            medicalHistory.setConsulationType(medicalHistoryDTO.getConsultationType());
        }
        if(medicalHistoryDTO.getPrescription() != null && !medicalHistoryDTO.getPrescription().isEmpty()){
            medicalHistory.setPrescription(medicalHistoryDTO.getPrescription());
        }
        return  medicalHistory;
    }

}
