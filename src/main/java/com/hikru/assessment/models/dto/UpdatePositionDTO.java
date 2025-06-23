package com.hikru.assessment.models.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.Date;

@Builder
public record UpdatePositionDTO (@Size(max = 100, message = "title must be less than 100 characters long.") String title,
                                 @Size(max = 1000,
                                         message = "description must be less than 1000 characters long.") String description,
                                 String location,
                                 @Pattern(regexp = "^(draft|open|closed|archived)$",
                                         message = "status must be 'open', 'draft', 'closed' or 'archived'") String status,
                                 @Min(value = 1, message = "recruiterId cannot be 0 or negative") Integer recruiterId,
                                 @Min(value = 1, message = "departmentId cannot be 0 or negative") Integer departmentId,
                                 Double budget,
                                 Date closingDate) {
}
