package com.fyp.highriseresbuildms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="unit")
public class Unit {
    @Id
    @Column(name="unit_id", length=5)
    private String unitId;

    @Column(name="unit_block", length=1, columnDefinition="char")
    private String unitBlock;

    @Column(name="unit_floor", length=2)
    private String unitFloor;

    @Column(name="unit_owned_status", length=6)
    private String unitOwnedStatus;

    @OneToMany(mappedBy="userUnit",fetch
            = FetchType.LAZY)
    @JsonIgnore
    private List<UserDetails> userList = new ArrayList<>();

    @OneToOne(mappedBy = "ownedUnit")
    @JsonIgnore
    private Ownership ownership;

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getUnitBlock() {
        return unitBlock;
    }

    public void setUnitBlock(String unitBlock) {
        this.unitBlock = unitBlock;
    }

    public String getUnitFloor() {
        return unitFloor;
    }

    public void setUnitFloor(String unitFloor) {
        this.unitFloor = unitFloor;
    }

    public List<UserDetails> getUserList() {
        return userList;
    }

    public void setUserList(List<UserDetails> userList) {
        this.userList = userList;
    }

    public String getUnitOwnedStatus() {
        return unitOwnedStatus;
    }

    public void setUnitOwnedStatus(String unitOwnedStatus) {
        this.unitOwnedStatus = unitOwnedStatus;
    }

    public Ownership getOwnership() {
        return ownership;
    }

    public void setOwnership(Ownership ownership) {
        this.ownership = ownership;
    }
}
