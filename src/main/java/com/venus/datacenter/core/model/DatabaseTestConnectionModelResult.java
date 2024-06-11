package com.venus.datacenter.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatabaseTestConnectionModelResult {
    private boolean success;
    private String message;
}
