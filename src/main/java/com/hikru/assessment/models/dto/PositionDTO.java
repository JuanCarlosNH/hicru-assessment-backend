package com.hikru.assessment.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.Date;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record PositionDTO(@NotNull(message = "title can not be null")
                          @Size(max = 100, message = "title must be less than 100 characters long.") String title,
                          @NotNull(message = "description can not be null")
                          @Size(max = 1000,
                                  message = "description must be less than 1000 characters long.") String description,
                          @NotNull(message = "location can not be null") String location,
                          @NotNull(message = "status can not be null")
                          @Pattern(regexp = "^(draft|open|closed|archived)$",
                                  message = "status must be 'open', 'draft', 'closed' or 'archived'") String status,
                          @NotNull(message = "recruiterId can not be null")
                          @Min(value = 1, message = "recruiterId cannot be 0 or negative") Integer recruiterId,
                          @NotNull(message = "departmentId can not be null")
                          @Min(value = 1, message = "departmentId cannot be 0 or negative") Integer departmentId,
                          @NotNull(message = "budget can not be null") Double budget,
                          Date closingDate,
                          Integer id) {
}
