package com.fyp.highriseresbuildms.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="facility_maintenance")
public class FacilityMaintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="fm_id")
    private int id;

    @Column(name="fm_date")
    private LocalDate fmDate;

    @Column(name="fm_time")
    private LocalTime fmTime;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="fm_facility")
    private Facility fmFacility;

    @Column(name="fm_type", length = 20)
    private String type;

    @Column(name="fm_description")
    private String description;

    @Column(name="fm_status",length = 10)
    private String status;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="fm_admin")
    private User adminIncharged;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFmDate() {
        return fmDate;
    }

    public void setFmDate(LocalDate fmDate) {
        this.fmDate = fmDate;
    }

    public LocalTime getFmTime() {
        return fmTime;
    }

    public void setFmTime(LocalTime fmTime) {
        this.fmTime = fmTime;
    }

    public Facility getFmFacility() {
        return fmFacility;
    }

    public void setFmFacility(Facility fmFacility) {
        this.fmFacility = fmFacility;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getAdminIncharged() {
        return adminIncharged;
    }

    public void setAdminIncharged(User adminIncharged) {
        this.adminIncharged = adminIncharged;
    }
}
