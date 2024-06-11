package com.venus.datacenter.core.repository;

import com.venus.datacenter.core.entity.ConnectorSQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ConnectorSQLRepository extends JpaRepository<ConnectorSQL, UUID> {
}
