package com.fyp.highriseresbuildms.service.facility_maintenance;

import com.fyp.highriseresbuildms.dto.MaintenanceIntervalDto;
import com.fyp.highriseresbuildms.entity.FacilityMaintenance;

import java.time.LocalDate;
import java.util.List;

public interface FacilityMaintenanceService {
    void createFacilityMaintenance(MaintenanceIntervalDto maintenanceIntervalDto);
    void createSingleFacilityMaintenance(MaintenanceIntervalDto maintenanceIntervalDto);
    List<FacilityMaintenance> getFacilityMaintenanceByDate(LocalDate date);
    FacilityMaintenance getFacilityMaintenanceById(int id);
    void saveFacilityMaintenance(FacilityMaintenance facilityMaintenance);
    List<FacilityMaintenance> getIncompleteFacilityMaintenance();
    List<FacilityMaintenance> getMaintenanceHistoryByFacility(int facilityId);
}
