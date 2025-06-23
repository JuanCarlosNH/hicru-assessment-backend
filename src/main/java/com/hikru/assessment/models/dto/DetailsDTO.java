package com.hikru.assessment.models.dto;

import lombok.Builder;

@Builder
public record DetailsDTO (DepartmentDTO department, RecruiterDTO recruiter){
}
