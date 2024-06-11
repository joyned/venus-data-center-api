package com.venus.datacenter.web.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ConnectorDTO {
    private UUID id;
    private String name;
    private String description;
    private String type;
}

