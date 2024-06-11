package com.venus.datacenter.core.service.impl;

import com.venus.datacenter.core.entity.ConnectorSQL;
import com.venus.datacenter.core.service.SQLConnector;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;

@Service
public class SQLConnectorImpl implements SQLConnector {

    @Override
    public Connection acquireConnection(ConnectorSQL connectorSQL) {
        if ("POSTGRESQL".equalsIgnoreCase(connectorSQL.getEngine().getName())) {
            return getPostgreSQLConnection(connectorSQL);
        } else if ("ORACLE".equalsIgnoreCase(connectorSQL.getEngine().getName())) {
            return getOracleConnection(connectorSQL);
        }

        return null;
    }

    private Connection getPostgreSQLConnection(ConnectorSQL connectorSQL) {
        StringBuilder jdbc = new StringBuilder();
        jdbc.append("jdbc:postgresql://")
                .append(connectorSQL.getHost());
        if (connectorSQL.getPort() != 0) {
            jdbc.append(":").append(connectorSQL.getPort());
        }
        jdbc.append("/").append(connectorSQL.getDatabase());
        return getConnection(connectorSQL, "org.postgresql.Driver", jdbc.toString());
    }

    private Connection getOracleConnection(ConnectorSQL connectorSQL) {
        StringBuilder jdbc = new StringBuilder();
        jdbc.append("jdbc:oracle:thin:@")
                .append(connectorSQL.getHost());
        if (connectorSQL.getPort() != 0) {
            jdbc.append(":").append(connectorSQL.getPort());
        }
        jdbc.append(":").append(connectorSQL.getDatabase());
        return getConnection(connectorSQL, "oracle.jdbc.OracleDriver", jdbc.toString());
    }

    private Connection getConnection(ConnectorSQL connectorSQL, String driverClassName, String jdbcUrl) {
        try {
            Class.forName(driverClassName);
            return DriverManager.getConnection(jdbcUrl, connectorSQL.getUsername(), connectorSQL.getPassword());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
