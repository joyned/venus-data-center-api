package com.venus.datacenter.web.mapper;

import com.venus.datacenter.core.entity.ConnectorRest;
import com.venus.datacenter.web.dto.ConnectorRestDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ConnectorRestMapper {

    ConnectorRest toModel(ConnectorRestDTO connectorRestDTO);

    ConnectorRestDTO toDTO(ConnectorRest connectorRest);
}
