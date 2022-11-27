package com.example.projetobd.controller.api.frontend;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class RoomRequest {
    @Min(value = 1, message = "A capacidade da sala deve ser maior que 0")
    private Integer capacity;
}
