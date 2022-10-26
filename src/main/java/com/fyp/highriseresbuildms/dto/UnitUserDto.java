package com.fyp.highriseresbuildms.dto;


import java.util.ArrayList;
import java.util.List;

public class UnitUserDto {
    private String unitId;

    private String unitBlock;

    private String unitFloor;

    private List<String> userList = new ArrayList<>();

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

    public List<String> getUserList() {
        return userList;
    }

    public void setUserList(List<String> userList) {
        this.userList = userList;
    }
}
