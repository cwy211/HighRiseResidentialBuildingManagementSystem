package com.fyp.highriseresbuildms.dto;

import java.time.LocalDate;

public class FacilityBookingDto {
    private LocalDate fbDate;

    private int fbFacility;

    private int slot;

    public LocalDate getFbDate() {
        return fbDate;
    }

    public void setFbDate(LocalDate fbDate) {
        this.fbDate = fbDate;
    }

    public int getFbFacility() {
        return fbFacility;
    }

    public void setFbFacility(int fbFacility) {
        this.fbFacility = fbFacility;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }
}
