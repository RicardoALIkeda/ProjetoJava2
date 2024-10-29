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
@RequestMapping("/api")
public class DemoController {

    private List<String> items = new ArrayList<>();

    @GetMapping("/items")
    public List<String> getItems() {
        return items;
    }

    @PostMapping("/items")
    public void addItem(@RequestBody String item) {
        items.add(item);
    }

    @PutMapping("/items/{index}")
    public void updateItem(@PathVariable int index, @RequestBody String item) {
        items.set(index, item);
    }

    @DeleteMapping("/items/{index}")
    public void deleteItem(@PathVariable int index) {
        items.remove(index);
    }
}