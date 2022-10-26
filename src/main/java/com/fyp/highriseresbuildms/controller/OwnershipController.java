package com.fyp.highriseresbuildms.controller;

import com.fyp.highriseresbuildms.dto.OwnershipDto;
import com.fyp.highriseresbuildms.entity.Complaint;
import com.fyp.highriseresbuildms.entity.Ownership;
import com.fyp.highriseresbuildms.entity.Unit;
import com.fyp.highriseresbuildms.service.ownership.OwnershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ownership")
public class OwnershipController {

    @Autowired
    private OwnershipService ownershipService;

    @RequestMapping(value = "/addOwnership",
            method = RequestMethod.POST)
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity addOwnership(@RequestBody OwnershipDto ownershipDto){
        ownershipService.addOwnership(ownershipDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(value ="/getAllOwnership",
            method = RequestMethod.GET)
    @PreAuthorize("hasRole('Admin')")
    public List<Ownership> getAllOwnership(){
        return ownershipService.getAllOwnerships();
    }
}
