package com.fyp.highriseresbuildms.dao.facility_maintenance;

import com.fyp.highriseresbuildms.entity.FacilityMaintenance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface FacilityMaintenanceDao extends JpaRepository<FacilityMaintenance,Integer> {
    List<FacilityMaintenance> findFacilityMaintenancesByFmDateEqualsOrderByFmTime(LocalDate date);
    List<FacilityMaintenance> findFacilityMaintenancesByFmDateBeforeAndStatus(LocalDate date, String status);
    List<FacilityMaintenance> findFacilityMaintenancesByFmFacility_IdAndStatus(int facilityId, String status);
}
