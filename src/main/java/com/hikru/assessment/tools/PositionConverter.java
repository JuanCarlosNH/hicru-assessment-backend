package com.hikru.assessment.tools;

import com.hikru.assessment.models.Department;
import com.hikru.assessment.models.Position;
import com.hikru.assessment.models.Recruiter;
import com.hikru.assessment.models.dto.PositionDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PositionConverter {

    public List<PositionDTO> convertPositionToDTOs(List<Position> positions) {
        return positions.stream()
                .map(this::convertPositionToDTO)
                .collect(Collectors.toList());
    }

    public PositionDTO convertPositionToDTO(Position position) {
        return PositionDTO.builder()
                .id(position.getId())
                .title(position.getTitle())
                .description(position.getDescription())
                .location(position.getLocation())
                .status(position.getStatus())
                .budget(position.getBudget())
                .closingDate(position.getClosingDate())
                .departmentId(position.getDepartment().getId())
                .recruiterId(position.getRecruiter().getId())
                .build();
    }

    public Position convertDTOToPosition(PositionDTO positionDTO) {
        return Position.builder()
                .title(positionDTO.title())
                .description(positionDTO.description())
                .location(positionDTO.location())
                .status(positionDTO.status())
                .budget(positionDTO.budget())
                .closingDate(positionDTO.closingDate())
                .build();
    }

    public Position convertDTOToPosition(PositionDTO positionDTO, Department department, Recruiter recruiter) {
        return Position.builder()
                .title(positionDTO.title())
                .description(positionDTO.description())
                .location(positionDTO.location())
                .status(positionDTO.status())
                .budget(positionDTO.budget())
                .closingDate(positionDTO.closingDate())
                .department(department)
                .recruiter(recruiter)
                .build();
    }

}
