package com.example.projetobd.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
public class SnackCreateRequest {

    @NotEmpty
    private String name;

    @NotEmpty
    @Min(0)
    private Double price;
}
