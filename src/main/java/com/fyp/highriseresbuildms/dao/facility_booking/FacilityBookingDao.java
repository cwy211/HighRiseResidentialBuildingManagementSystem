package com.fyp.highriseresbuildms.dao.facility_booking;

import com.fyp.highriseresbuildms.entity.FacilityBooking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface FacilityBookingDao extends JpaRepository<FacilityBooking,Integer> {
    List<FacilityBooking> findFacilityBookingsByFbFacility_IdAndFbDateEquals(int facilityId, LocalDate fbDate);
    List<FacilityBooking> findFacilityBookingsByFbUser_UserId(String useId);
    List<FacilityBooking> findFacilityBookingsByFbFacility_Id(int facilityId);
}
