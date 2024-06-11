package com.venus.datacenter.core.service.impl;

import com.venus.datacenter.core.entity.Dashboard;
import com.venus.datacenter.core.repository.DashboardRepository;
import com.venus.datacenter.core.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final DashboardRepository dashboardRepository;

    @Override
    public List<Dashboard> findAll() {
        return dashboardRepository.findAll();
    }

    @Override
    public Dashboard findById(UUID id) {
        return dashboardRepository.findById(id).orElse(null);
    }

    @Override
    public Dashboard save(Dashboard dashboard) {
        return dashboardRepository.save(dashboard);
    }

    @Override
    public void delete(UUID id) {
        dashboardRepository.deleteById(id);
    }

}
