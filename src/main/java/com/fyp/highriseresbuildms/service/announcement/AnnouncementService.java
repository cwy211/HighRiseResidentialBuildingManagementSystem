package com.fyp.highriseresbuildms.service.announcement;

import com.fyp.highriseresbuildms.entity.Announcement;

import java.util.List;

public interface AnnouncementService {
    void createAnnouncement(Announcement announcement);
    void updateAnnouncement(Announcement announcement);
    Announcement getAnnouncement(int id);
    List<Announcement> getAllAnnouncement();
    List<Announcement> getActiveAnnouncement();
    void deleteAnnouncement(Announcement announcement);
}
