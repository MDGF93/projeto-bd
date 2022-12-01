package com.example.projetobd.service;

import com.example.projetobd.entity.Transaction;
import com.example.projetobd.repository.TransactionRepository;
import com.example.projetobd.request.TicketAndSnacksRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final TicketService ticketService;
    private final SnackService snackService;

    public TransactionService(TransactionRepository transactionRepository, TicketService ticketService, SnackService snackService) {
        this.transactionRepository = transactionRepository;
        this.ticketService = ticketService;
        this.snackService = snackService;
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }

    public void createNewTransaction(TicketAndSnacksRequest ticketAndSnacksRequest) {
        Transaction transaction = new Transaction();
        transaction.setSnacks(new ArrayList<>());
        transaction.setTicket(ticketService.getTicketById(ticketAndSnacksRequest.getTicketId()));

    }
}
