package com.fyp.highriseresbuildms.dao.helpdesk_ticket;

import com.fyp.highriseresbuildms.entity.HelpdeskTicket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HelpdeskTicketDao extends JpaRepository<HelpdeskTicket,Integer> {
    List<HelpdeskTicket> findHelpdeskTicketsByTicketUserUserId(String userId);
}
