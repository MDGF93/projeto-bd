package com.example.projetobd.service;

import com.example.projetobd.entity.Ticket;
import com.example.projetobd.repository.SessionRepository;
import com.example.projetobd.repository.TicketRepository;
import com.example.projetobd.request.TicketBuyRequest;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final SessionRepository sessionRepository;

    public TicketService(TicketRepository ticketRepository, SessionRepository sessionRepository) {
        this.ticketRepository = ticketRepository;
        this.sessionRepository = sessionRepository;
    }

    public void buyTicket(TicketBuyRequest ticketBuyRequest) {
        //Create a new ticket with the data from the request
        Ticket ticket = new Ticket();
        //Check if there's still room in the session
        Integer sessionCapacity = sessionRepository.findById(ticketBuyRequest.getSessionId()).get().getRoom().getCapacity();

        if (sessionCapacity > 0) {
            //If there's room, save the ticket and decrease the session capacity
            ticket.setSession(sessionRepository.findById(ticketBuyRequest.getSessionId()).get());
            ticket.setSeatNumber(ticketBuyRequest.getSeatNumber());
            ticket.setTicketType(ticketBuyRequest.getTicketType());
            ticket.setCreditCard(ticketBuyRequest.isCreditCard());
            ticketRepository.save(ticket);
            sessionRepository.findById(ticketBuyRequest.getSessionId()).get().getRoom().setCapacity(sessionCapacity - 1);
        }
        else {
            throw new RuntimeException("There are no available seats in this session");
        }
    }

    public String printTicket(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).get();
        return """
                Ticket ID: %s
                Movie: %s
                Room: %s
                Seat Number: %s
                Ticket Type: %s
                """.formatted(ticket.getId(), ticket.getSession().getMovie().getTitleBr(), ticket.getSession().getRoom().getId(), ticket.getSeatNumber(), ticket.getTicketType());
    }
}
