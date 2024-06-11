package com.venus.datacenter.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardDTO {
    private UUID id;
    private String name;
    private String description;
    private String query;
    private ConnectorDTO connector;
}
