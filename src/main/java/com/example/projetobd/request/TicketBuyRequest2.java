package com.example.projetobd.request;

import com.example.projetobd.entity.TicketType;
import lombok.Data;

import java.util.List;

@Data
public class TicketBuyRequest2 {
    private Long sessionId;

    private Integer ticketQuantity;
    private List<TicketType> ticketType;
    private List<Boolean> isCreditCard;

    public TicketBuyRequest2(Long sessionId, Integer ticketQuantity, List<TicketType> ticketType, List<Boolean> isCreditCard) {
        this.sessionId = sessionId;
        this.ticketQuantity = ticketQuantity;
        this.ticketType = ticketType;
        this.isCreditCard = isCreditCard;
    }

    public TicketBuyRequest2() {
    }

}
