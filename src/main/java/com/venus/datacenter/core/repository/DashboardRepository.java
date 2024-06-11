package com.venus.datacenter.core.repository;

import com.venus.datacenter.core.entity.Dashboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DashboardRepository extends JpaRepository<Dashboard, UUID> {
}
