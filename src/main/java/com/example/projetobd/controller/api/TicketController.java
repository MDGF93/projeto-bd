package com.example.projetobd.controller.api;

import com.example.projetobd.entity.Ticket;
import com.example.projetobd.request.TicketBuyRequest;
import com.example.projetobd.service.TicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/buy")
    public void buyTicket(@RequestBody TicketBuyRequest ticketBuyRequest) {
        ticketService.buyTicket(ticketBuyRequest);
    }

    @GetMapping("/{ticketId}")
    public String getTicketById(@PathVariable Long ticketId) {
        return ticketService.printTicket(ticketId);
    }

    @GetMapping
    public List<Ticket> getallTickets() {
        return ticketService.getAllTickets();
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllTickets() {
        ticketService.deleteAllTickets();
    }
}
