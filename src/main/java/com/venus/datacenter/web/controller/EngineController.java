package com.venus.datacenter.web.controller;

import com.venus.datacenter.core.service.EngineService;
import com.venus.datacenter.web.dto.EngineDTO;
import com.venus.datacenter.web.mapper.EngineMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/engine")
@RequiredArgsConstructor
public class EngineController {

    private final EngineService engineService;
    private final EngineMapper engineMapper;

    @GetMapping
    public ResponseEntity<List<EngineDTO>> findAll() {
        return ResponseEntity.ok(engineMapper.toDTO(engineService.findAll()));
    }
}
