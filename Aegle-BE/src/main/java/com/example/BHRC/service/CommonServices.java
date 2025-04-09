package com.example.BHRC.service;

import com.example.BHRC.dto.GenericResponse;
import com.example.BHRC.model.Specialization;
import com.example.BHRC.repository.SpecializationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommonServices {
    @Autowired
    private SpecializationRepository specializationRepository;

    // Fetch all specializations
    public GenericResponse getAllSpecializations() {
        try {
            List<Specialization> specializations = specializationRepository.findAll();
            return new GenericResponse(true, "Specializations retrieved successfully", specializations, null);
        } catch (Exception e) {
            return new GenericResponse(false, "Failed to retrieve specializations", null, e.getMessage());
        }
    }

    // Add a new specialization
    public GenericResponse addSpecialization(Specialization specialization) {
        try {
            Specialization createdSpecialization = specializationRepository.save(specialization);
            return new GenericResponse(true, "Specialization created successfully", createdSpecialization, null);
        } catch (Exception e) {
            return new GenericResponse(false, "Failed to create specialization", null, e.getMessage());
        }
    }

    // Delete a specialization by ID
    public GenericResponse deleteSpecialization(Long id) {
        try {
            Optional<Specialization> specialization = specializationRepository.findById(id);
            if (specialization.isPresent()) {
                specializationRepository.deleteById(id);
                return new GenericResponse(true, "Specialization deleted successfully", null, null);
            } else {
                return new GenericResponse(false, "Specialization not found", null, "Specialization not found with ID: " + id);
            }
        } catch (Exception e) {
            return new GenericResponse(false, "Failed to delete specialization", null, e.getMessage());
        }
    }


}
