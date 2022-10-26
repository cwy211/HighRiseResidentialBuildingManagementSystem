package com.fyp.highriseresbuildms.controller;

import com.fyp.highriseresbuildms.dto.FacilityBookingDto;
import com.fyp.highriseresbuildms.entity.Facility;
import com.fyp.highriseresbuildms.entity.FacilityBooking;
import com.fyp.highriseresbuildms.service.facility.FacilityService;
import com.fyp.highriseresbuildms.service.facility_booking.FacilityBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/facility")
public class FacilityController {

    @Autowired
    private FacilityService facilityService;

    @Autowired
    private FacilityBookingService facilityBookingService;

    @RequestMapping(value = "/addFacility", method = RequestMethod.POST)
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity addFacility(@RequestBody Facility facility){
        facilityService.addFacility(facility);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(value = "/editFacility", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity editFacility(@RequestBody Facility facility){
        facilityService.editFacility(facility);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(value="/getAllFacility", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('User','Admin')")
    public List<Facility> getAllFacility(){
        return facilityService.getAllFacility();
    }

    @RequestMapping(value="/getFacilityById", method = RequestMethod.GET)
    @PreAuthorize("hasRole('Admin')")
    public Facility getFacilityById(@RequestParam int facilityId){
        return facilityService.getFacilityById(facilityId);
    }

    @RequestMapping(value="/getAllBookingFacility", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('User','Admin')")
    public List<Facility> getAllBookingFacility(){
        return facilityService.getAllBookingFacility();
    }

    @RequestMapping(value = "/makeFacilityBooking", method = RequestMethod.POST)
    @PreAuthorize("hasRole('User')")
    public ResponseEntity makeFacilityBooking(@RequestBody FacilityBookingDto facilityBookingDto){
        facilityBookingService.makeFacilityBooking(facilityBookingDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(value = "/getFacilityBookingByFacilityDate", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('User','Admin')")
    public List<FacilityBooking> getFacilityBookingByFacilityDate(@RequestParam int facilityId, @RequestParam("fbDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fbDate){
        return facilityBookingService.getFacilityBookingByFacilityDate(facilityId,fbDate);
    }

    @RequestMapping(value = "/getFacilityBookingByUser", method = RequestMethod.GET)
    @PreAuthorize("hasRole('User')")
    public List<FacilityBooking> getFacilityBookingByUser(){
        return facilityBookingService.getFacilityBookingByUser();
    }

    @RequestMapping(value = "/deleteFacilityBooking", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('User')")
    public ResponseEntity deleteFacilityBooking(@RequestParam int bookingId){
        facilityBookingService.deleteFacilityBooking(bookingId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(value = "/getFacilityBookingByFacility", method = RequestMethod.GET)
    @PreAuthorize("hasRole('Admin')")
    public List<FacilityBooking> getFacilityBookingByFacility(@RequestParam int facilityId){
        return facilityBookingService.getFacilityBookingByFacility(facilityId);
    }

}
