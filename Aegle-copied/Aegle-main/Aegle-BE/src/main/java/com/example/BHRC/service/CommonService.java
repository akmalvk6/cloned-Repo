package com.example.BHRC.service;

import com.example.BHRC.dto.SearchDTO;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CommonService {

    public Sort determineSort(SearchDTO searchDTO, String defaultField) {
        // Default sorting (by patientFirstName ascending)
        if (searchDTO.getSortBy() == null || searchDTO.getSortBy().isEmpty()) {
            searchDTO.setSortBy(defaultField);
        }
        // Default direction is ascending
        Sort.Direction sortDirection = Sort.Direction.ASC;
        if (searchDTO.getSortDirection() != null && (searchDTO.getSortDirection().equalsIgnoreCase("desc"))) {
            sortDirection = Sort.Direction.DESC;
        }
        return Sort.by(sortDirection, searchDTO.getSortBy());
    }



}
