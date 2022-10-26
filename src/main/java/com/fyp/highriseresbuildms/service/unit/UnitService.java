package com.fyp.highriseresbuildms.service.unit;

import com.fyp.highriseresbuildms.dto.UnitUserDto;
import com.fyp.highriseresbuildms.entity.Unit;

import java.util.List;

public interface UnitService {
    void registerUnit(Unit unit);
    void updateUnit(Unit unit);
    List<Unit> getAllUnit();
    List<String> getAllUnitId();
    List<UnitUserDto> searchUnitByUnitId(String unitNo);
    UnitUserDto getUnitByUnitId(String unitNo);
    List<String> getAllOwnedUnitId();
    List<String> getAllVacantUnitId();
    boolean checkUnitExist(String unitNo);
}
