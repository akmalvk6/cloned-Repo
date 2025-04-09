package com.example.BHRC.controller;
import com.example.BHRC.dto.DoctorWeeklyTimingDTO;
import com.example.BHRC.exception.ResourceNotFoundException;
import com.example.BHRC.model.Doctor;
import com.example.BHRC.service.DoctorService;
import com.example.BHRC.service.DoctorWeeklyTimingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidParameterException;
import java.util.List;


@RestController
@RequestMapping("v1/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private DoctorWeeklyTimingService weeklyTimingService;

    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
        return ResponseEntity.ok(doctorService.getDoctorById(id));
    }

    @PostMapping
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {
        return ResponseEntity.ok(doctorService.addDoctor(doctor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Long id, @RequestBody Doctor doctorDetails) {
        return ResponseEntity.ok(doctorService.updateDoctor(id, doctorDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.ok("Doctor deleted successfully");
    }

    // Weekly Timing APIs

    /**
            * Get all weekly timings for a specific doctor
     *
             * @param doctorId The ID of the doctor whose timings to retrieve
     * @return List of all weekly timings for the specified doctor
     */
    @GetMapping("/{doctorId}/weekly-timings")
    public ResponseEntity<List<DoctorWeeklyTimingDTO>> getDoctorWeeklyTimings(@PathVariable Long doctorId) {
        List<DoctorWeeklyTimingDTO> timings = weeklyTimingService.getWeeklyTimingsByDoctorId(doctorId);
        return ResponseEntity.ok(timings);
    }

    /**
     * Get a specific weekly timing by its ID
     *
     * @param id The ID of the weekly timing to retrieve
     * @return The weekly timing with the specified ID
     * @throws ResourceNotFoundException if timing with the given ID doesn't exist
     */
    @GetMapping("/weekly-timings/{id}")
    public ResponseEntity<DoctorWeeklyTimingDTO> getWeeklyTimingById(@PathVariable Long id) {
        DoctorWeeklyTimingDTO timing = weeklyTimingService.getWeeklyTimingById(id);
        return ResponseEntity.ok(timing);
    }

    /**
     * Create a new weekly timing for a doctor
     *
     * @param timingDTO The weekly timing data to create
     * @return The created weekly timing with its generated ID
     * @throws ResourceNotFoundException if the doctor ID specified in the timing doesn't exist
     */
    @PostMapping("/weekly-timings")
    public ResponseEntity<DoctorWeeklyTimingDTO> createWeeklyTiming(@Valid @RequestBody DoctorWeeklyTimingDTO timingDTO) {
        DoctorWeeklyTimingDTO createdTiming = weeklyTimingService.createWeeklyTiming(timingDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTiming);
    }

    /**
     * Update an existing weekly timing
     *
     * @param id The ID of the weekly timing to update
     * @param timingDTO The updated timing data
     * @return The updated weekly timing
     * @throws ResourceNotFoundException if timing with the given ID doesn't exist
     */
    @PutMapping("/weekly-timings/{id}")
    public ResponseEntity<DoctorWeeklyTimingDTO> updateWeeklyTiming(
            @PathVariable Long id,
            @Valid @RequestBody DoctorWeeklyTimingDTO timingDTO) {
        DoctorWeeklyTimingDTO updatedTiming = weeklyTimingService.updateWeeklyTiming(id, timingDTO);
        return ResponseEntity.ok(updatedTiming);
    }

    /**
     * Delete a weekly timing
     *
     * @param id The ID of the weekly timing to delete
     * @return No content response on successful deletion
     * @throws ResourceNotFoundException if timing with the given ID doesn't exist
     */
    @DeleteMapping("/weekly-timings/{id}")
    public ResponseEntity<Void> deleteWeeklyTiming(@PathVariable Long id) {
        weeklyTimingService.deleteWeeklyTiming(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Get weekly timings with optional filters for week number and doctor
     *
     * @param weekNumber Optional filter for specific week number
     * @param doctorId Optional filter for a specific doctor
     * @return List of weekly timings matching the specified filters
     */
    @GetMapping("/weekly-timings/filter")
    public ResponseEntity<List<DoctorWeeklyTimingDTO>> getFilteredWeeklyTimings(
            @RequestParam(required = false) Integer weekNumber,
            @RequestParam(required = false) Long doctorId) {

        // Validate week number if provided
        if (weekNumber != null && (weekNumber < 1 || weekNumber > 7)) {
            throw new InvalidParameterException("Week number must be between 1 and 7");
        }

        List<DoctorWeeklyTimingDTO> filteredTimings = weeklyTimingService.getFilteredWeeklyTimings(weekNumber, doctorId);
        return ResponseEntity.ok(filteredTimings);
    }
}

