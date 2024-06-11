package com.venus.datacenter.core.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.venus.datacenter.core.entity.Connector;
import com.venus.datacenter.core.entity.ConnectorRest;
import com.venus.datacenter.core.entity.ConnectorSQL;
import com.venus.datacenter.core.entity.Engine;
import com.venus.datacenter.core.repository.ConnectorRepository;
import com.venus.datacenter.core.repository.ConnectorRestRepository;
import com.venus.datacenter.core.repository.ConnectorSQLRepository;
import com.venus.datacenter.core.service.ConnectorService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ConnectorServiceImpl implements ConnectorService {

    private final ConnectorRepository connectorRepository;
    private final ConnectorRestRepository connectorRestRepository;
    private final ConnectorSQLRepository connectorSQLRepository;

    @Override
    public List<Connector> findAll() {
        return connectorRepository.findAll();
    }

    @Override
    public Connector findById(UUID id) {
        return connectorRepository.findById(id).orElse(null);
    }

    @Override
    public Connector findConnectorDetailsById(UUID id) {
        Optional<Connector> connector = connectorRepository.findById(id);
        return connector.orElse(null);
    }

    @Override
    @Transactional
    public Connector saveConnector(LinkedHashMap<String, Object> connector) {
        // TODO: melhorar esse cara pra talvez receber um DTO que tem tudo?
        Connector con = null;
        ObjectMapper objectMapper = new ObjectMapper();

        UUID uuid = Objects.isNull(connector.get("id")) ? null : UUID.fromString((String) connector.get("id"));

        if (StringUtils.hasText((CharSequence) connector.get("testQuery"))) {
            ConnectorSQL connectorSQL = new ConnectorSQL();
            connectorSQL.setId(uuid);
            connectorSQL.setName((String) connector.get("name"));
            connectorSQL.setDescription((String) connector.get("description"));
            connectorSQL.setHost((String) connector.get("host"));
            connectorSQL.setPort(Objects.isNull(connector.get("port")) ? 0 : Integer.parseInt((String) connector.get("port")));
            connectorSQL.setDatabase((String) connector.get("database"));
            connectorSQL.setUsername((String) connector.get("username"));
            connectorSQL.setPassword((String) connector.get("password"));
            connectorSQL.setTestQuery((String) connector.get("testQuery"));
            connectorSQL.setEngine(objectMapper.convertValue(connector.get("engine"), Engine.class));
            con = saveConnectorSQL(connectorSQL);
        } else {
            ConnectorRest connectorRest = new ConnectorRest();
            connectorRest.setId(uuid);
            connectorRest.setName((String) connector.get("name"));
            connectorRest.setDescription((String) connector.get("description"));
            connectorRest.setUrl((String) connector.get("url"));
            connectorRest.setMethod(((LinkedHashMap<String, String>) connector.get("method")).get("name"));
            connectorRest.setHeaders((String) connector.get("headers"));
            con = saveConnectorRest(connectorRest);
        }

        return con;
    }

    @Override
    public Connector saveConnectorRest(ConnectorRest connectorRest) {
        return connectorRestRepository.save(connectorRest);
    }

    @Override
    public Connector saveConnectorSQL(ConnectorSQL connectorSQL) {
        return connectorSQLRepository.save(connectorSQL);
    }

    @Override
    public void delete(UUID id) {
        connectorRepository.deleteById(id);
    }

}
