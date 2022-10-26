package com.fyp.highriseresbuildms.controller;

import com.fyp.highriseresbuildms.dto.MaintenanceFeeToBePaidDto;
import com.fyp.highriseresbuildms.service.maintenance_fee_payment.MaintenanceFeePaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fee")
public class MaintenanceFeePaymentController {
    @Autowired
    private MaintenanceFeePaymentService maintenanceFeePaymentService;

    @RequestMapping(value = "/getMaintenanceFeeToBePaid", method = RequestMethod.GET)
    @PreAuthorize("hasRole('User')")
    public List<MaintenanceFeeToBePaidDto> getMaintenanceFeeToBePaid(){
        return maintenanceFeePaymentService.getMaintenanceFeeToBePaid();
    }




}
