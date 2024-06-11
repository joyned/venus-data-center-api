package com.venus.datacenter.web.controller;

import com.venus.datacenter.core.entity.Connector;
import com.venus.datacenter.core.entity.ConnectorRest;
import com.venus.datacenter.core.entity.ConnectorSQL;
import com.venus.datacenter.core.service.ConnectorService;
import com.venus.datacenter.web.dto.ConnectorDTO;
import com.venus.datacenter.web.mapper.ConnectorMapper;
import com.venus.datacenter.web.mapper.ConnectorRestMapper;
import com.venus.datacenter.web.mapper.ConnectorSQLMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/connector")
@RequiredArgsConstructor
public class ConnectorController {

    private final ConnectorService connectorService;
    private final ConnectorMapper connectorMapper;
    private final ConnectorSQLMapper connectorSQLMapper;
    private final ConnectorRestMapper connectorRestMapper;

    @GetMapping
    public ResponseEntity<List<ConnectorDTO>> findAll() {
        List<Connector> connectors = connectorService.findAll();
        List<ConnectorDTO> connectorDTOs = new ArrayList<>();

        connectors.forEach(connector -> {
            ConnectorDTO connectorDTO = connectorMapper.toDTO(connector);
            if (connector instanceof ConnectorSQL) {
                connectorDTO.setType("SQL");
            } else if (connector instanceof ConnectorRest) {
                connectorDTO.setType("REST");
            }
            connectorDTOs.add(connectorDTO);
        });

        return ResponseEntity.ok(connectorDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConnectorDTO> findById(@PathVariable String id) {
        Connector connector = connectorService.findConnectorDetailsById(UUID.fromString(id));

        if (Objects.nonNull(connector)) {
            if (connector instanceof ConnectorSQL) {
                return ResponseEntity.ok(connectorSQLMapper.toDTO((ConnectorSQL) connector));
            } else if (connector instanceof ConnectorRest) {
                return ResponseEntity.ok(connectorRestMapper.toDTO((ConnectorRest) connector));
            }
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<UUID> save(@RequestBody LinkedHashMap<String, Object> body) {
        return ResponseEntity.ok(connectorService.saveConnector(body).getId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        connectorService.delete(UUID.fromString(id));
        return ResponseEntity.ok().build();
    }
}
