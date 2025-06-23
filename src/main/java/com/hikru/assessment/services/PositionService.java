package com.hikru.assessment.services;

import com.hikru.assessment.exceptions.DepartmentNotFoundException;
import com.hikru.assessment.exceptions.PositionNotFoundException;
import com.hikru.assessment.exceptions.RecruiterNotFoundException;
import com.hikru.assessment.models.dto.DetailsDTO;
import com.hikru.assessment.models.dto.PositionDTO;
import com.hikru.assessment.models.dto.UpdatePositionDTO;

import java.util.List;
import java.util.Optional;

public interface PositionService {

    List<PositionDTO> findAllPositions();

    DetailsDTO getDetails(Integer id) throws PositionNotFoundException;

    void savePosition(PositionDTO positionDTO) throws DepartmentNotFoundException, RecruiterNotFoundException;

    void updatePosition(Integer id, UpdatePositionDTO updatePositionDTO) throws PositionNotFoundException,
            DepartmentNotFoundException, RecruiterNotFoundException;

    void deletePosition(Integer id);

}
