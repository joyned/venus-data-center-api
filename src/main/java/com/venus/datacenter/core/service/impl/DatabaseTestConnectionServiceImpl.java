package com.venus.datacenter.core.service.impl;

import com.venus.datacenter.config.DatabaseTestConnectionService;
import com.venus.datacenter.core.entity.ConnectorSQL;
import com.venus.datacenter.core.model.DatabaseTestConnectionModelResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@Service
@Slf4j
public class DatabaseTestConnectionServiceImpl implements DatabaseTestConnectionService {

    @Override
    public DatabaseTestConnectionModelResult testConnection(ConnectorSQL connectorSQL) {

        if ("POSTGRESQL".equalsIgnoreCase(connectorSQL.getEngine().getName())) {
            return testConnectionPostgreSQL(connectorSQL);
        } else if ("ORACLE".equalsIgnoreCase(connectorSQL.getEngine().getName())) {
            return testConnectionOracle(connectorSQL);
        }

        return null;
    }

    private DatabaseTestConnectionModelResult testConnectionPostgreSQL(ConnectorSQL connectorSQL) {
        StringBuilder jdbc = new StringBuilder();
        jdbc.append("jdbc:postgresql://")
                .append(connectorSQL.getHost());
        if (connectorSQL.getPort() != 0) {
            jdbc.append(":").append(connectorSQL.getPort());
        }
        jdbc.append("/").append(connectorSQL.getDatabase());

        return testConnectionGeneric(connectorSQL, "org.postgresql.Driver", jdbc.toString());
    }

    private DatabaseTestConnectionModelResult testConnectionOracle(ConnectorSQL connectorSQL) {
        StringBuilder jdbc = new StringBuilder();
        jdbc.append("jdbc:oracle:thin:@")
                .append(connectorSQL.getHost());
        if (connectorSQL.getPort() != 0) {
            jdbc.append(":").append(connectorSQL.getPort());
        }
        jdbc.append(":").append(connectorSQL.getDatabase());

        return testConnectionGeneric(connectorSQL, "oracle.jdbc.OracleDriver", jdbc.toString());
    }

    private DatabaseTestConnectionModelResult testConnectionGeneric(ConnectorSQL connectorSQL, String driverClassName,
                                                                    String jdbcUrl) {
        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            return new DatabaseTestConnectionModelResult(false, e.getMessage());
        }

        try (Connection connection =
                     DriverManager.getConnection(jdbcUrl, connectorSQL.getUsername(), connectorSQL.getPassword())) {

            Statement statement = connection.createStatement();
            statement.executeQuery(connectorSQL.getTestQuery());
            return new DatabaseTestConnectionModelResult(true, "Connection was created successfully!");
        } catch (Exception e) {
            log.error("Failed while testing connection for database {}.", connectorSQL.getHost(), e);
            return new DatabaseTestConnectionModelResult(false, e.getMessage());
        }
    }
}
