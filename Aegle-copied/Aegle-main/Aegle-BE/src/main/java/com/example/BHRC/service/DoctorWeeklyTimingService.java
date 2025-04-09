package com.example.BHRC.service;

import com.example.BHRC.dto.DoctorWeeklyTimingDTO;
import com.example.BHRC.exception.ResourceNotFoundException;
import com.example.BHRC.model.Doctor;
import com.example.BHRC.model.DoctorWeeklyTiming;
import com.example.BHRC.repository.DoctorRepository;
import com.example.BHRC.repository.DoctorWeeklyTimingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorWeeklyTimingService {

    @Autowired
    private DoctorWeeklyTimingRepository weeklyTimingRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    public List<DoctorWeeklyTimingDTO> getWeeklyTimingsByDoctorId(Long doctorId) {
        // Updated to use the correct repository method
        List<DoctorWeeklyTiming> timings = weeklyTimingRepository.findByDoctorDoctorId(doctorId);
        return timings.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public DoctorWeeklyTimingDTO getWeeklyTimingById(Long id) {
        DoctorWeeklyTiming timing = weeklyTimingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Weekly timing not found with id: " + id));
        return convertToDTO(timing);
    }

    @Transactional
    public DoctorWeeklyTimingDTO createWeeklyTiming(DoctorWeeklyTimingDTO timingDTO) {
        Doctor doctor = doctorRepository.findById(timingDTO.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + timingDTO.getDoctorId()));

        DoctorWeeklyTiming timing = new DoctorWeeklyTiming();
        timing.setWeekNo(timingDTO.getWeekNo());
        timing.setStartTime(timingDTO.getStartTime());
        timing.setEndTime(timingDTO.getEndTime());
        timing.setDoctor(doctor);

        DoctorWeeklyTiming savedTiming = weeklyTimingRepository.save(timing);
        return convertToDTO(savedTiming);
    }

    @Transactional
    public DoctorWeeklyTimingDTO updateWeeklyTiming(Long id, DoctorWeeklyTimingDTO timingDTO) {
        DoctorWeeklyTiming timing = weeklyTimingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Weekly timing not found with id: " + id));

        timing.setWeekNo(timingDTO.getWeekNo());
        timing.setStartTime(timingDTO.getStartTime());
        timing.setEndTime(timingDTO.getEndTime());
        timing.setUpdatedDate(LocalDateTime.now());

        DoctorWeeklyTiming updatedTiming = weeklyTimingRepository.save(timing);
        return convertToDTO(updatedTiming);
    }

    @Transactional
    public void deleteWeeklyTiming(Long id) {
        if (!weeklyTimingRepository.existsById(id)) {
            throw new ResourceNotFoundException("Weekly timing not found with id: " + id);
        }
        weeklyTimingRepository.deleteById(id);
    }

    private DoctorWeeklyTimingDTO convertToDTO(DoctorWeeklyTiming timing) {
        DoctorWeeklyTimingDTO dto = new DoctorWeeklyTimingDTO();
        dto.setDoctorWeeklyTimingId(timing.getDoctorWeeklyTimingId());
        dto.setWeekNo(timing.getWeekNo());
        dto.setStartTime(timing.getStartTime());
        dto.setEndTime(timing.getEndTime());
        // Make sure to use the correct ID field from your Doctor entity
        dto.setDoctorId(timing.getDoctor().getDoctorId());
        return dto;
    }

    /**
     * Get weekly timings filtered by week number and/or doctor ID
     *
     * @param weekNumber Optional filter for specific week number
     * @param doctorId Optional filter for a specific doctor
     * @return List of weekly timings matching the specified filters
     */
    public List<DoctorWeeklyTimingDTO> getFilteredWeeklyTimings(Integer weekNumber, Long doctorId) {
        // Apply filters based on which parameters are provided
        List<DoctorWeeklyTiming> timings;

        if (weekNumber != null && doctorId != null) {
            // Filter by both week number and doctor ID
            timings = weeklyTimingRepository.findByDoctorDoctorIdAndWeekNo(doctorId,weekNumber);
        } else if (weekNumber != null) {
            // Filter by week number only
            timings = weeklyTimingRepository.findByWeekNo(weekNumber);
        } else if (doctorId != null) {
            // Filter by doctor ID only
            timings = weeklyTimingRepository.findByDoctorDoctorId(doctorId);
        } else {
            // No filters - fetch all weekly timings
            timings = weeklyTimingRepository.findAll();
        }

        // Convert entities to DTOs and return
        return timings.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}