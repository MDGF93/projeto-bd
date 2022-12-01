package com.example.projetobd.request;


import com.example.projetobd.entity.SnackOrder;
import lombok.Data;

import java.util.List;

@Data
public class TicketAndSnacksRequest {
    private Long ticketId;
    private List<SnackOrder> snackOrder;
}
