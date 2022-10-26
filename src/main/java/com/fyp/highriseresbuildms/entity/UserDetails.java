package com.fyp.highriseresbuildms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="user_details")
public class UserDetails extends User{

    @Column(name="user_first_name", length=50)
    private String userFirstName;

    @Column(name="user_last_name", length=50)
    private String userLastName;

    @Column(name="user_gender", length=1, columnDefinition = "char")
    private String userGender;

    @Column(name="user_contact", length=20)
    private String userContact;

    @ManyToOne
    @JoinColumn(name="user_unit")
    private Unit userUnit;

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getUserContact() {
        return userContact;
    }

    public void setUserContact(String userContact) {
        this.userContact = userContact;
    }

    public Unit getUserUnit() {
        return userUnit;
    }

    public void setUserUnit(Unit userUnit) {
        this.userUnit = userUnit;
    }
}
