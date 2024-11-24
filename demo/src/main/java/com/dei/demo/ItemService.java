package com.dei.demo;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Arrays;

@Service
public class ItemService {

    public List<String> getItems() {
        // Simulação de itens, você pode substituir por lógica real
        return Arrays.asList("Item1", "Item2", "Item3");
    }
}