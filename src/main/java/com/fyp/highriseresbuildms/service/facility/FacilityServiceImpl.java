package com.fyp.highriseresbuildms.service.facility;

import com.fyp.highriseresbuildms.dao.facility.FacilityDao;
import com.fyp.highriseresbuildms.entity.Facility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FacilityServiceImpl implements FacilityService{
    @Autowired
    private FacilityDao facilityDao;

    @Override
    public void addFacility(Facility facility) {
        facilityDao.save(facility);
    }

    @Override
    public void editFacility(Facility facility) {
        Facility updatedFacility = facilityDao.findById(facility.getId()).get();
        updatedFacility.setBookingStatus(facility.getBookingStatus());
        updatedFacility.setDescription(facility.getDescription());
        updatedFacility.setName(facility.getName());
        facilityDao.save(updatedFacility);
    }

    @Override
    public List<Facility> getAllFacility() {
        return facilityDao.findAll();
    }

    @Override
    public List<Facility> getAllBookingFacility() {
        return facilityDao.findFacilitiesByBookingStatusEquals("Yes");
    }

    @Override
    public Facility getFacilityById(int facilityId) {
        return facilityDao.findById(facilityId).get();
    }

    @Override
    public void initFacility() {
        Facility badmintonCourt = new Facility();
        badmintonCourt.setName("Badminton Court");
        badmintonCourt.setDescription("Standard badminton court at level 7");
        badmintonCourt.setBookingStatus("Yes");
        facilityDao.save(badmintonCourt);

        Facility basketballCourt = new Facility();
        basketballCourt.setName("Basketball Court");
        basketballCourt.setDescription("NBA Standard basketball court at level 7");
        basketballCourt.setBookingStatus("Yes");
        facilityDao.save(basketballCourt);
    }
}
