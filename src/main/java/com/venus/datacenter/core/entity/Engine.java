package com.venus.datacenter.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Engine {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;
}

