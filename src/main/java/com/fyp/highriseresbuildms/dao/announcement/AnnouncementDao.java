package com.fyp.highriseresbuildms.dao.announcement;

import com.fyp.highriseresbuildms.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnnouncementDao extends JpaRepository<Announcement,Integer> {
    List<Announcement> findAnnouncementsByStatusEqualsOrderByCreateDateDesc(String status);
    List<Announcement> findAllByOrderByCreateDateDesc();
}
