package com.venus.datacenter.web.controller;

import com.venus.datacenter.core.service.DashboardService;
import com.venus.datacenter.web.dto.DashboardDTO;
import com.venus.datacenter.web.mapper.DashboardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;
    private final DashboardMapper dashboardMapper;

    @GetMapping
    public ResponseEntity<List<DashboardDTO>> findAll() {
        return ResponseEntity.ok(dashboardMapper.toDTO(dashboardService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DashboardDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok(dashboardMapper.toDTO(dashboardService.findById(UUID.fromString(id))));
    }

    @PostMapping
    public ResponseEntity<DashboardDTO> save(@RequestBody DashboardDTO dashboardDTO) {
        return ResponseEntity.ok(dashboardMapper.toDTO(dashboardService.save(dashboardMapper.toModel(dashboardDTO))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        dashboardService.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

}