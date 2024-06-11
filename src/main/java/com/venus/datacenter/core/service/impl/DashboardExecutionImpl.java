package com.venus.datacenter.core.service.impl;

import com.venus.datacenter.core.entity.Connector;
import com.venus.datacenter.core.entity.ConnectorSQL;
import com.venus.datacenter.core.entity.Dashboard;
import com.venus.datacenter.core.exception.DashboardExecutionException;
import com.venus.datacenter.core.repository.ConnectorRepository;
import com.venus.datacenter.core.repository.DashboardRepository;
import com.venus.datacenter.core.service.DashboardExecution;
import com.venus.datacenter.core.service.SQLConnector;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

@Service
@RequiredArgsConstructor
public class DashboardExecutionImpl implements DashboardExecution {

    private final DashboardRepository dashboardRepository;
    private final ConnectorRepository connectorRepository;
    private final SQLConnector sqlConnector;

    @Override
    public Object execute(UUID dashboardId, LinkedHashMap<String, String> parameters) {
        Dashboard dashboard = dashboardRepository.findById(dashboardId).orElse(null);

        if (Objects.isNull(dashboard)) {
            return null;
        }

        Connector connector = connectorRepository.findById(dashboard.getConnector().getId()).orElse(null);

        if (Objects.isNull(connector)) {
            return null;
        }

        if (connector instanceof ConnectorSQL) {
            String query = dashboard.getQuery();
            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                query = query.replace(key, value.trim());
            }
            return executeSQLConnector((ConnectorSQL) connector, query);
        }

        return null;
    }

    private Object executeSQLConnector(ConnectorSQL connector, String query) {
        List<LinkedHashMap<String, Object>> result = new ArrayList<>();
        try (Connection connection = sqlConnector.acquireConnection(connector)) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                LinkedHashMap<String, Object> row = new LinkedHashMap<>();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.put(rs.getMetaData().getColumnName(i), rs.getObject(i));
                }
                result.add(row);
            }
        } catch (Exception e) {
            throw new DashboardExecutionException(e);
        }
        return result;
    }

}
