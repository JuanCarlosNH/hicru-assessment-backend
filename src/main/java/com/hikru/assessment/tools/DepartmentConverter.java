package com.hikru.assessment.tools;

import com.hikru.assessment.models.Department;
import com.hikru.assessment.models.dto.DepartmentDTO;
import org.springframework.stereotype.Component;

@Component
public class DepartmentConverter {

    public DepartmentDTO convertToDTO(Department department) {
        return DepartmentDTO.builder()
                .id(department.getId())
                .name(department.getName())
                .build();
    }

}
