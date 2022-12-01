package com.example.projetobd.request;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class SnackOrderCreateRequest {
    private List<Long> snacksId;
    private ArrayList<Integer> snacksQuantity;

    public SnackOrderCreateRequest(List<Long> snacksId, ArrayList<Integer> snacksQuantity) {
        this.snacksId = snacksId;
        this.snacksQuantity = snacksQuantity;
    }

    public Map<Long, Integer> getAllSnacksIdsAndSnacksQuantity() {
        Map<Long, Integer> snacksIdsAndSnacksQuantity = new HashMap<>();
        for (int i = 0; i < snacksId.size(); i++) {
            snacksIdsAndSnacksQuantity.put(snacksId.get(i), snacksQuantity.get(i));
        }
        return snacksIdsAndSnacksQuantity;
    }
}
