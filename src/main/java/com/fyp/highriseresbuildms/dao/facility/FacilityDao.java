package com.fyp.highriseresbuildms.dao.facility;

import com.fyp.highriseresbuildms.entity.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacilityDao extends JpaRepository<Facility,Integer> {
    List<Facility> findFacilitiesByBookingStatusEquals(String status);
}
