package com.fyp.highriseresbuildms.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="ownership")
public class Ownership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ownership_id")
    private int id;

    @ManyToOne
    @JoinColumn(name="ownership_owner")
    private User owner;

    @OneToOne
    @JoinColumn(name="ownership_unit")
    private Unit ownedUnit;

    @Column(name="ownership_start_date")
    private LocalDate startDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Unit getOwnedUnit() {
        return ownedUnit;
    }

    public void setOwnedUnit(Unit ownedUnit) {
        this.ownedUnit = ownedUnit;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
}
