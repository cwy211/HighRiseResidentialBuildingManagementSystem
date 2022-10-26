package com.fyp.highriseresbuildms.service.complaint;

import com.fyp.highriseresbuildms.entity.Complaint;

import java.util.List;

public interface ComplaintService {
    Complaint sentimentAnalysis(Complaint complaint);
    List<String> POSAnalysis(String lemmaTextString);
    void createComplaint(Complaint complaint);
    Complaint categoryAnalysis(Complaint complaint, List<String> processedComplaint);
    String lemmaAnalysis(Complaint complaint);
    List<Complaint> getAllComplaint();
    List<Complaint> getComplaintByUserName();
    Complaint getComplaintById(int id);
    void handleComplaint(Complaint complaint);
    void processComplaintStatus(Complaint complaint);
    List<Complaint> getAllComplaintSentimentAsc();
    List<Complaint> getAllComplaintMaintenancePriorityAsc();

}
