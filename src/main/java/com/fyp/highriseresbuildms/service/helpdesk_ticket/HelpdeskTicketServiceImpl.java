package com.fyp.highriseresbuildms.service.helpdesk_ticket;

import com.fyp.highriseresbuildms.dao.helpdesk_ticket.HelpdeskTicketDao;
import com.fyp.highriseresbuildms.entity.HelpdeskTicket;
import com.fyp.highriseresbuildms.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class HelpdeskTicketServiceImpl implements HelpdeskTicketService{
    @Autowired
    private HelpdeskTicketDao helpdeskTicketDao;

    @Autowired
    private UserService userService;

    @Override
    public void createHelpdeskTicket(HelpdeskTicket helpdeskTicket) {
        helpdeskTicket.setTicketDate(new Date());
        helpdeskTicket.setTicketUser(userService.getUser(userService.getUserName()));
        helpdeskTicket.setTicketStatus("Pending");
        helpdeskTicketDao.save(helpdeskTicket);
    }

    @Override
    public List<HelpdeskTicket> getAllHelpdeskTicket() {
        return helpdeskTicketDao.findAll();
    }

    @Override
    public List<HelpdeskTicket> getHelpdeskTicketByUser() {
        return helpdeskTicketDao.findHelpdeskTicketsByTicketUserUserId(userService.getUserName());
    }

    @Override
    public HelpdeskTicket getHelpdeskTicketById(int id) {
        return helpdeskTicketDao.findById(id).get();
    }

    @Override
    public void handleHelpdeskTicket(HelpdeskTicket helpdeskTicket) {
        HelpdeskTicket updatedHelpdeskTicket = helpdeskTicketDao.findById(helpdeskTicket.getId()).get();
        updatedHelpdeskTicket.setTicketResponse(helpdeskTicket.getTicketResponse());
        updatedHelpdeskTicket.setTicketAdmin(userService.getUser(userService.getUserName()));
        updatedHelpdeskTicket.setTicketStatus("Reviewed");
        helpdeskTicketDao.save(updatedHelpdeskTicket);
    }

    @Override
    public void processHelpdeskTicket(HelpdeskTicket helpdeskTicket) {
        HelpdeskTicket updatedHelpdeskTicket = helpdeskTicketDao.findById(helpdeskTicket.getId()).get();
        updatedHelpdeskTicket.setTicketStatus("Processing");
        helpdeskTicketDao.save(updatedHelpdeskTicket);
    }
}
