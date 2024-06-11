package com.venus.datacenter.web.controller;

import com.venus.datacenter.core.service.DashboardExecution;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.UUID;

@RestController
@RequestMapping("/dashboard/execution")
@RequiredArgsConstructor
public class DashboardExecutionController {

    private final DashboardExecution dashboardExecution;

    @PostMapping("/{id}")
    public ResponseEntity<Object> execute(@PathVariable String id, @RequestBody Object parameters) {
        try {
            return ResponseEntity.ok(dashboardExecution.execute(UUID.fromString(id), (LinkedHashMap<String, String>) parameters));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
