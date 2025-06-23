package com.hikru.assessment.services;

import com.hikru.assessment.exceptions.DepartmentNotFoundException;
import com.hikru.assessment.exceptions.PositionNotFoundException;
import com.hikru.assessment.exceptions.RecruiterNotFoundException;
import com.hikru.assessment.models.Department;
import com.hikru.assessment.models.Position;
import com.hikru.assessment.models.Recruiter;
import com.hikru.assessment.models.dto.DetailsDTO;
import com.hikru.assessment.models.dto.PositionDTO;
import com.hikru.assessment.models.dto.UpdatePositionDTO;
import com.hikru.assessment.repositories.DepartmentRepository;
import com.hikru.assessment.repositories.PositionRepository;
import com.hikru.assessment.repositories.RecruiterRepository;
import com.hikru.assessment.tools.DepartmentConverter;
import com.hikru.assessment.tools.PositionConverter;
import com.hikru.assessment.tools.RecruiterConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PositionServiceImpl implements PositionService {

    private final PositionRepository positionRepository;
    private final DepartmentRepository departmentRepository;
    private final RecruiterRepository recruiterRepository;
    private final PositionConverter positionConverter;
    private final DepartmentConverter departmentConverter;
    private final RecruiterConverter recruiterConverter;

    @Autowired
    public PositionServiceImpl(PositionRepository positionRepository, DepartmentRepository departmentRepository,
                               RecruiterRepository recruiterRepository, PositionConverter positionConverter,
                               DepartmentConverter departmentConverter, RecruiterConverter recruiterConverter) {
        this.positionRepository = positionRepository;
        this.departmentRepository = departmentRepository;
        this.recruiterRepository = recruiterRepository;
        this.positionConverter = positionConverter;
        this.departmentConverter = departmentConverter;
        this.recruiterConverter = recruiterConverter;
    }

    @Override
    public List<PositionDTO> findAllPositions() {
        return positionConverter.convertPositionToDTOs(positionRepository.findAll());
    }

    @Override
    public DetailsDTO getDetails(Integer id) throws PositionNotFoundException {
        Optional<Position> positionOpt = positionRepository.findById(id);
        if(positionOpt.isPresent()) {
            Position position = positionOpt.get();
            return DetailsDTO.builder()
                    .recruiter(recruiterConverter.convertToDTO(position.getRecruiter()))
                    .department(departmentConverter.convertToDTO(position.getDepartment()))
                    .build();
        }
        throw new PositionNotFoundException("Position not found", id);
    }

    @Override
    @Transactional
    public void savePosition(PositionDTO positionDTO) throws DepartmentNotFoundException, RecruiterNotFoundException {

        Optional<Department> departmentOpt = departmentRepository.findById(positionDTO.departmentId());
        if(departmentOpt.isEmpty()) {
            throw new DepartmentNotFoundException("Department not found", positionDTO.departmentId());
        }

        Optional<Recruiter> recruiterOpt = recruiterRepository.findById(positionDTO.recruiterId());
        if(recruiterOpt.isEmpty()) {
            throw new RecruiterNotFoundException("Recruiter not found", positionDTO.recruiterId());
        }

        positionRepository.save(positionConverter.convertDTOToPosition(positionDTO, departmentOpt.get(), recruiterOpt.get()));
    }

    @Override
    public void updatePosition(Integer id, UpdatePositionDTO updatePositionDTO) throws PositionNotFoundException,
            DepartmentNotFoundException, RecruiterNotFoundException {

        Optional<Position> positionOpt = positionRepository.findById(id);
        if(positionOpt.isPresent()) {
            Position position = positionOpt.get();
            if(updatePositionDTO.title() != null && !updatePositionDTO.title().isEmpty()) {
                position.setTitle(updatePositionDTO.title());
            }
            if(updatePositionDTO.description() != null && !updatePositionDTO.description().isEmpty()) {
                position.setDescription(updatePositionDTO.description());
            }
            if(updatePositionDTO.location() != null && !updatePositionDTO.location().isEmpty()) {
                position.setLocation(updatePositionDTO.location());
            }
            if(updatePositionDTO.status() != null && !updatePositionDTO.status().isEmpty()) {
                position.setStatus(updatePositionDTO.status());
            }
            if(updatePositionDTO.budget() != null) {
                position.setBudget(updatePositionDTO.budget());
            }
            if(updatePositionDTO.closingDate() != null) {
                position.setClosingDate(updatePositionDTO.closingDate());
            }
            if(updatePositionDTO.departmentId() != null) {
                Optional<Department> departmentOpt = departmentRepository.findById(updatePositionDTO.departmentId());
                if(departmentOpt.isEmpty()) {
                    throw new DepartmentNotFoundException("Department not found", updatePositionDTO.departmentId());
                }
                position.setDepartment(departmentOpt.get());
            }
            if(updatePositionDTO.recruiterId() != null) {
                Optional<Recruiter> recruiterOpt = recruiterRepository.findById(updatePositionDTO.recruiterId());
                if(recruiterOpt.isEmpty()) {
                    throw new RecruiterNotFoundException("Recruiter not found", updatePositionDTO.recruiterId());
                }
                position.setRecruiter(recruiterOpt.get());
            }
            positionRepository.save(position);
            return;
        }
        throw new PositionNotFoundException("Position not found", id);

    }

    @Override
    public void deletePosition(Integer id) {
        positionRepository.deleteById(id);
    }


}
