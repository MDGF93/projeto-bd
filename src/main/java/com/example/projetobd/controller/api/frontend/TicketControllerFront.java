package com.example.projetobd.controller.api.frontend;

import com.example.projetobd.entity.Ticket;
import com.example.projetobd.request.TicketBuyRequest;
import com.example.projetobd.service.RoomService;
import com.example.projetobd.service.SessionService;
import com.example.projetobd.service.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tickets")
public class TicketControllerFront {

    private final TicketService ticketService;
    private final SessionService sessionService;
    private final RoomService roomService;

    public TicketControllerFront(TicketService ticketService, SessionService sessionService, RoomService roomService) {
        this.ticketService = ticketService;
        this.sessionService = sessionService;
        this.roomService = roomService;
    }

    @GetMapping("/{ticketId}")
    public String getTicket(@PathVariable Long ticketId, Model model) {
        model.addAttribute("ticketService", ticketService);
        model.addAttribute("ticket", ticketService.getTicketById(ticketId));
        return "ticket-print";
    }

    @GetMapping("/buy")
    public String buyTicketForm(Model model) {
        model.addAttribute("ticketBuyRequest", new TicketBuyRequest());
        model.addAttribute("ticketService", ticketService);
        model.addAttribute("sessionService", sessionService);
        model.addAttribute("roomService", roomService);
        return "buy-ticket";
    }

    @PostMapping("/buy")
    public String buyTicket(TicketBuyRequest ticketBuyRequest, Model model) {
        Ticket ticket = ticketService.buyTicket(ticketBuyRequest);
        model.addAttribute("ticketService", ticketService);
        model.addAttribute("ticket", ticket);
        return "ticket-print";
    }

}
