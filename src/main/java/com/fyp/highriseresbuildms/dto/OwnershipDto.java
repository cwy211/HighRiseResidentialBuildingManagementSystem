package com.fyp.highriseresbuildms.dto;

import java.time.LocalDate;

public class OwnershipDto {

    private String owner;

    private String ownedUnit;

    private LocalDate startDate;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwnedUnit() {
        return ownedUnit;
    }

    public void setOwnedUnit(String ownedUnit) {
        this.ownedUnit = ownedUnit;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
}
