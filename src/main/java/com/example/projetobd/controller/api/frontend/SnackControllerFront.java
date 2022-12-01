package com.example.projetobd.controller.api.frontend;


import com.example.projetobd.entity.Snack;
import com.example.projetobd.entity.SnackOrder;
import com.example.projetobd.request.SnackCreateRequest;
import com.example.projetobd.service.SnackOrderService;
import com.example.projetobd.service.SnackService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/snacks")
public class SnackControllerFront {

    private final SnackService snackService;
    private final SnackOrderService snackOrderService;

    public SnackControllerFront(SnackService snackService, SnackOrderService snackOrderService) {
        this.snackService = snackService;
        this.snackOrderService = snackOrderService;
    }

    @GetMapping
    public String getSnacks(Model model){
        model.addAttribute("snacks", snackService.getAllSnacks());
        return "snacks";
    }

    @GetMapping("/new")
    public String addSnackForm(Model model) {
        model.addAttribute("snackCreateRequest", new SnackCreateRequest());
        return "add-snack";
    }

    @PostMapping
    public String addSnack(SnackCreateRequest snackCreateRequest, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "add-snack";
        }
        snackService.createNewSnack(snackCreateRequest);
        return "redirect:/snacks";
    }

    @GetMapping("/{snackId}/delete")
    public String deleteSnack(@PathVariable Long snackId) {
        snackService.deleteSnackById(snackId);
        return "redirect:/snacks";
    }

    @GetMapping("/{snackId}/edit")
    public String editSnackForm(@PathVariable Long snackId, Model model) {
        model.addAttribute("snack", snackService.getSnackById(snackId));
        return "edit-snack";
    }

    @PostMapping("/{snackId}/edit")
    public String editSnack(@PathVariable Long snackId, SnackCreateRequest snackCreateRequest, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "edit-snack";
        }
        snackService.updateSnack(snackId, snackCreateRequest);
        return "redirect:/snacks";
    }

    @GetMapping("/order")
    public String orderSnacksForm(Model model){
        //Create a map that will contain all the snacks id and the quantity of each one
        List<Long> snacksId = snackService.getAllSnacks().stream().map(Snack::getId).toList();
        List<Integer> snacksQuantity = snacksId.stream().map(snackId -> 0).toList();

        model.addAttribute("allSnacks", snackService.getAllSnacks());
        model.addAttribute("snackService", snackService);
        model.addAttribute("snacksId", snacksId);
        model.addAttribute("snacksQuantity", snacksQuantity);
        return "order-snacks";
    }

    @PostMapping("/order")
    public String orderSnacks(SnackOrder snackOrder, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "order-snacks";
        }
        snackOrderService.createNewSnackOrder(snackOrder);
        return "redirect:/snacks";
    }

}
