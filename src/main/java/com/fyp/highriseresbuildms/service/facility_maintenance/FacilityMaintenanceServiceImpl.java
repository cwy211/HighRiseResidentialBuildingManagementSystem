package com.fyp.highriseresbuildms.service.facility_maintenance;

import com.fyp.highriseresbuildms.dao.facility.FacilityDao;
import com.fyp.highriseresbuildms.dao.facility_maintenance.FacilityMaintenanceDao;
import com.fyp.highriseresbuildms.dto.MaintenanceIntervalDto;
import com.fyp.highriseresbuildms.entity.FacilityMaintenance;
import com.fyp.highriseresbuildms.service.maintenance_interval.MaintenanceIntervalService;
import com.fyp.highriseresbuildms.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class FacilityMaintenanceServiceImpl implements FacilityMaintenanceService{
    @Autowired
    private FacilityMaintenanceDao facilityMaintenanceDao;

    @Autowired
    private MaintenanceIntervalService maintenanceIntervalService;

    @Autowired
    private FacilityDao facilityDao;

    @Autowired
    private UserService userService;

    @Override
    public void createFacilityMaintenance(MaintenanceIntervalDto maintenanceIntervalDto) {
        maintenanceIntervalService.addMaintenanceInterval(maintenanceIntervalDto);
        LocalDate startDate = maintenanceIntervalDto.getStartDate();
        LocalDate endDate = maintenanceIntervalDto.getEndDate();
        int compareTo =0;
        int add=0;
        switch (maintenanceIntervalDto.getInterval().toLowerCase()){
            case "daily":
                while(compareTo<=0){
                    FacilityMaintenance newFacilityMaintenance = new FacilityMaintenance();
                    newFacilityMaintenance.setFmFacility(facilityDao.findById(maintenanceIntervalDto.getFacility()).get());
                    newFacilityMaintenance.setFmDate(startDate.plusDays(add));
                    newFacilityMaintenance.setFmTime(maintenanceIntervalDto.getTime());
                    newFacilityMaintenance.setStatus("Incomplete");
                    facilityMaintenanceDao.save(newFacilityMaintenance);
                    add++;
                    compareTo=(startDate.plusDays(add)).compareTo(endDate);
                }
                break;
            case "weekly":
                while(compareTo<=0){
                    FacilityMaintenance newFacilityMaintenance = new FacilityMaintenance();
                    newFacilityMaintenance.setFmFacility(facilityDao.findById(maintenanceIntervalDto.getFacility()).get());
                    newFacilityMaintenance.setFmDate(startDate.plusWeeks(add));
                    newFacilityMaintenance.setFmTime(maintenanceIntervalDto.getTime());
                    newFacilityMaintenance.setStatus("Incomplete");
                    facilityMaintenanceDao.save(newFacilityMaintenance);
                    add++;
                    compareTo=(startDate.plusWeeks(add)).compareTo(endDate);
                }
                break;
            case "monthly":
                while(compareTo<=0){
                    FacilityMaintenance newFacilityMaintenance = new FacilityMaintenance();
                    newFacilityMaintenance.setFmFacility(facilityDao.findById(maintenanceIntervalDto.getFacility()).get());
                    newFacilityMaintenance.setFmDate(startDate.plusMonths(add));
                    newFacilityMaintenance.setFmTime(maintenanceIntervalDto.getTime());
                    newFacilityMaintenance.setStatus("Incomplete");
                    facilityMaintenanceDao.save(newFacilityMaintenance);
                    add++;
                    compareTo=(startDate.plusMonths(add)).compareTo(endDate);
                }
                break;

            case "yearly":
                while(compareTo<=0){
                    FacilityMaintenance newFacilityMaintenance = new FacilityMaintenance();
                    newFacilityMaintenance.setFmFacility(facilityDao.findById(maintenanceIntervalDto.getFacility()).get());
                    newFacilityMaintenance.setFmDate(startDate.plusYears(add));
                    newFacilityMaintenance.setFmTime(maintenanceIntervalDto.getTime());
                    newFacilityMaintenance.setStatus("Incomplete");
                    facilityMaintenanceDao.save(newFacilityMaintenance);
                    add++;
                    compareTo=(startDate.plusYears(add)).compareTo(endDate);
                }
                break;
            default:
                while(compareTo<=0){
                    FacilityMaintenance newFacilityMaintenance = new FacilityMaintenance();
                    newFacilityMaintenance.setFmFacility(facilityDao.findById(maintenanceIntervalDto.getFacility()).get());
                    newFacilityMaintenance.setFmDate(startDate.plusDays(add));
                    newFacilityMaintenance.setFmTime(maintenanceIntervalDto.getTime());
                    newFacilityMaintenance.setStatus("Incomplete");
                    facilityMaintenanceDao.save(newFacilityMaintenance);
                    add=add+Integer.parseInt(maintenanceIntervalDto.getInterval());
                    compareTo=(startDate.plusDays(add)).compareTo(endDate);
                }
                break;
        }

    }

    @Override
    public void createSingleFacilityMaintenance(MaintenanceIntervalDto maintenanceIntervalDto) {
        FacilityMaintenance facilityMaintenance = new FacilityMaintenance();
        facilityMaintenance.setFmDate(maintenanceIntervalDto.getStartDate());
        facilityMaintenance.setFmTime(maintenanceIntervalDto.getTime());
        facilityMaintenance.setStatus("Incomplete");
        facilityMaintenance.setFmFacility(facilityDao.findById(maintenanceIntervalDto.getFacility()).get());
        facilityMaintenanceDao.save(facilityMaintenance);
    }

    @Override
    public List<FacilityMaintenance> getFacilityMaintenanceByDate(LocalDate date) {
        return facilityMaintenanceDao.findFacilityMaintenancesByFmDateEqualsOrderByFmTime(date);
    }

    @Override
    public FacilityMaintenance getFacilityMaintenanceById(int id) {
        return facilityMaintenanceDao.findById(id).get();
    }

    @Override
    public void saveFacilityMaintenance(FacilityMaintenance facilityMaintenance) {
        FacilityMaintenance updatedFacilityMaintenance=facilityMaintenanceDao.findById(facilityMaintenance.getId()).get();
        updatedFacilityMaintenance.setDescription(facilityMaintenance.getDescription());
        updatedFacilityMaintenance.setType(facilityMaintenance.getType());
        updatedFacilityMaintenance.setStatus("Completed");
        updatedFacilityMaintenance.setAdminIncharged(userService.getUser(userService.getUserName()));
        facilityMaintenanceDao.save(updatedFacilityMaintenance);
    }

    @Override
    public List<FacilityMaintenance> getIncompleteFacilityMaintenance() {
        return facilityMaintenanceDao.findFacilityMaintenancesByFmDateBeforeAndStatus(LocalDate.now(),"Incomplete");
    }

    @Override
    public List<FacilityMaintenance> getMaintenanceHistoryByFacility(int facilityId) {
        return facilityMaintenanceDao.findFacilityMaintenancesByFmFacility_IdAndStatus(facilityId,"Completed");
    }
}
