package com.example.projetobd.request;

import com.example.projetobd.entity.TicketType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SnacksAndTicketsRequest2 {
    private Long sessionId;
    private List<TicketType> ticketType;
    private List<Boolean> isCreditCard;
    private Integer ticketQuantity;

    private List<Long> snacksId;
    private ArrayList<Integer> snacksQuantity;

    public SnacksAndTicketsRequest2(TicketBuyRequest2 ticketBuyRequest, SnackOrderCreateRequest snackOrderCreateRequest) {
        this.sessionId = ticketBuyRequest.getSessionId();
        this.ticketType = ticketBuyRequest.getTicketType();
        this.isCreditCard = ticketBuyRequest.getIsCreditCard();
        this.ticketQuantity = ticketBuyRequest.getTicketQuantity();
        this.snacksId = snackOrderCreateRequest.getSnacksId();
        this.snacksQuantity = snackOrderCreateRequest.getSnacksQuantity();
    }

    public SnacksAndTicketsRequest2(Integer ticketQuantity){
        this.ticketQuantity = ticketQuantity;
    }
}
