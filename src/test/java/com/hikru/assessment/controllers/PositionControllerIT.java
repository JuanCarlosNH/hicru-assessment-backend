package com.hikru.assessment.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hikru.assessment.exceptions.GlobalExceptionHandler;
import com.hikru.assessment.exceptions.PositionNotFoundException;
import com.hikru.assessment.models.dto.DetailsDTO;
import com.hikru.assessment.models.dto.PositionDTO;
import com.hikru.assessment.models.dto.UpdatePositionDTO;
import com.hikru.assessment.services.PositionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PositionControllerIT {

    private MockMvc mockMvc;
    private PositionController positionController;

    @MockitoBean
    private PositionServiceImpl positionService;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        positionController = new PositionController(positionService);
        mockMvc = MockMvcBuilders.standaloneSetup(positionController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void getAllPositionReturn200() throws Exception {
        when(positionService.findAllPositions()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/positions"))
                .andExpect(status().is(200));
    }

    @Test
    public void getDetailsReturn200() throws Exception {
        when(positionService.getDetails(anyInt())).thenReturn(DetailsDTO.builder().build());
        mockMvc.perform(get("/api/positions/{id}", 1))
                .andExpect(status().is(200));
    }

    @Test
    public void getDetailsPositionNotFoundException() {
        doThrow(new PositionNotFoundException("INTENTIONAL TEST EXCEPTION", 5))
                .when(positionService).getDetails(anyInt());
        assertThrows(PositionNotFoundException.class, () -> positionController.getDetails(anyInt()));
    }

    @Test
    public void savePositionReturn200() throws Exception {

        PositionDTO positionDTO = PositionDTO.builder()
                                    .title("title")
                                    .description("description")
                                    .status("open")
                                    .location("location")
                                    .budget(100.0)
                                    .closingDate(new Date())
                                    .departmentId(1)
                                    .recruiterId(1)
                                    .build();

        doNothing().when(positionService).savePosition(any());
        mockMvc.perform(post("/api/positions")
                                    .content(objectMapper.writeValueAsBytes(positionDTO))
                                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    public void savePositionReturn400() throws Exception {

        PositionDTO positionDTO = PositionDTO.builder()
                .title("title")
                .description("description")
                .status("open")
                .location("location")
                .budget(100.0)
                .closingDate(new Date())
                .departmentId(1)
                .build();

        doNothing().when(positionService).savePosition(any());
        mockMvc.perform(post("/api/positions")
                        .content(objectMapper.writeValueAsBytes(positionDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400));
    }

    @Test
    public void updatePositionReturn200() throws Exception {

        UpdatePositionDTO updatePositionDTO = UpdatePositionDTO.builder()
                .title("title")
                .description("description")
                .status("open")
                .location("location")
                .budget(100.0)
                .closingDate(new Date())
                .departmentId(1)
                .recruiterId(1)
                .build();

        doNothing().when(positionService).updatePosition(anyInt(), any());
        mockMvc.perform(put("/api/positions/{id}", 1)
                        .content(objectMapper.writeValueAsBytes(updatePositionDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    public void updatePositionReturn400() throws Exception {

        UpdatePositionDTO updatePositionDTO = UpdatePositionDTO.builder()
                .title("title")
                .description("description")
                .status("openAndClose")
                .location("location")
                .budget(100.0)
                .closingDate(new Date())
                .departmentId(1)
                .recruiterId(1)
                .build();

        doNothing().when(positionService).updatePosition(anyInt(), any());
        mockMvc.perform(put("/api/positions/{id}", 1)
                        .content(objectMapper.writeValueAsBytes(updatePositionDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400));
    }

    @Test
    public void deletePositionReturn200() throws Exception {
        doNothing().when(positionService).deletePosition(anyInt());
        mockMvc.perform(delete("/api/positions/{id}", 1))
                .andExpect(status().is(200));
    }

}
