package com.example.projetobd.controller.api.frontend;

import com.example.projetobd.entity.Room;
import com.example.projetobd.entity.Snack;
import com.example.projetobd.entity.Ticket;
import com.example.projetobd.request.SnackOrderCreateRequest;
import com.example.projetobd.request.SnacksAndTicketsRequest;
import com.example.projetobd.request.TicketBuyRequest;
import com.example.projetobd.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestMapping("/transactions")
@Controller
public class TransactionControllerFront {
    private final TicketService ticketService;
    private final SnackOrderService snackOrderService;
    private final SnackService snackService;
    private final RoomService roomService;
    private final SessionService sessionService;

    public TransactionControllerFront(TicketService ticketService, SnackOrderService snackOrderService, SnackService snackService, RoomService roomService, SessionService sessionService) {
        this.ticketService = ticketService;
        this.snackOrderService = snackOrderService;
        this.snackService = snackService;
        this.roomService = roomService;
        this.sessionService = sessionService;
    }

    @GetMapping("/prebuy")
    public String prebuy(Model model) {

        return "pre-buy-tickets-and-snacks";
    }


    @GetMapping("/buy")
    public String buyTicketsAndSnacksForm(Model model) {
        List<Long> snacksId = snackService.getAllSnacks().stream().map(Snack::getId).toList();
        ArrayList<Integer> snacksQuantity = new ArrayList<>(snacksId.stream().map(snackId -> 0).toList());
        SnackOrderCreateRequest snackOrderCreateRequest = new SnackOrderCreateRequest(snacksId, snacksQuantity);
        List<Room> rooms = roomService.getRooms();
        model.addAttribute("snackIdsAndQty", snackOrderCreateRequest.getAllSnacksIdsAndSnacksQuantity());
        model.addAttribute("snackService", snackService);
        model.addAttribute("ticketService", ticketService);
        model.addAttribute("snackOrderService", snackOrderService);
        model.addAttribute("roomService", roomService);
        model.addAttribute("sessionService", sessionService);
        model.addAttribute("snacksAndTicketsRequest", new SnacksAndTicketsRequest(new TicketBuyRequest(), snackOrderCreateRequest));
        model.addAttribute("rooms", rooms);
        return "buy-snacks-tickets";
    }

    @PostMapping("/buy")
    public String buyTicketsAndSnacks(SnacksAndTicketsRequest snacksAndTicketsRequest, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "buy-snacks-tickets";
        }
        List<Long> snacksId = snackService.getAllSnacks().stream().map(Snack::getId).toList();
        snacksAndTicketsRequest.setSnacksId(snacksId);
        System.out.println("Aqui");
        Ticket ticket = ticketService.buyTicket(snacksAndTicketsRequest.createTicketBuyRequest());
        Map<Long, Integer> snackOrder = snackOrderService.createNewSnackOrder(snacksAndTicketsRequest.createSnackOrderCreateRequest());
        ticketService.buyTicket(snacksAndTicketsRequest.createTicketBuyRequest());
        snacksAndTicketsRequest.setSnacksId(new ArrayList<>(snackOrder.keySet()));
        snackOrderService.createNewSnackOrder(snacksAndTicketsRequest.createSnackOrderCreateRequest());
        model.addAttribute("snackOrderService", snackOrderService);
        model.addAttribute("snackOrder", snackOrder);
        model.addAttribute("ticket", ticket);
        model.addAttribute("ticketService", ticketService);
        model.addAttribute("snackService", snackService);
        return "print-order";
    }


}
