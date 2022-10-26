package com.fyp.highriseresbuildms.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="helpdesk_ticket")
public class HelpdeskTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ticket_id")
    private int id;

    @Column(name="ticket_title", length=50)
    private String title;

    @Column(name="ticket_description")
    private String ticketDescription;

    @ManyToOne
    @JoinColumn(name="ticket_user")
    private User ticketUser;

    @Column(name="ticket_status", length = 10)
    private String ticketStatus;

    @Column(name="ticket_response", length = 255)
    private String ticketResponse;

    @ManyToOne
    @JoinColumn(name="ticket_admin")
    private User ticketAdmin;

    @Column(name="ticket_create_date")
    private Date ticketDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTicketDescription() {
        return ticketDescription;
    }

    public void setTicketDescription(String ticketDescription) {
        this.ticketDescription = ticketDescription;
    }

    public User getTicketUser() {
        return ticketUser;
    }

    public void setTicketUser(User ticketUser) {
        this.ticketUser = ticketUser;
    }

    public String getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public String getTicketResponse() {
        return ticketResponse;
    }

    public void setTicketResponse(String ticketResponse) {
        this.ticketResponse = ticketResponse;
    }

    public User getTicketAdmin() {
        return ticketAdmin;
    }

    public void setTicketAdmin(User ticketAdmin) {
        this.ticketAdmin = ticketAdmin;
    }

    public Date getTicketDate() {
        return ticketDate;
    }

    public void setTicketDate(Date ticketDate) {
        this.ticketDate = ticketDate;
    }
}
