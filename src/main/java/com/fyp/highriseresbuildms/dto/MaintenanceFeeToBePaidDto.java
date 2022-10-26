package com.fyp.highriseresbuildms.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MaintenanceFeeToBePaidDto {
    private BigDecimal total;
    private LocalDate yearMonth;
    private BigDecimal totalMaintenanceFundUsed;
    private int numberOfOwnedUnits;

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public LocalDate getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(LocalDate yearMonth) {
        this.yearMonth = yearMonth;
    }

    public BigDecimal getTotalMaintenanceFundUsed() {
        return totalMaintenanceFundUsed;
    }

    public void setTotalMaintenanceFundUsed(BigDecimal totalMaintenanceFundUsed) {
        this.totalMaintenanceFundUsed = totalMaintenanceFundUsed;
    }

    public int getNumberOfOwnedUnits() {
        return numberOfOwnedUnits;
    }

    public void setNumberOfOwnedUnits(int numberOfOwnedUnits) {
        this.numberOfOwnedUnits = numberOfOwnedUnits;
    }
}
