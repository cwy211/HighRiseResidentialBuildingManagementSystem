package com.fyp.highriseresbuildms.service.maintenance_fund_usage;

import com.fyp.highriseresbuildms.dto.MaintenanceFundUsageDto;
import com.fyp.highriseresbuildms.entity.MaintenanceFundUsage;

import java.time.LocalDate;
import java.util.List;

public interface MaintenanceFundUsageService {
    void addMaintenanceFundUsage(MaintenanceFundUsage maintenanceFundUsage);
    List<MaintenanceFundUsageDto> getMaintenanceFundUsageByYearMonth(LocalDate yearMonth);
    MaintenanceFundUsage getMaintenanceFundUsageById(int id);
    void deleteMaintenanceFundUsage(int id);
}
