package com.example.projetobd.request;

import com.example.projetobd.entity.TicketType;
import lombok.Data;

@Data
public class TicketBuyRequest {
    private Long sessionId;
    private Integer seatNumber;
    private TicketType ticketType;
    private boolean isCreditCard;

}
