package com.venus.datacenter.web.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ConnectorRestDTO extends ConnectorDTO {
    private String url;
    private String method;
    private String headers;
}

