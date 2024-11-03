package com.dei.demo;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    private List<Item> items = new ArrayList<>();
    private int nextId = 1;

    public ItemManager() {
        items.add(new Item(nextId++, "Item 1"));
        items.add(new Item(nextId++, "Item 2"));
        items.add(new Item(nextId++, "Item 3"));
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(String name) {
        Item newItem = new Item(nextId++, name);
        items.add(newItem);
    }

    public boolean updateItem(int id, String name) {
        for (Item item : items) {
            if (item.getId() == id) {
                item.setName(name);
                return true;
            }
        }
        return false;
    }

    public boolean deleteItem(int id) {
        return items.removeIf(item -> item.getId() == id);
    }
}