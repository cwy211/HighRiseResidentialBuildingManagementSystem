package com.fyp.highriseresbuildms.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="complaint")
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="complaint_id")
    private int id;

    @Column(name="complaint_title", length = 100)
    private String complaintTitle;

    @Column(name="complaint_desc")
    private String complaintDescription;

    @ManyToOne
    @JoinColumn(name="complaint_user")
    private User complaintUser;

    @Column(name="complaint_sentiment_value")
    private double sentimentValue;

    @ManyToOne
    @JoinColumn(name="complaint_category")
    private ComplaintCategory complaintCategory;

    @Column(name="complaint_status",length = 10)
    private String complaintStatus;

    @Column(name="complaint_response")
    private String complaintResponse;

    @ManyToOne
    @JoinColumn(name="complaint_admin")
    private User complaintAdmin;

    @Column(name="complaint_date_time")
    private LocalDateTime createdDateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComplaintTitle() {
        return complaintTitle;
    }

    public void setComplaintTitle(String complaintTitle) {
        this.complaintTitle = complaintTitle;
    }

    public String getComplaintDescription() {
        return complaintDescription;
    }

    public void setComplaintDescription(String complaintDescription) {
        this.complaintDescription = complaintDescription;
    }

    public User getComplaintUser() {
        return complaintUser;
    }

    public void setComplaintUser(User complaintUser) {
        this.complaintUser = complaintUser;
    }

    public double getSentimentValue() {
        return sentimentValue;
    }

    public void setSentimentValue(double sentimentValue) {
        this.sentimentValue = sentimentValue;
    }

    public ComplaintCategory getComplaintCategory() {
        return complaintCategory;
    }

    public void setComplaintCategory(ComplaintCategory complaintCategory) {
        this.complaintCategory = complaintCategory;
    }

    public String getComplaintStatus() {
        return complaintStatus;
    }

    public void setComplaintStatus(String complaintStatus) {
        this.complaintStatus = complaintStatus;
    }

    public String getComplaintResponse() {
        return complaintResponse;
    }

    public void setComplaintResponse(String complaintResponse) {
        this.complaintResponse = complaintResponse;
    }

    public User getComplaintAdmin() {
        return complaintAdmin;
    }

    public void setComplaintAdmin(User complaintAdmin) {
        this.complaintAdmin = complaintAdmin;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    @Override
    public String toString() {
        return "Complaint{" +
                "id=" + id +
                ", complaintTitle='" + complaintTitle + '\'' +
                ", complaintDescription='" + complaintDescription + '\'' +
                ", complaintUser=" + complaintUser +
                ", sentimentValue=" + sentimentValue +
                ", complaintCategory=" + complaintCategory +
                ", complaintStatus='" + complaintStatus + '\'' +
                ", complaintResponse='" + complaintResponse + '\'' +
                ", complaintAdmin=" + complaintAdmin +
                '}';
    }
}
