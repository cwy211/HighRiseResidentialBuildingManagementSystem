package com.fyp.highriseresbuildms.service.unit;

import com.fyp.highriseresbuildms.dao.unit.UnitDao;
import com.fyp.highriseresbuildms.dto.UnitUserDto;
import com.fyp.highriseresbuildms.entity.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UnitServiceImpl implements UnitService{

    @Autowired
    private UnitDao unitDao;

    @Override
    public void registerUnit(Unit unit) {
        unit.setUnitOwnedStatus("Vacant");
        unitDao.save(unit);
    }

    @Override
    public void updateUnit(Unit unit) {
        Unit originalUnit=unitDao.findById(unit.getUnitId()).get();
        originalUnit.setUnitBlock(unit.getUnitBlock());
        originalUnit.setUnitFloor(unit.getUnitFloor());
        unitDao.save(originalUnit);
    }

    @Override
    public List<Unit> getAllUnit() {
        return unitDao.findAll();
    }

    @Override
    public List<String> getAllUnitId() {
        return unitDao.getAllUnitId();
    }

    @Override
    public List<UnitUserDto> searchUnitByUnitId(String unitNo) {
        List<UnitUserDto> result= new ArrayList<>();
        if(unitDao.findById(unitNo).isPresent()){
            Unit unit = unitDao.findById(unitNo).get();
            UnitUserDto unitUserDto = new UnitUserDto();
            unitUserDto.setUnitId(unit.getUnitId());
            unitUserDto.setUnitBlock(unit.getUnitBlock());
            unitUserDto.setUnitFloor(unit.getUnitFloor());
            List<String> residentIdList = new ArrayList<>();
            unit.getUserList().stream().forEach(elem->residentIdList.add(elem.getUserId()));
            unitUserDto.setUserList(residentIdList);
            result.add(unitUserDto);
        }
        return result;
    }

    @Override
    public UnitUserDto getUnitByUnitId(String unitNo) {
            Unit unit = unitDao.findById(unitNo).get();
            UnitUserDto unitUserDto = new UnitUserDto();
            unitUserDto.setUnitId(unit.getUnitId());
            unitUserDto.setUnitBlock(unit.getUnitBlock());
            unitUserDto.setUnitFloor(unit.getUnitFloor());
            List<String> residentIdList = new ArrayList<>();
            unit.getUserList().stream().forEach(elem->residentIdList.add(elem.getUserId()));
            unitUserDto.setUserList(residentIdList);

        return unitUserDto;
    }

    @Override
    public List<String> getAllOwnedUnitId() {
        return unitDao.getAllOwnedUnitId();
    }

    @Override
    public List<String> getAllVacantUnitId() {
        return unitDao.getAllVacantUnitId();
    }

    @Override
    public boolean checkUnitExist(String unitNo) {
        Optional<Unit> unitExist= unitDao.findById(unitNo);
        return unitExist.isPresent();
    }



}
