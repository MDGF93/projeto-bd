package com.example.projetobd.request;

import com.example.projetobd.entity.TicketType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SnacksAndTicketsRequest {
    private Long sessionId;
    private TicketType ticketType;
    private boolean isCreditCard;
    private List<Long> snacksId;
    private ArrayList<Integer> snacksQuantity;
    public SnacksAndTicketsRequest(TicketBuyRequest ticketBuyRequest, SnackOrderCreateRequest snackOrderCreateRequest) {
        this.sessionId = ticketBuyRequest.getSessionId();
        this.ticketType = ticketBuyRequest.getTicketType();
        this.isCreditCard = ticketBuyRequest.isCreditCard();
        this.snacksId = snackOrderCreateRequest.getSnacksId();
        this.snacksQuantity = snackOrderCreateRequest.getSnacksQuantity();
    }
    public SnacksAndTicketsRequest(List<Long> snacksId) {
        this.snacksId = snacksId;
    }
    public SnacksAndTicketsRequest() {
    }

    public SnackOrderCreateRequest createSnackOrderCreateRequest() {
        return new SnackOrderCreateRequest(snacksId, snacksQuantity);
    }

    public TicketBuyRequest createTicketBuyRequest() {
        return new TicketBuyRequest(sessionId, ticketType, isCreditCard);
    }
}
