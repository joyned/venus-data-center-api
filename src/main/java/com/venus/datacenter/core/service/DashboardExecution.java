package com.venus.datacenter.core.service;

import java.util.LinkedHashMap;
import java.util.UUID;

public interface DashboardExecution {

    Object execute(UUID dashboardId, LinkedHashMap<String, String> parameters);

}
