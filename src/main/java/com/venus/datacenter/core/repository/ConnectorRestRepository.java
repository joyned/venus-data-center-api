package com.venus.datacenter.core.repository;

import com.venus.datacenter.core.entity.ConnectorRest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ConnectorRestRepository extends JpaRepository<ConnectorRest, UUID> {

}
