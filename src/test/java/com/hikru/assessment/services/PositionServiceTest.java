package com.hikru.assessment.services;

import com.hikru.assessment.exceptions.DepartmentNotFoundException;
import com.hikru.assessment.exceptions.PositionNotFoundException;
import com.hikru.assessment.exceptions.RecruiterNotFoundException;
import com.hikru.assessment.models.Department;
import com.hikru.assessment.models.Position;
import com.hikru.assessment.models.Recruiter;
import com.hikru.assessment.models.dto.DepartmentDTO;
import com.hikru.assessment.models.dto.DetailsDTO;
import com.hikru.assessment.models.dto.PositionDTO;
import com.hikru.assessment.models.dto.RecruiterDTO;
import com.hikru.assessment.models.dto.UpdatePositionDTO;
import com.hikru.assessment.repositories.DepartmentRepository;
import com.hikru.assessment.repositories.PositionRepository;
import com.hikru.assessment.repositories.RecruiterRepository;
import com.hikru.assessment.tools.DepartmentConverter;
import com.hikru.assessment.tools.PositionConverter;
import com.hikru.assessment.tools.RecruiterConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PositionServiceTest {

    private PositionRepository positionRepository;
    private DepartmentRepository departmentRepository;
    private RecruiterRepository recruiterRepository;
    private PositionConverter positionConverter;
    private DepartmentConverter departmentConverter;
    private RecruiterConverter recruiterConverter;

    private PositionService positionService;

    @BeforeEach
    public void setup() {
        positionRepository = mock(PositionRepository.class);
        departmentRepository = mock(DepartmentRepository.class);
        recruiterRepository = mock(RecruiterRepository.class);
        positionConverter = mock(PositionConverter.class);
        departmentConverter = mock(DepartmentConverter.class);
        recruiterConverter = mock(RecruiterConverter.class);
        positionService = new PositionServiceImpl(positionRepository, departmentRepository, recruiterRepository,
                positionConverter, departmentConverter, recruiterConverter);
    }

    @Test
    public void findAllPositionsTest() {
        when(positionRepository.findAll()).thenReturn(getPositions());
        when(positionConverter.convertPositionToDTOs(any())).thenReturn(getPositionDTOs());
        List<PositionDTO> positionDTOList = positionService.findAllPositions();
        assertEquals(2, positionDTOList.size());
        assertEquals("title1", positionDTOList.getFirst().title());
    }

    @Test
    public void getDetailsTest() {
        when(recruiterConverter.convertToDTO(any())).thenReturn(getRecruiterDTO());
        when(departmentConverter.convertToDTO(any())).thenReturn(getDepartmentDTO());
        when(positionRepository.findById(anyInt())).thenReturn(Optional.of(getPosition()));
        DetailsDTO detailsDTO = positionService.getDetails(anyInt());
        assertNotNull(detailsDTO.department());
        assertEquals("department", detailsDTO.department().name());
        assertEquals("name", detailsDTO.recruiter().name());
    }

    @Test
    public void getDetailsTestPositionNotFoundException() {
        when(positionRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(PositionNotFoundException.class, () -> positionService.getDetails(anyInt()));
        verify(recruiterConverter, times(0)).convertToDTO(any());
        verify(departmentConverter, times(0)).convertToDTO(any());
    }

    @Test
    public void savePositionTest() {
        when(departmentRepository.findById(anyInt())).thenReturn(Optional.of(getDepartment()));
        when(recruiterRepository.findById(anyInt())).thenReturn(Optional.of(getRecruiter()));
        when(positionConverter.convertDTOToPosition(any(), any(), any())).thenReturn(getPosition());
        when(positionRepository.save(getPosition())).thenReturn(getPosition());

        positionService.savePosition(getPositionDTO());

        verify(recruiterRepository, times(1)).findById(anyInt());
        verify(positionConverter, times(1)).convertDTOToPosition(any(), any(), any());
    }

    @Test
    public void savePositionDepartmentNotFoundExceptionTest() {
        when(departmentRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(DepartmentNotFoundException.class, () -> positionService.savePosition(getPositionDTO()));
        verify(recruiterRepository, times(0)).findById(anyInt());
        verify(positionConverter, times(0)).convertDTOToPosition(any(), any(), any());
        verify(positionRepository, times(0)).save(any());
    }

    @Test
    public void savePositionRecruiterNotFoundExceptionTest() {
        when(departmentRepository.findById(anyInt())).thenReturn(Optional.of(getDepartment()));
        when(recruiterRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(RecruiterNotFoundException.class, () -> positionService.savePosition(getPositionDTO()));
        verify(positionConverter, times(0)).convertDTOToPosition(any(), any(), any());
        verify(positionRepository, times(0)).save(any());
    }

    @Test
    public void updatePositionTest() {
        when(positionRepository.findById(anyInt())).thenReturn(Optional.of(getPosition()));
        when(departmentRepository.findById(anyInt())).thenReturn(Optional.of(getDepartment()));
        when(recruiterRepository.findById(anyInt())).thenReturn(Optional.of(getRecruiter()));
        when(positionRepository.save(any())).thenReturn(getPosition());
        positionService.updatePosition(1, getUpdatePositionDTO());
        verify(departmentRepository, times(1)).findById(anyInt());
        verify(recruiterRepository, times(1)).findById(anyInt());
        verify(positionRepository, times(1)).save(any());
    }

    @Test
    public void updatePositionWithPositionNotFoundExceptionTest() {
        when(positionRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(PositionNotFoundException.class, () -> positionService.updatePosition(1, getUpdatePositionDTO()));
        verify(departmentRepository, times(0)).findById(anyInt());
        verify(recruiterRepository, times(0)).findById(anyInt());
        verify(positionRepository, times(0)).save(any());
    }

    @Test
    public void updatePositionWithDepartmentNotFoundExceptionTest() {
        when(positionRepository.findById(anyInt())).thenReturn(Optional.of(getPosition()));
        when(departmentRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(DepartmentNotFoundException.class, () -> positionService.updatePosition(1, getUpdatePositionDTO()));
        verify(departmentRepository, times(1)).findById(anyInt());
        verify(recruiterRepository, times(0)).findById(anyInt());
        verify(positionRepository, times(0)).save(any());
    }

    @Test
    public void updatePositionWithRecruiterNotFoundExceptionTest() {
        when(positionRepository.findById(anyInt())).thenReturn(Optional.of(getPosition()));
        when(departmentRepository.findById(anyInt())).thenReturn(Optional.of(getDepartment()));
        when(recruiterRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(RecruiterNotFoundException.class, () -> positionService.updatePosition(1, getUpdatePositionDTO()));
        verify(departmentRepository, times(1)).findById(anyInt());
        verify(recruiterRepository, times(1)).findById(anyInt());
        verify(positionRepository, times(0)).save(any());
    }

    @Test
    public void deletePositionTest() {
        doNothing().when(positionRepository).deleteById(anyInt());
        positionService.deletePosition(anyInt());
        verify(positionRepository, times(1)).deleteById(anyInt());
    }

    private Department getDepartment() {
        return Department.builder()
                .name("department")
                .build();
    }

    private Recruiter getRecruiter() {
        return Recruiter.builder()
                .name("name")
                .lastname("lastname")
                .build();
    }

    private DepartmentDTO getDepartmentDTO() {
        return DepartmentDTO.builder()
                .name("department")
                .build();
    }

    private RecruiterDTO getRecruiterDTO() {
        return RecruiterDTO.builder()
                .name("name")
                .lastname("lastname")
                .build();
    }

    private List<PositionDTO> getPositionDTOs() {
        List<PositionDTO> positionDTOList = new ArrayList<>();
        PositionDTO positionDTO2 = PositionDTO.builder()
                .title("title2")
                .description("description2")
                .build();
        positionDTOList.add(getPositionDTO());
        positionDTOList.add(positionDTO2);
        return positionDTOList;
    }

    private List<Position> getPositions() {
        List<Position> positionList = new ArrayList<>();
        Position position2 = Position.builder()
                .title("title2")
                .description("description2")
                .build();
        positionList.add(getPosition());
        positionList.add(position2);
        return positionList;
    }

    private PositionDTO getPositionDTO() {
        return PositionDTO.builder()
                .title("title1")
                .description("description1")
                .departmentId(1)
                .recruiterId(1)
                .build();
    }

    private UpdatePositionDTO getUpdatePositionDTO() {
        return UpdatePositionDTO.builder()
                .title("title1")
                .description("description1")
                .departmentId(1)
                .recruiterId(1)
                .build();
    }

    private Position getPosition() {
        return Position.builder()
                .title("title1")
                .description("description1")
                .recruiter(getRecruiter())
                .department(getDepartment())
                .build();
    }
}
