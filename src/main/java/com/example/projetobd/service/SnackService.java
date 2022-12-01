package com.example.projetobd.service;


import com.example.projetobd.entity.Snack;
import com.example.projetobd.repository.SnackRepository;
import com.example.projetobd.request.SnackCreateRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SnackService {

    private final SnackRepository snackRepository;

    public SnackService(SnackRepository snackRepository) {
        this.snackRepository = snackRepository;
    }
    public Snack getSnackById(Long id) {
        return snackRepository.findById(id).orElse(null);
    }

    public void createNewSnack(SnackCreateRequest snackCreateRequest) {
        Snack snack = new Snack();
        snack.setName(snackCreateRequest.getName());
        snack.setPrice(snackCreateRequest.getPrice());
        snackRepository.save(snack);
    }

    public List<Snack> getAllSnacks() {
        return snackRepository.findAll();
    }

    public void deleteSnackById(Long id) {
        snackRepository.deleteById(id);
    }

    public void updateSnack(Long snackId, SnackCreateRequest snackCreateRequest) {
        Snack snack = snackRepository.findById(snackId).orElse(null);
        snack.setName(snackCreateRequest.getName());
        snack.setPrice(snackCreateRequest.getPrice());
        snackRepository.save(snack);
    }
}
