package com.venus.datacenter.web.controller;

import com.venus.datacenter.config.DatabaseTestConnectionService;
import com.venus.datacenter.core.model.DatabaseTestConnectionModelResult;
import com.venus.datacenter.web.dto.ConnectorSQLDTO;
import com.venus.datacenter.web.mapper.ConnectorSQLMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test-sql-connection")
@RequiredArgsConstructor
public class DatabaseTestConnectionController {

    private final DatabaseTestConnectionService databaseTestConnectionService;
    private final ConnectorSQLMapper connectorSQLMapper;

    @PostMapping
    public ResponseEntity<DatabaseTestConnectionModelResult> testConnection(@RequestBody ConnectorSQLDTO connectorSQLDTO) {
        DatabaseTestConnectionModelResult dto =
                databaseTestConnectionService.testConnection(connectorSQLMapper.toModel(connectorSQLDTO));

        if (dto.isSuccess()) {
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.internalServerError().body(dto);
        }

    }
}
