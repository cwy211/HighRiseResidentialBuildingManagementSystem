package com.fyp.highriseresbuildms.service.maintenance_fee_payment;

import com.fyp.highriseresbuildms.dao.maintenance_fund_usage.MaintenanceFundUsageDao;
import com.fyp.highriseresbuildms.dao.ownership.OwnershipDao;
import com.fyp.highriseresbuildms.dto.MaintenanceFeeToBePaidDto;
import com.fyp.highriseresbuildms.entity.MaintenanceFundUsage;
import com.fyp.highriseresbuildms.entity.Ownership;
import com.fyp.highriseresbuildms.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MaintenanceFeePaymentServiceImpl implements MaintenanceFeePaymentService{


    @Autowired
    private MaintenanceFundUsageDao maintenanceFundUsageDao;

    @Autowired
    private OwnershipDao ownershipDao;

    @Autowired
    private UserService userService;





    @Override //calculating maintenance fee for each resident with ownership
    public List<MaintenanceFeeToBePaidDto> getMaintenanceFeeToBePaid() {
        //get ownership of resident
        Ownership ownership = ownershipDao.findOwnershipByOwner_UserId(userService.getUserName());
        //create list for storing maintenance fee
        List<MaintenanceFeeToBePaidDto> maintenanceFeeToBePaidDtoList = new ArrayList<>();
        //check for existence of ownership
        if(ownership!=null){
            //get first day of the month of the start date of ownership
            LocalDate startingDate= ownership.getStartDate().with(TemporalAdjusters.firstDayOfMonth());
            LocalDate currentDate=LocalDate.now();
            // for comparing time
            int compareTo =0;
            // while the starting date does not exceed current date
                while(compareTo<=0){
                    BigDecimal sum = BigDecimal.valueOf(0);
                    MaintenanceFeeToBePaidDto maintenanceFeeToBePaidDto = new MaintenanceFeeToBePaidDto();
                    String yearMonthString = startingDate.getYear()+"-"+startingDate.getMonthValue();
                    //get maintenance fund usage of first month of ownership
                    List<MaintenanceFundUsage> maintenanceFundUsageList = maintenanceFundUsageDao.findMaintenanceFundUsagesByUsageMonthYear(yearMonthString);
                    //for loop to get total maintenance fund usage of the month
                    for(MaintenanceFundUsage maintenanceFundUsage:maintenanceFundUsageList){
                        sum = sum.add(maintenanceFundUsage.getPrice());
                    }
                    // get number of ownerships of the month
                    long numberOfOwnership=ownershipDao.countOwnershipsByStartDateIsBefore(startingDate.with(TemporalAdjusters.lastDayOfMonth()));
                    //divide total maintenance fund usage of the month with number of ownerships of the month
                    BigDecimal amountPayable = sum.divide(BigDecimal.valueOf(numberOfOwnership),2,RoundingMode.HALF_UP);
                    maintenanceFeeToBePaidDto.setTotal(amountPayable);
                    maintenanceFeeToBePaidDto.setYearMonth(startingDate);
                    maintenanceFeeToBePaidDto.setNumberOfOwnedUnits((int) numberOfOwnership);
                    maintenanceFeeToBePaidDto.setTotalMaintenanceFundUsed(sum);
                    //get starting date of next month of ownership
                    startingDate=startingDate.plusMonths(1);
                    //next whether the next month exceeds the current date
                    compareTo=(startingDate.compareTo(currentDate));
                    //add maintenance fee details into list for storing maintenance fee
                    maintenanceFeeToBePaidDtoList.add(maintenanceFeeToBePaidDto);
            }
            return maintenanceFeeToBePaidDtoList;
        }
        return maintenanceFeeToBePaidDtoList;
    }
}
