package com.fyp.highriseresbuildms.service.facility_booking;

import com.fyp.highriseresbuildms.dao.facility.FacilityDao;
import com.fyp.highriseresbuildms.dao.facility_booking.FacilityBookingDao;
import com.fyp.highriseresbuildms.dao.user.UserDao;
import com.fyp.highriseresbuildms.dto.FacilityBookingDto;
import com.fyp.highriseresbuildms.entity.FacilityBooking;
import com.fyp.highriseresbuildms.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class FacilityBookingServiceImpl implements FacilityBookingService{
    @Autowired
    private FacilityBookingDao facilityBookingDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @Autowired
    private FacilityDao facilityDao;


    @Override
    public void makeFacilityBooking(FacilityBookingDto facilityBookingDto) {
        FacilityBooking facilityBooking = new FacilityBooking();
        facilityBooking.setFbDate(facilityBookingDto.getFbDate());
        facilityBooking.setSlot(facilityBookingDto.getSlot());
        facilityBooking.setFbFacility(facilityDao.findById(facilityBookingDto.getFbFacility()).get());
        facilityBooking.setFbUser(userDao.findById(userService.getUserName()).get());
        facilityBookingDao.save(facilityBooking);
    }

    @Override
    public List<FacilityBooking> getFacilityBookingByFacilityDate(int facilityId, LocalDate fbDate) {
        return facilityBookingDao.findFacilityBookingsByFbFacility_IdAndFbDateEquals(facilityId,fbDate);
    }

    @Override
    public List<FacilityBooking> getFacilityBookingByUser() {
        return facilityBookingDao.findFacilityBookingsByFbUser_UserId(userService.getUserName());
    }

    @Override
    public void deleteFacilityBooking(int bookingId) {
        facilityBookingDao.delete(facilityBookingDao.findById(bookingId).get());
    }

    @Override
    public List<FacilityBooking> getFacilityBookingByFacility(int facilityId) {
        return facilityBookingDao.findFacilityBookingsByFbFacility_Id(facilityId);
    }
}
