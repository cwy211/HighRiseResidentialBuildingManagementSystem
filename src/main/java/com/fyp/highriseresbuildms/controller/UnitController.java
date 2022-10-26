package com.fyp.highriseresbuildms.controller;

import com.fyp.highriseresbuildms.dto.UnitUserDto;
import com.fyp.highriseresbuildms.entity.Unit;
import com.fyp.highriseresbuildms.service.unit.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/unit")
public class UnitController {
    @Autowired
    private UnitService unitService;

    @RequestMapping(value ="/getAllUnit",
            method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('Admin','User')")
    public List<Unit> getAllUnit(){

        return unitService.getAllUnit();
    }

    @RequestMapping(value ="/getAllUnitId",
            method = RequestMethod.GET)
    @PreAuthorize("hasRole('Admin')")
    public List<String> getAllUnitId(){
        return unitService.getAllUnitId();
    }

    @RequestMapping(value ="/getAllOwnedUnitId",
            method = RequestMethod.GET)
    @PreAuthorize("hasRole('Admin')")
    public List<String> getAllOwnedUnitId(){
        return unitService.getAllOwnedUnitId();
    }

    @RequestMapping(value ="/getAllVacantUnitId",
            method = RequestMethod.GET)
    @PreAuthorize("hasRole('Admin')")
    public List<String> getAllVacantUnitId(){
        return unitService.getAllVacantUnitId();
    }

    @RequestMapping(value = "/registerUnit",
            method = RequestMethod.POST)
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity registerUnit(@RequestBody Unit unit){
        unitService.registerUnit(unit);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(value = "/updateUnit",
            method = RequestMethod.PUT)
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity updateUnit(@RequestBody Unit unit){
        unitService.updateUnit(unit);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(value = "/getUnitByUnitId",
            method = RequestMethod.GET)
    @PreAuthorize("hasRole('Admin')")
    public UnitUserDto getUnitByUnitId(@RequestParam String unitNo){
        return unitService.getUnitByUnitId(unitNo);
    }

    @RequestMapping(value = "/searchUnitByUnitId",
            method = RequestMethod.GET)
    @PreAuthorize("hasRole('Admin')")
    public List<UnitUserDto> searchUnitByUnitId(@RequestParam String unitNo){
        return unitService.searchUnitByUnitId(unitNo);
    }

    @RequestMapping(value = "/checkUnitExist",
            method = RequestMethod.GET)
    @PreAuthorize("hasRole('Admin')")
    public boolean checkUnitExist(@RequestParam String unitNo){
        return unitService.checkUnitExist(unitNo);
    }
}
