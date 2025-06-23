package com.hikru.assessment.models.dto;

import lombok.Builder;

@Builder
public record RecruiterDTO(Integer id, String name, String lastname) {
}
