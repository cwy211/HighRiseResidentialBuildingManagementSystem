package com.fyp.highriseresbuildms.service.facility;

import com.fyp.highriseresbuildms.entity.Facility;

import java.util.List;

public interface FacilityService {
    void addFacility(Facility facility);
    void editFacility(Facility facility);
    List<Facility> getAllFacility();
    List<Facility> getAllBookingFacility();
    Facility getFacilityById(int facilityId);
    void initFacility();
}
