package com.fyp.highriseresbuildms.dao.maintenance_fund_usage;

import com.fyp.highriseresbuildms.entity.MaintenanceFundUsage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaintenanceFundUsageDao extends JpaRepository<MaintenanceFundUsage,Integer> {
    List<MaintenanceFundUsage> findMaintenanceFundUsagesByUsageMonthYear(String yearMonth);
}
