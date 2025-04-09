package com.example.BHRC.controller;
import com.example.BHRC.dto.AppointmentsDTO;
import com.example.BHRC.service.AppointmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/appointments")
public class AppointmentsController {

    @Autowired
    private AppointmentsService appointmentsService;

    @PostMapping("/book")
    public AppointmentsDTO bookAppointment(@RequestBody AppointmentsDTO dto) {
        return appointmentsService.bookAppointment(dto);
    }

    @GetMapping("/all")
    public List<AppointmentsDTO> getAllAppointments() {
        return appointmentsService.getAllAppointments();
    }

    @PutMapping("/edit/{appointmentId}")
    public AppointmentsDTO editAppointments(@PathVariable Long appointmentId, @RequestBody AppointmentsDTO dto) {
        return appointmentsService.editAppointment(appointmentId, dto);
    }

    @DeleteMapping("/delete/{appointmentId}")
    public String deleteAppointments(@PathVariable Long appointmentId) {
        return appointmentsService.deleteAppointment(appointmentId);
    }
}