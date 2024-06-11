package com.venus.datacenter.web.mapper;

import com.venus.datacenter.core.entity.ConnectorSQL;
import com.venus.datacenter.core.entity.Engine;
import com.venus.datacenter.web.dto.ConnectorSQLDTO;
import com.venus.datacenter.web.dto.EngineDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ConnectorSQLMapper {

    ConnectorSQL toModel(ConnectorSQLDTO connectorSQLDTO);

    ConnectorSQLDTO toDTO(ConnectorSQL connectorSQL);

    EngineDTO engineToDTO(Engine engine);
}
