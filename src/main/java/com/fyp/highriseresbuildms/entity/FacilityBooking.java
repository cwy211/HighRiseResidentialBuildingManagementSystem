package com.fyp.highriseresbuildms.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="facility_booking")
public class FacilityBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="fb_id")
    private int id;

    @Column(name="fb_date")
    private LocalDate fbDate;

    @ManyToOne
    @JoinColumn(name="fb_facility")
    private Facility fbFacility;

    @Column(name="fb_slot")
    private int slot;

    @ManyToOne
    @JoinColumn(name="fb_user")
    private User fbUser;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFbDate() {
        return fbDate;
    }

    public void setFbDate(LocalDate fbDate) {
        this.fbDate = fbDate;
    }

    public Facility getFbFacility() {
        return fbFacility;
    }

    public void setFbFacility(Facility fbFacility) {
        this.fbFacility = fbFacility;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public User getFbUser() {
        return fbUser;
    }

    public void setFbUser(User fbUser) {
        this.fbUser = fbUser;
    }
}
