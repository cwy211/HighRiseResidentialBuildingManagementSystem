package com.fyp.highriseresbuildms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name="admin_details")
public class AdminDetails extends User{
    @Column(name="admin_first_name", length=50)
    private String adminFirstName;

    @Column(name="admin_last_name", length=50)
    private String adminLastName;

    @Column(name="admin_gender", length=1, columnDefinition = "char")
    private String adminGender;

    @Column(name="admin_contact",length=20)
    private String adminContact;

    public String getAdminFirstName() {
        return adminFirstName;
    }

    public void setAdminFirstName(String adminFirstName) {
        this.adminFirstName = adminFirstName;
    }

    public String getAdminLastName() {
        return adminLastName;
    }

    public void setAdminLastName(String adminLastName) {
        this.adminLastName = adminLastName;
    }

    public String getAdminGender() {
        return adminGender;
    }

    public void setAdminGender(String adminGender) {
        this.adminGender = adminGender;
    }

    public String getAdminContact() {
        return adminContact;
    }

    public void setAdminContact(String adminContact) {
        this.adminContact = adminContact;
    }
}
