package com.venus.datacenter.core.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dashboard {

    @Id
    @GeneratedValue
    private UUID id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String query;

    @OneToOne
    @JoinColumn(name = "connector_id")
    private Connector connector;
}
