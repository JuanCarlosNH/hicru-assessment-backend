package com.hikru.assessment.controllers;

import com.hikru.assessment.models.dto.PositionDTO;
import com.hikru.assessment.models.dto.UpdatePositionDTO;
import com.hikru.assessment.services.PositionService;
import com.hikru.assessment.services.PositionServiceImpl;
import com.hikru.assessment.tools.ControllerResponseTool;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/positions")
@Validated
public class PositionController extends ControllerResponseTool {

    private final PositionService positionService;

    @Autowired
    public PositionController(PositionServiceImpl positionServiceImpl) {
        this.positionService = positionServiceImpl;
    }

    @GetMapping
    public ResponseEntity<?> findAllPositions() {
        return ok(positionService.findAllPositions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDetails(@Min(value = 1, message = "id cannot be 0 or negative")
                                            @PathVariable Integer id) {
        return ok(positionService.getDetails(id));
    }

    @PostMapping
    public ResponseEntity<?> savePosition(@Valid @RequestBody PositionDTO positionDTO) {
        positionService.savePosition(positionDTO);
        return ok(positionDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePosition(@Min(value = 1, message = "id cannot be 0 or negative")
                                                @PathVariable Integer id,
                                            @Valid @RequestBody UpdatePositionDTO updatePositionDTO) {
        positionService.updatePosition(id, updatePositionDTO);
        return ok(updatePositionDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePosition(@Min(value = 1, message = "id cannot be 0 or negative")
                                                @PathVariable Integer id) {
        positionService.deletePosition(id);
        return ok("Position deleted");
    }

}
