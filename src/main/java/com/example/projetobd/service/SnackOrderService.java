package com.example.projetobd.service;

import com.example.projetobd.entity.SnackOrder;
import com.example.projetobd.repository.SnackOrderRepository;
import org.springframework.stereotype.Service;

@Service
public class SnackOrderService {

    private final SnackService snackService;
    private final SnackOrderRepository snackOrderRepository;

    public SnackOrderService(SnackService snackService, SnackOrderRepository snackOrderRepository) {
        this.snackService = snackService;
        this.snackOrderRepository = snackOrderRepository;
    }
    public void createNewSnackOrder(SnackOrder snackOrder) {
        if (snackOrder.getSnack() != null) {
            snackOrder.setSnack(snackService.getSnackById(snackOrder.getSnack().getId()));
            snackOrderRepository.save(snackOrder);
        }
    }

    public SnackOrder getSnackOrderById(Long id) {
        return snackOrderRepository.findById(id).orElse(null);
    }
}
