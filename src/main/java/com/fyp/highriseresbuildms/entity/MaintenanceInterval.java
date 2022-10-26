package com.fyp.highriseresbuildms.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="maintenance_interval")
public class MaintenanceInterval {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="mi_id")
    private int id;

    @Column(name="mi_interval",length = 10)
    private String interval;

    @ManyToOne
    @JoinColumn(name="mi_facility")
    private Facility miFacility;

    @Column(name="mi_time")
    private LocalTime time;

    @Column(name="mi_start_date")
    private LocalDate startDate;

    @Column(name="mi_end_date")
    private LocalDate endDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public Facility getMiFacility() {
        return miFacility;
    }

    public void setMiFacility(Facility miFacility) {
        this.miFacility = miFacility;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
