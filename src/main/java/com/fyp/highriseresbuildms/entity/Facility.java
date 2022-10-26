package com.fyp.highriseresbuildms.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="facility")
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="facility_id")
    private int id;

    @NotNull
    @Column(name="facility_name", nullable = false, length = 20)
    private String name;

    @Column(name="facility_description")
    private String description;

    @Column(name="facility_booking_status",length = 3)
    private String bookingStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}
