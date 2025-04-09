package com.example.BHRC.service;

import com.example.BHRC.model.TestServices;
import com.example.BHRC.repository.TestServicesRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServicesService {

    private final TestServicesRepository testServicesRepository;

    public TestServicesService(TestServicesRepository testServicesRepository) {
        this.testServicesRepository = testServicesRepository;
    }

    // Retrieve all test services
    public List<TestServices> getAllTestServices() {
        return testServicesRepository.findAll();
    }

    // Save a new test service
    public TestServices saveTestService(TestServices testService) {
        return testServicesRepository.save(testService);
    }

    // Retrieve a test service by ID
    public TestServices getTestServiceById(Long id) {
        return testServicesRepository.findById(id).orElse(null);
    }

    // Delete a test service by ID
    public void deleteTestService(Long id) {
        testServicesRepository.deleteById(id);
    }
}
