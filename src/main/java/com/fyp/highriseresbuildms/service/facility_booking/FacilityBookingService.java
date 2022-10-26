package com.fyp.highriseresbuildms.service.facility_booking;

import com.fyp.highriseresbuildms.dto.FacilityBookingDto;
import com.fyp.highriseresbuildms.entity.FacilityBooking;

import java.time.LocalDate;
import java.util.List;

public interface FacilityBookingService {
    void makeFacilityBooking(FacilityBookingDto facilityBookingDto);
    List<FacilityBooking> getFacilityBookingByFacilityDate(int facilityId, LocalDate fbDate);
    List<FacilityBooking> getFacilityBookingByUser();
    void deleteFacilityBooking(int bookingId);
    List<FacilityBooking> getFacilityBookingByFacility(int facilityId);
}
