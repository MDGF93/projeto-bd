package com.example.projetobd.controller.api;

import com.example.projetobd.request.TicketBuyRequest;
import com.example.projetobd.service.TicketService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
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

}
