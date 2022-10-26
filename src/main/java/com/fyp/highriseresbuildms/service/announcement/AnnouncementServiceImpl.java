package com.fyp.highriseresbuildms.service.announcement;

import com.fyp.highriseresbuildms.dao.announcement.AnnouncementDao;
import com.fyp.highriseresbuildms.dao.user.UserDao;
import com.fyp.highriseresbuildms.entity.Announcement;
import com.fyp.highriseresbuildms.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class AnnouncementServiceImpl implements AnnouncementService{
    @Autowired
    private AnnouncementDao announcementDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @Override
    public void createAnnouncement(Announcement announcement) {
        String currentUserName=userService.getUserName();
        if(currentUserName!=null){
            announcement.setUser(userDao.findById(currentUserName).get());
        }
        announcement.setCreateDate(new Date());
        announcement.setStatus("Active");
        announcementDao.save(announcement);
    }

    @Override
    public void updateAnnouncement(Announcement updatedAnnouncement) {
        Announcement announcement = announcementDao.findById(updatedAnnouncement.getId()).get();
        announcement.setTitle(updatedAnnouncement.getTitle());
        announcement.setDescription(updatedAnnouncement.getDescription());
        announcement.setStatus(updatedAnnouncement.getStatus());
        announcementDao.save(announcement);
    }

    @Override
    public Announcement getAnnouncement(int id) {
        return announcementDao.findById(id).get();
    }

    @Override
    public List<Announcement> getAllAnnouncement() {
        return announcementDao.findAllByOrderByCreateDateDesc();
    }

    @Override
    public List<Announcement> getActiveAnnouncement() {
        return announcementDao.findAnnouncementsByStatusEqualsOrderByCreateDateDesc("Active");
    }

    @Override
    public void deleteAnnouncement(Announcement announcement) {
        announcement.setStatus("Inactive");
        announcementDao.save(announcement);
    }
}
