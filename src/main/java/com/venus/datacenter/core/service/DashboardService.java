package com.venus.datacenter.core.service;

import com.venus.datacenter.core.entity.Dashboard;

import java.util.List;
import java.util.UUID;

public interface DashboardService {

    List<Dashboard> findAll();

    Dashboard findById(UUID id);

    Dashboard save(Dashboard dashboard);

    void delete(UUID id);

}
