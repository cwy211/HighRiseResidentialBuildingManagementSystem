package com.fyp.highriseresbuildms.service.helpdesk_ticket;

import com.fyp.highriseresbuildms.entity.HelpdeskTicket;

import java.util.List;

public interface HelpdeskTicketService {
    void createHelpdeskTicket(HelpdeskTicket helpdeskTicket);
    List<HelpdeskTicket> getAllHelpdeskTicket();
    List<HelpdeskTicket> getHelpdeskTicketByUser();
    HelpdeskTicket getHelpdeskTicketById(int id);
    void handleHelpdeskTicket(HelpdeskTicket helpdeskTicket);
    void processHelpdeskTicket(HelpdeskTicket helpdeskTicket);
}
