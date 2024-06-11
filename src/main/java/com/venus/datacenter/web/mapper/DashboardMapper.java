package com.venus.datacenter.web.mapper;

import com.venus.datacenter.core.entity.Connector;
import com.venus.datacenter.core.entity.ConnectorRest;
import com.venus.datacenter.core.entity.ConnectorSQL;
import com.venus.datacenter.core.entity.Dashboard;
import com.venus.datacenter.core.model.ConnectorTypeEnum;
import com.venus.datacenter.web.dto.ConnectorDTO;
import com.venus.datacenter.web.dto.DashboardDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface DashboardMapper {

    Dashboard toModel(DashboardDTO dashboardDTO);

    @Mapping(target = "connector", source = "connector", qualifiedByName = "createConnectorDTO")
    DashboardDTO toDTO(Dashboard dashboard);

    List<DashboardDTO> toDTO(List<Dashboard> dashboards);

    default Connector createConnector(ConnectorDTO connector) {
        if (ConnectorTypeEnum.SQL.getValue().equalsIgnoreCase(connector.getType())) {
            ConnectorSQL connectorSQL = new ConnectorSQL();
            connectorSQL.setId(connector.getId());
            connectorSQL.setName(connector.getName());
            connectorSQL.setDescription(connector.getDescription());
            return connectorSQL;
        } else if (ConnectorTypeEnum.REST.getValue().equalsIgnoreCase(connector.getType())) {
            ConnectorRest connectorRest = new ConnectorRest();
            connectorRest.setId(connector.getId());
            connectorRest.setName(connector.getName());
            connectorRest.setDescription(connector.getDescription());
            return connectorRest;
        }
        return null;
    }

    @Named("createConnectorDTO")
    default ConnectorDTO createConnectorDTO(Connector connector) {
        ConnectorDTO connectorDTO = new ConnectorDTO();
        connectorDTO.setId(connector.getId());
        connectorDTO.setName(connector.getName());
        connectorDTO.setDescription(connector.getDescription());
        if (connector instanceof ConnectorSQL) {
            connectorDTO.setType(ConnectorTypeEnum.SQL.getValue());
        } else if (connector instanceof ConnectorRest) {
            connectorDTO.setType(ConnectorTypeEnum.REST.getValue());
        }
        return connectorDTO;
    }
}
