package com.venus.datacenter.core.model;

import lombok.Getter;

@Getter
public enum ConnectorTypeEnum {
    SQL("SQL"),
    REST("REST");

    private final String value;

    ConnectorTypeEnum(String value) {
        this.value = value;
    }
}
