package com.venus.datacenter.core.service;

import com.venus.datacenter.core.entity.ConnectorSQL;

import java.sql.Connection;

public interface SQLConnector {

    Connection acquireConnection(ConnectorSQL connectorSQL);

}
