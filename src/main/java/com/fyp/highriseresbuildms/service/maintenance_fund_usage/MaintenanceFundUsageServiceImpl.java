package com.fyp.highriseresbuildms.service.maintenance_fund_usage;

import com.fyp.highriseresbuildms.dao.maintenance_fund_usage.MaintenanceFundUsageDao;
import com.fyp.highriseresbuildms.dto.MaintenanceFundUsageDto;
import com.fyp.highriseresbuildms.entity.MaintenanceFundUsage;
import com.fyp.highriseresbuildms.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MaintenanceFundUsageServiceImpl implements MaintenanceFundUsageService{

    @Autowired
    private MaintenanceFundUsageDao maintenanceFundUsageDao;

    @Autowired
    private UserService userService;

    @Override
    public void addMaintenanceFundUsage(MaintenanceFundUsage maintenanceFundUsage) {
        maintenanceFundUsage.setUploader(userService.getUser(userService.getUserName()));
        maintenanceFundUsage.setUploadDateTime(LocalDateTime.now());
        LocalDate localDate=maintenanceFundUsage.getPaymentDate();
        String monthYear = localDate.getYear()+"-"+localDate.getMonthValue();
        maintenanceFundUsage.setUsageMonthYear(monthYear);
        maintenanceFundUsageDao.save(maintenanceFundUsage);
    }

    @Override
    public List<MaintenanceFundUsageDto> getMaintenanceFundUsageByYearMonth(LocalDate yearMonth) {
        String yearMonthString = yearMonth.getYear()+"-"+yearMonth.getMonthValue();
        List<MaintenanceFundUsage> maintenanceFundUsageList=maintenanceFundUsageDao.findMaintenanceFundUsagesByUsageMonthYear(yearMonthString);
        List<MaintenanceFundUsageDto> maintenanceFundUsageDtos=new ArrayList<>();
        for(MaintenanceFundUsage maintenanceFundUsage:maintenanceFundUsageList){
            MaintenanceFundUsageDto maintenanceFundUsageDto=new MaintenanceFundUsageDto();
            maintenanceFundUsageDto.setId(maintenanceFundUsage.getId());
            maintenanceFundUsageDto.setTitle(maintenanceFundUsage.getTitle());
            maintenanceFundUsageDto.setDescription(maintenanceFundUsage.getDescription());
            maintenanceFundUsageDto.setUsageMonthYear(maintenanceFundUsage.getUsageMonthYear());
            maintenanceFundUsageDto.setUploadDateTime(maintenanceFundUsage.getUploadDateTime());
            maintenanceFundUsageDto.setUploader(maintenanceFundUsage.getUploader());
            maintenanceFundUsageDto.setPaymentDate(maintenanceFundUsage.getPaymentDate());
            maintenanceFundUsageDto.setPrice(maintenanceFundUsage.getPrice());
            maintenanceFundUsageDtos.add(maintenanceFundUsageDto);
        }
        return maintenanceFundUsageDtos;
    }

    @Override
    public MaintenanceFundUsage getMaintenanceFundUsageById(int id) {
        return maintenanceFundUsageDao.findById(id).get();
    }

    @Override
    public void deleteMaintenanceFundUsage(int id) {
        maintenanceFundUsageDao.deleteById(id);
    }
}
