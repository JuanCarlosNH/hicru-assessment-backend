package com.hikru.assessment.tools;

import com.hikru.assessment.models.Recruiter;
import com.hikru.assessment.models.dto.RecruiterDTO;
import org.springframework.stereotype.Component;

@Component
public class RecruiterConverter {

    public RecruiterDTO convertToDTO(Recruiter recruiter) {
        return RecruiterDTO.builder()
                .id(recruiter.getId())
                .name(recruiter.getName())
                .lastname(recruiter.getLastname())
                .build();
    }

}
