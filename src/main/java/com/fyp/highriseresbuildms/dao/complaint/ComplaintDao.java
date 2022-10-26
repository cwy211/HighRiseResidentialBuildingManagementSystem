package com.fyp.highriseresbuildms.dao.complaint;

import com.fyp.highriseresbuildms.entity.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComplaintDao extends JpaRepository<Complaint,Integer> {

    List<Complaint> findComplaintsByComplaintUser_UserId(String userId);
    List<Complaint> findAllByOrderBySentimentValueAsc();
    List<Complaint> findAllByOrderByComplaintCategory_RankAsc();

}
