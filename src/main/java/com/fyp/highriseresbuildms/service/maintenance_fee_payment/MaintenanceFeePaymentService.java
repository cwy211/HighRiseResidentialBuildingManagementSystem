package com.fyp.highriseresbuildms.service.maintenance_fee_payment;

import com.fyp.highriseresbuildms.dto.MaintenanceFeeToBePaidDto;

import java.util.List;

public interface MaintenanceFeePaymentService {
    List<MaintenanceFeeToBePaidDto> getMaintenanceFeeToBePaid();
}
