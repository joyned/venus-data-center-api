package com.venus.datacenter.web.mapper;

import com.venus.datacenter.core.entity.Engine;
import com.venus.datacenter.web.dto.EngineDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface EngineMapper {

    Engine toModel(EngineDTO engineDTO);

    EngineDTO map(Engine value);

    List<EngineDTO> toDTO(List<Engine> engine);
}
