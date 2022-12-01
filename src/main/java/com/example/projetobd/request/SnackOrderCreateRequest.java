package com.example.projetobd.request;

import lombok.Data;

import java.util.Map;

@Data
public class SnackOrderCreateRequest {
    private Map<Long, Integer> snacks;

    public SnackOrderCreateRequest() {
    }

    public SnackOrderCreateRequest(Map<Long, Integer> snacks) {
        this.snacks = snacks;
    }
}
