package com.example.projetobd.controller.api;

import com.example.projetobd.entity.Transaction;
import com.example.projetobd.request.TicketAndSnacksRequest;
import com.example.projetobd.service.SnackService;
import com.example.projetobd.service.TicketService;
import com.example.projetobd.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    private final TransactionService transactionService;
    private final TicketService ticketService;
    private final SnackService snackService;

    public TransactionController(TransactionService transactionService, TicketService ticketService, SnackService snackService) {
        this.transactionService = transactionService;
        this.ticketService = ticketService;
        this.snackService = snackService;
    }

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable Long id) {
        return transactionService.getTransactionById(id);
    }

    @PostMapping
    public void createNewTransaction(@RequestBody TicketAndSnacksRequest ticketAndSnacksRequest) {
        transactionService.createNewTransaction(ticketAndSnacksRequest);
    }

}
