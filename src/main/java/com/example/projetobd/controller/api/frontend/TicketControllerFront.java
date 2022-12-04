package com.example.projetobd.controller.api.frontend;

import com.example.projetobd.request.TicketBuyRequest;
import com.example.projetobd.service.RoomService;
import com.example.projetobd.service.SessionService;
import com.example.projetobd.service.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

}
