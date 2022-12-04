package com.example.projetobd.service;

import com.example.projetobd.entity.SnackOrder;
import com.example.projetobd.repository.SnackOrderRepository;
import com.example.projetobd.request.SnackOrderCreateRequest;
import org.springframework.stereotype.Service;

import java.util.Map;

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


    public Map<Long, Integer> createNewSnackOrder(SnackOrderCreateRequest snackOrderCreateRequest) {
        Map<Long, Integer> snacksIdsAndSnacksQuantity = snackOrderCreateRequest.getAllSnacksIdsAndSnacksQuantity();
        for (Map.Entry<Long, Integer> entry : snacksIdsAndSnacksQuantity.entrySet()) {
            SnackOrder snackOrder = new SnackOrder(snackService.getSnackById(entry.getKey()), entry.getValue());
            snackOrderRepository.save(snackOrder);
        }
        return snacksIdsAndSnacksQuantity;
    }

    public void deleteSnackOrderById(Long snackOrderId) {
        snackOrderRepository.deleteById(snackOrderId);
    }

    public Double calculateSnackOrderTotalPrice(Long snackOrderId) {
        SnackOrder snackOrder = snackOrderRepository.findById(snackOrderId).orElse(null);
        if (snackOrder != null) {
            return snackOrder.getSnack().getPrice() * snackOrder.getQuantity();
        }
        return null;
    }

    public Double calculateSnackOrderTotalPrice(SnackOrderCreateRequest snackOrderCreateRequest) {
        Map<Long, Integer> snacksIdsAndSnacksQuantity = snackOrderCreateRequest.getAllSnacksIdsAndSnacksQuantity();
        double totalPrice = 0.0;
        for (Map.Entry<Long, Integer> entry : snacksIdsAndSnacksQuantity.entrySet()) {
            SnackOrder snackOrder = new SnackOrder(snackService.getSnackById(entry.getKey()), entry.getValue());
            totalPrice += snackOrder.getSnack().getPrice() * snackOrder.getQuantity();
        }
        return totalPrice;
    }

    public Double calculateSnackOrderTotalPrice(Map<Long, Integer> snacksIdsAndSnacksQuantity) {
        double totalPrice = 0.0;
        for (Map.Entry<Long, Integer> entry : snacksIdsAndSnacksQuantity.entrySet()) {
            SnackOrder snackOrder = new SnackOrder(snackService.getSnackById(entry.getKey()), entry.getValue());
            totalPrice += snackOrder.getSnack().getPrice() * snackOrder.getQuantity();
        }
        return totalPrice;
    }

    public SnackOrder getSnackOrderById(Long id) {
        return snackOrderRepository.findById(id).orElse(null);
    }
}
