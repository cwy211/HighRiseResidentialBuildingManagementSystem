package com.fyp.highriseresbuildms.controller;

import com.fyp.highriseresbuildms.dto.MaintenanceFundUsageDto;
import com.fyp.highriseresbuildms.dto.MaintenanceIntervalDto;
import com.fyp.highriseresbuildms.entity.FacilityMaintenance;
import com.fyp.highriseresbuildms.entity.MaintenanceFundUsage;
import com.fyp.highriseresbuildms.entity.MaintenanceInterval;
import com.fyp.highriseresbuildms.service.facility_maintenance.FacilityMaintenanceService;
import com.fyp.highriseresbuildms.service.maintenance_fund_usage.MaintenanceFundUsageService;
import com.fyp.highriseresbuildms.service.maintenance_interval.MaintenanceIntervalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/maintenance")
public class MaintenanceController {
    @Autowired
    private MaintenanceIntervalService maintenanceIntervalService;

    @Autowired
    private FacilityMaintenanceService facilityMaintenanceService;

    @Autowired
    private MaintenanceFundUsageService maintenanceFundUsageService;


    @RequestMapping(value = "/addMaintenanceInterval", method = RequestMethod.POST)
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity addMaintenanceInterval(@RequestBody MaintenanceIntervalDto maintenanceIntervalDto){
        maintenanceIntervalService.addMaintenanceInterval(maintenanceIntervalDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(value="/getAllMaintenanceInterval", method = RequestMethod.GET)
    @PreAuthorize("hasRole('Admin')")
    public List<MaintenanceInterval> getAllMaintenanceInterval(){
        return maintenanceIntervalService.getAllMaintenanceInterval();
    }

    @RequestMapping(value = "/createFacilityMaintenance", method = RequestMethod.POST)
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity createFacilityMaintenance(@RequestBody MaintenanceIntervalDto maintenanceIntervalDto){
        facilityMaintenanceService.createFacilityMaintenance(maintenanceIntervalDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(value = "/createSingleFacilityMaintenance", method = RequestMethod.POST)
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity createSingleFacilityMaintenance(@RequestBody MaintenanceIntervalDto maintenanceIntervalDto){
        facilityMaintenanceService.createSingleFacilityMaintenance(maintenanceIntervalDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(value="/getFacilityMaintenanceByDate", method = RequestMethod.GET)
    @PreAuthorize("hasRole('Admin')")
    public List<FacilityMaintenance> getFacilityMaintenanceByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        return facilityMaintenanceService.getFacilityMaintenanceByDate(date);
    }

    @RequestMapping(value="/getFacilityMaintenanceById", method = RequestMethod.GET)
    @PreAuthorize("hasRole('Admin')")
    public FacilityMaintenance getFacilityMaintenanceById(@RequestParam int id){
        return facilityMaintenanceService.getFacilityMaintenanceById(id);
    }

    @RequestMapping(value = "/saveFacilityMaintenance", method = RequestMethod.POST)
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity saveFacilityMaintenance(@RequestBody FacilityMaintenance facilityMaintenance){
        facilityMaintenanceService.saveFacilityMaintenance(facilityMaintenance);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(value="/getIncompleteFacilityMaintenance", method = RequestMethod.GET)
    @PreAuthorize("hasRole('Admin')")
    public List<FacilityMaintenance> getIncompleteFacilityMaintenance(){
        return facilityMaintenanceService.getIncompleteFacilityMaintenance();
    }

    @RequestMapping(value="/getMaintenanceHistoryByFacility", method = RequestMethod.GET)
    @PreAuthorize("hasRole('Admin')")
    public List<FacilityMaintenance> getMaintenanceHistoryByFacility(@RequestParam int facilityId){
        return facilityMaintenanceService.getMaintenanceHistoryByFacility(facilityId);
    }

    @RequestMapping(value = "/addMaintenanceFundUsage", method = RequestMethod.POST)
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity addMaintenanceFundUsage(@RequestBody MaintenanceFundUsage maintenanceFundUsage){
        maintenanceFundUsageService.addMaintenanceFundUsage(maintenanceFundUsage);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(value = "/getMaintenanceFundUsageByYearMonth", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('User','Admin')")
    public List<MaintenanceFundUsageDto> getMaintenanceFundUsageByYearMonth(@RequestParam("yearMonth") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate yearMonth){
        return maintenanceFundUsageService.getMaintenanceFundUsageByYearMonth(yearMonth);
    }

    @RequestMapping(value = "/getMaintenanceFundUsageById", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('User','Admin')")
    public MaintenanceFundUsage getMaintenanceFundUsageById(@RequestParam int id){
        return maintenanceFundUsageService.getMaintenanceFundUsageById(id);
    }

    @RequestMapping(value = "/deleteMaintenanceFundUsage", method = RequestMethod.DELETE)
    @PreAuthorize("hasAnyRole('User','Admin')")
    public ResponseEntity deleteMaintenanceFundUsage(@RequestParam int id){
        maintenanceFundUsageService.deleteMaintenanceFundUsage(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
