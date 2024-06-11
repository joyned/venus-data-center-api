package com.venus.datacenter.config;

import com.venus.datacenter.core.entity.ConnectorSQL;
import com.venus.datacenter.core.model.DatabaseTestConnectionModelResult;

public interface DatabaseTestConnectionService {

    DatabaseTestConnectionModelResult testConnection(ConnectorSQL connectorSQL);
}
