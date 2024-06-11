package com.venus.datacenter.web.mapper;

import com.venus.datacenter.core.entity.Connector;
import com.venus.datacenter.web.dto.ConnectorDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface ConnectorMapper {

    ConnectorDTO toDTO(Connector connector);

    List<ConnectorDTO> toDTO(List<Connector> connector);

}
