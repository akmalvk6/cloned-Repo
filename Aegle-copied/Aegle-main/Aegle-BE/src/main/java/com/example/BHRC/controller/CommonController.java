package com.example.BHRC.controller;

import com.example.BHRC.dto.GenericResponse;
import com.example.BHRC.model.Specialization;
import com.example.BHRC.service.CommonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/commonController/")
public class CommonController {

    @Autowired
    private CommonServices commonServices;

    // List all specializations
    @GetMapping("/listSpecializations")
    public ResponseEntity<GenericResponse> listSpecializations() {
        return ResponseEntity.ok(commonServices.getAllSpecializations());
    }

    // Add a new specialization
    @PostMapping("/addSpecialization")
    public ResponseEntity<GenericResponse> addSpecialization(@RequestBody Specialization specialization) {
        return ResponseEntity.ok(commonServices.addSpecialization(specialization));
    }

    // Delete a specialization by ID
    @DeleteMapping("/deleteSpecialization/{id}")
    public ResponseEntity<GenericResponse> deleteSpecialization(@PathVariable Long id) {
        return ResponseEntity.ok(commonServices.deleteSpecialization(id));
    }

}
