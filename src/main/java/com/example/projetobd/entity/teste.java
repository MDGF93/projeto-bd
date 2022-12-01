package com.example.projetobd.entity;

import java.util.ArrayList;
import java.util.List;

public class teste {

    public static void main(String[] args) {

        //Create a Map of String and Integer
        List<Long> snacksId = List.of(1L, 2L, 3L);
        ArrayList<Integer> snacksQuantity = new ArrayList<>(snacksId.stream().map(snackId -> 0).toList());
        //Iterate over the list snacksId and add +1 to each element of snacksQuantity
        for (int i = 0; i < snacksId.size(); i++) {
            System.out.println(snacksId.get(i));
            snacksQuantity.set(i, snacksQuantity.get(i) + 1);
        }
        System.out.println(snacksQuantity.get(0));


    }
}
