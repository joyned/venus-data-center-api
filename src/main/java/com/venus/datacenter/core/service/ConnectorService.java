package com.venus.datacenter.core.service;

import com.venus.datacenter.core.entity.Connector;
import com.venus.datacenter.core.entity.ConnectorRest;
import com.venus.datacenter.core.entity.ConnectorSQL;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

public interface ConnectorService {

    List<Connector> findAll();

    Connector findById(UUID id);

    Connector findConnectorDetailsById(UUID id);

    Connector saveConnector(LinkedHashMap<String, Object> connector);

    Connector saveConnectorRest(ConnectorRest connectorRest);

    Connector saveConnectorSQL(ConnectorSQL connectorSQL);

    void delete(UUID id);
}
