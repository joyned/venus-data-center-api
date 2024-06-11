package com.venus.datacenter.core.service.impl;

import com.venus.datacenter.core.entity.Engine;
import com.venus.datacenter.core.repository.EngineRepository;
import com.venus.datacenter.core.service.EngineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EngineServiceImpl implements EngineService {

    private final EngineRepository engineRepository;

    @Override
    public List<Engine> findAll() {
        return engineRepository.findAll();
    }
}
