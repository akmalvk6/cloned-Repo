package com.example.BHRC.controller;

import com.example.BHRC.dto.GenericResponse;
import com.example.BHRC.dto.MedicalHistoryDTO;
import com.example.BHRC.dto.PatientDTO;
import com.example.BHRC.dto.SearchDTO;
import com.example.BHRC.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/patientController/")
public class PatientController {
    private static final Logger patientControllerLog = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    PatientService patientService;

    @GetMapping("patient/{patientId}")
    public GenericResponse getPatientDetails(@PathVariable Long patientId){
        return  patientService.getPatientDetails(patientId);
    }

    @PostMapping("listPatients")
    public GenericResponse listPatients(@RequestBody SearchDTO searchDTO){
        return  patientService.listPatients(searchDTO);
    }

    @PostMapping("createOrUpdatePatient")
    public GenericResponse createNewPatient(@RequestBody PatientDTO patientDTO){
        return  patientService.createOrUpdatePatient(patientDTO);
    }

    @DeleteMapping("patient/{patientId}")
    public GenericResponse deletePatient(@PathVariable Long patientId){
        return  patientService.deletePatient(patientId);
    }

    @PostMapping("listMedicalHistories")
    public GenericResponse listMedicalHistoriesOfPatient(@RequestBody SearchDTO searchDTO){
        return  patientService.listMedicalHistoriesOfPatient(searchDTO);
    }

    @GetMapping("medicalHistory/{medicalHistoryId}")
    public GenericResponse getOneMedicalHistory(@PathVariable Long medicalHistoryId){
        return  patientService.getOneMedicalHistory(medicalHistoryId);
    }

    @PostMapping("createOrUpdateMedicalHistory")
    public GenericResponse createOrUpdateMedicalHistory(@RequestBody MedicalHistoryDTO medicalHistoryDTO){
        return  patientService.createOrUpdateMedicalHistory(medicalHistoryDTO);
    }


}
