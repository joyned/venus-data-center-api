package com.venus.datacenter.web.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ConnectorSQLDTO extends ConnectorDTO {
    private String host;
    private int port;
    private String database;
    private String username;
    private String password;
    private String testQuery;
    private EngineDTO engine;
}

