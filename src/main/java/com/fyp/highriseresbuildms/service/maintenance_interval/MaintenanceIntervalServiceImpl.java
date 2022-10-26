package com.fyp.highriseresbuildms.service.maintenance_interval;

import com.fyp.highriseresbuildms.dao.facility.FacilityDao;
import com.fyp.highriseresbuildms.dao.maintenance_interval.MaintenanceIntervalDao;
import com.fyp.highriseresbuildms.dto.MaintenanceIntervalDto;
import com.fyp.highriseresbuildms.entity.MaintenanceInterval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MaintenanceIntervalServiceImpl implements MaintenanceIntervalService{
    @Autowired
    private MaintenanceIntervalDao maintenanceIntervalDao;

    @Autowired
    private FacilityDao facilityDao;

    @Override
    public void addMaintenanceInterval(MaintenanceIntervalDto maintenanceIntervalDto) {
        MaintenanceInterval maintenanceInterval=new MaintenanceInterval();
        maintenanceInterval.setInterval(maintenanceIntervalDto.getInterval());
        maintenanceInterval.setStartDate(maintenanceIntervalDto.getStartDate());
        maintenanceInterval.setTime(maintenanceIntervalDto.getTime());
        maintenanceInterval.setMiFacility(facilityDao.findById(maintenanceIntervalDto.getFacility()).get());
        maintenanceInterval.setEndDate(maintenanceIntervalDto.getEndDate());
        maintenanceIntervalDao.save(maintenanceInterval);
    }

    @Override
    public List<MaintenanceInterval> getAllMaintenanceInterval() {
        return maintenanceIntervalDao.findAll();
    }

}
