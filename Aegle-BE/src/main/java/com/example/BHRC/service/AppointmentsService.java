package com.example.BHRC.service;
import com.example.BHRC.dto.AppointmentsDTO;
import com.example.BHRC.model.Appointments;
import com.example.BHRC.model.Doctor;
import com.example.BHRC.model.Patient;
import com.example.BHRC.repository.AppointmentsRepository;
import com.example.BHRC.repository.DoctorRepository;
import com.example.BHRC.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentsService {

    @Autowired
    private AppointmentsRepository appointmentsRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    public AppointmentsDTO bookAppointment(AppointmentsDTO dto) {
        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Appointments appointments = new Appointments();
        appointments.setAppointmentType(dto.getAppointmentType());
        appointments.setAppointmentBookingDate(dto.getAppointmentBookingDate());
        appointments.setAppointmentBookingTime(dto.getAppointmentBookingTime());
        appointments.setPatient(patient);
        appointments.setDoctor(doctor);
        appointments.setCreatedDate(dto.getCreatedDate());
        appointments.setUpdatedDate(dto.getUpdatedDate());

        appointments = appointmentsRepository.save(appointments);
        dto.setAppointmentBookingId(appointments.getAppointmentBookingId());

        return dto;
    }

    public List<AppointmentsDTO> getAllAppointments() {
        return appointmentsRepository.findAll().stream().map(appointments -> {
            AppointmentsDTO dto = new AppointmentsDTO();
            dto.setAppointmentBookingId(appointments.getAppointmentBookingId());
            dto.setAppointmentType(appointments.getAppointmentType());
            dto.setAppointmentBookingDate(appointments.getAppointmentBookingDate());
            dto.setAppointmentBookingTime(appointments.getAppointmentBookingTime());
            dto.setPatientId(appointments.getPatient().getPatientId());
            dto.setDoctorId(appointments.getDoctor().getDoctorId());
            dto.setCreatedDate(appointments.getCreatedDate());
            dto.setUpdatedDate(appointments.getUpdatedDate());
            return dto;
        }).collect(Collectors.toList());
    }

    public AppointmentsDTO editAppointment(Long appointmentId, AppointmentsDTO dto) {
        Appointments appointments = appointmentsRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        appointments.setAppointmentType(dto.getAppointmentType());
        appointments.setAppointmentBookingDate(dto.getAppointmentBookingDate());
        appointments.setAppointmentBookingTime(dto.getAppointmentBookingTime());
        appointments.setUpdatedDate(dto.getUpdatedDate());

        appointmentsRepository.save(appointments);

        dto.setAppointmentBookingId(appointments.getAppointmentBookingId());
        return dto;
    }

    public String deleteAppointment(Long appointmentId) {
        Appointments appointments = appointmentsRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        appointmentsRepository.delete(appointments);
        return "Appointment with ID " + appointmentId + " has been deleted successfully.";
    }

}
