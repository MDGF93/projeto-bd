package com.example.projetobd.controller.api;

import com.example.projetobd.entity.Snack;
import com.example.projetobd.request.SnackCreateRequest;
import com.example.projetobd.service.SnackService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/snacks")
public class SnackController {

    private final SnackService snackService;

    public SnackController(SnackService snackService) {
        this.snackService = snackService;
    }

    @GetMapping
    public Iterable<Snack> getAllSnacks() {
        return snackService.getAllSnacks();
    }

    @GetMapping("/{id}")
    public Snack getSnackById(@PathVariable Long id) {
        return snackService.getSnackById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteSnackById(@PathVariable Long id) {
        snackService.deleteSnackById(id);
    }

    @PostMapping
    public void createNewSnack(@RequestBody SnackCreateRequest snackCreateRequest) {
        snackService.createNewSnack(snackCreateRequest);
    }

}
