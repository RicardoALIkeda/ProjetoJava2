package com.dei.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private List<String> items = new ArrayList<>();

    @GetMapping
    public List<String> getAllItems() {
        return items;
    }

    @PostMapping
    public String createItem(@RequestBody String item) {
        items.add(item);
        return "Item criado: " + item;
    }

    @PutMapping("/{id}")
    public String updateItem(@PathVariable int id, @RequestBody String item) {
        if (id >= 0 && id < items.size()) {
            items.set(id, item);
            return "Item atualizado: " + item;
        } else {
            return "Item não encontrado";
        }
    }

    @DeleteMapping("/{id}")
    public String deleteItem(@PathVariable int id) {
        if (id >= 0 && id < items.size()) {
            String removedItem = items.remove(id);
            return "Item deletado: " + removedItem;
        } else {
            return "Item não encontrado";
        }
    }
}
