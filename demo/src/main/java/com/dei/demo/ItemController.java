package com.dei.demo;

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

    private final ItemManager itemManager = new ItemManager();

    @GetMapping
    public List<String> getAllItems() {
        return itemManager.getItems();
    }

    @PostMapping
    public String createItem(@RequestBody String item) {
        itemManager.addItem(item);
        return "Item criado: " + item;
    }

    @PutMapping("/{id}")
    public String updateItem(@PathVariable int id, @RequestBody String item) {
        boolean updated = itemManager.updateItem(id, item);
        if (updated) {
            return "Item atualizado: " + item;
        } else {
            return "Item não encontrado";
        }
    }

    @DeleteMapping("/{id}")
    public String deleteItem(@PathVariable int id) {
        boolean deleted = itemManager.deleteItem(id);
        if (deleted) {
            return "Item deletado";
        } else {
            return "Item não encontrado";
        }
    }
}