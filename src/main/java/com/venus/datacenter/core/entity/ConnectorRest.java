package com.venus.datacenter.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "connector_rest")
@Data
@NoArgsConstructor
public class ConnectorRest extends Connector {

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private String method;

    @Column
    private String headers;

}
