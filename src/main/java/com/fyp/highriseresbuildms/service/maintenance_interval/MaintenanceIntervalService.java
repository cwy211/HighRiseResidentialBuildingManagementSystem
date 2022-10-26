package com.fyp.highriseresbuildms.service.maintenance_interval;

import com.fyp.highriseresbuildms.dto.MaintenanceIntervalDto;
import com.fyp.highriseresbuildms.entity.MaintenanceInterval;

import java.util.List;

public interface MaintenanceIntervalService {
    void addMaintenanceInterval(MaintenanceIntervalDto maintenanceIntervalDto);
    List<MaintenanceInterval> getAllMaintenanceInterval();
}
