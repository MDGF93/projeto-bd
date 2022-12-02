package com.example.projetobd.request;

import com.example.projetobd.entity.TicketType;
import lombok.Data;

@Data
public class TicketBuyRequest {
    private Long sessionId;
    private TicketType ticketType;
    private boolean isCreditCard;

    public TicketBuyRequest(Long sessionId, TicketType ticketType, boolean isCreditCard) {
        this.sessionId = sessionId;
        this.ticketType = ticketType;
        this.isCreditCard = isCreditCard;
    }

    public TicketBuyRequest() {
    }

}
