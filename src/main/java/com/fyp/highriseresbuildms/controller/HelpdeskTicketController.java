package com.fyp.highriseresbuildms.controller;

import com.fyp.highriseresbuildms.entity.HelpdeskTicket;
import com.fyp.highriseresbuildms.service.helpdesk_ticket.HelpdeskTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/helpdeskTicket")
public class HelpdeskTicketController {
    @Autowired
    private HelpdeskTicketService helpdeskTicketService;

    @RequestMapping(value = "/createHelpdeskTicket", method = RequestMethod.POST)
    @PreAuthorize("hasRole('User')")
    public ResponseEntity createHelpdeskTicket(@RequestBody HelpdeskTicket helpdeskTicket){
        helpdeskTicketService.createHelpdeskTicket(helpdeskTicket);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(value="/getAllHelpdeskTicket", method = RequestMethod.GET)
    @PreAuthorize("hasRole('Admin')")
    public List<HelpdeskTicket> getAllHelpdeskTicket(){
        return helpdeskTicketService.getAllHelpdeskTicket();
    }

    @RequestMapping(value="/getHelpdeskTicketByUser", method = RequestMethod.GET)
    @PreAuthorize("hasRole('User')")
    public List<HelpdeskTicket> getHelpdeskTicketByUser(){
        return helpdeskTicketService.getHelpdeskTicketByUser();
    }


    @RequestMapping(value="/getHelpdeskTicketById", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('User','Admin')")
    public HelpdeskTicket getHelpdeskTicketById(@RequestParam int id){
        return helpdeskTicketService.getHelpdeskTicketById(id);
    }

    @RequestMapping(value="/handleHelpdeskTicket", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity handleHelpdeskTicket(@RequestBody HelpdeskTicket helpdeskTicket){
        helpdeskTicketService.handleHelpdeskTicket(helpdeskTicket);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(value="/processHelpdeskTicket", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity processHelpdeskTicket(@RequestBody HelpdeskTicket helpdeskTicket){
        helpdeskTicketService.processHelpdeskTicket(helpdeskTicket);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
