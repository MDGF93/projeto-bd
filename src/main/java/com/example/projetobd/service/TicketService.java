package com.example.projetobd.service;

import com.example.projetobd.entity.Session;
import com.example.projetobd.entity.Ticket;
import com.example.projetobd.entity.TicketType;
import com.example.projetobd.repository.SessionRepository;
import com.example.projetobd.repository.TicketRepository;
import com.example.projetobd.request.TicketBuyRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final SessionRepository sessionRepository;

    public TicketService(TicketRepository ticketRepository, SessionRepository sessionRepository) {
        this.ticketRepository = ticketRepository;
        this.sessionRepository = sessionRepository;
    }

    public Ticket buyTicket(TicketBuyRequest ticketBuyRequest) {
        //Check if there's available seats in the session
        Session movieSession = sessionRepository.findById(ticketBuyRequest.getSessionId()).get();
        if (movieSession.getAvailableSeats() == 0){
            throw new RuntimeException("There are no available seats in this session");
        }
        Ticket ticket = new Ticket();
        ticket.setSession(movieSession);
        ticket.setSeatNumber(movieSession.getRoom().getCapacity() - movieSession.getAvailableSeats() + 1);
        ticket.setTicketType(ticketBuyRequest.getTicketType());
        ticket.setCreditCard(ticketBuyRequest.isCreditCard());

        //Reduce the available seats in the session
        movieSession.setAvailableSeats((movieSession.getAvailableSeats() - 1));
        sessionRepository.save(movieSession);
        ticketRepository.save(ticket);
        return ticket;
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

    public Ticket getTicketById(Long ticketId) {
        return ticketRepository.findById(ticketId).get();
    }

    public List<TicketType> getTicketTypes() {
        return Arrays.asList(TicketType.values());
    }

    public double getTicketPrice(LocalDate date, TicketType ticketType, boolean isCreditCard) {
        double price = 0;
        //get day of the week from date
        int dayOfWeek = date.getDayOfWeek().getValue();
        //check if it's a weekend, if it's a weekend base price should be 25.00, else it should be 20.00. ADULT pay full price, CHILD pay 25% of the price, STUDENT and SENIOR pay 50% of the price, FLAMENGUISTA pay 0% of the price.
        if (dayOfWeek == 6 || dayOfWeek == 7) {
            if (ticketType == TicketType.ADULT) {
                price = 25.00;
            }
            else if (ticketType == TicketType.CHILD) {
                price = 25.00 * 0.25;
            }
            else if (ticketType == TicketType.STUDENT || ticketType == TicketType.SENIOR) {
                price = 25.00 * 0.5;
            }
            else if (ticketType == TicketType.FLAMENGUISTA) {
                price = 0;
            }
        }
        else {
            if (ticketType == TicketType.ADULT) {
                price = 20.00;
            }
            else if (ticketType == TicketType.CHILD) {
                price = 20.00 * 0.25;
            }
            else if (ticketType == TicketType.STUDENT || ticketType == TicketType.SENIOR) {
                price = 20.00 * 0.5;
            }
            else if (ticketType == TicketType.FLAMENGUISTA) {
                price = 0;
            }
        }
        if (isCreditCard) {
            return price * 1.1;
        }
        return price;
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public void deleteAllTickets() {
        //set all fields to null
        for (Ticket ticket : ticketRepository.findAll()) {
            ticket.setSession(null);
            ticket.setTicketType(null);
            ticket.setCreditCard(false);
            ticketRepository.save(ticket);
        }
    }
}
