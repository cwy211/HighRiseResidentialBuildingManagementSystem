package com.fyp.highriseresbuildms.controller;

import com.fyp.highriseresbuildms.entity.Announcement;
import com.fyp.highriseresbuildms.service.announcement.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/announcement")
public class AnnouncementController {
    @Autowired
    private AnnouncementService announcementService;

    @RequestMapping(value ="/getAllAnnouncement",
            method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('Admin','User')")
    public List<Announcement> getAllAnnouncement(){

        return announcementService.getAllAnnouncement();
    }

    @RequestMapping(value = "/createAnnouncement",
            method = RequestMethod.POST)
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity createAnnouncement(@RequestBody Announcement announcement){
        announcementService.createAnnouncement(announcement);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(value ="/getActiveAnnouncement",
            method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('Admin','User')")
    public List<Announcement> getActiveAnnouncement(){

        return announcementService.getActiveAnnouncement();
    }

    @RequestMapping(value = "/updateAnnouncement",
            method = RequestMethod.PUT)
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity updateAnnouncement(@RequestBody Announcement announcement){
        announcementService.updateAnnouncement(announcement);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(value = "/getAnnouncement",
            method = RequestMethod.GET)
    @PreAuthorize("hasRole('Admin')")
    public Announcement getAnnouncement(@RequestParam int id){
        return announcementService.getAnnouncement(id);
    }
}
