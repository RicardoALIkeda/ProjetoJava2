package com.dei.demo;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    private List<String> items = new ArrayList<>();
    public ItemManager() {
        items.add("Item 1");
        items.add("Item 2");
        items.add("Item 3");
    }
    public List<String> getItems() {
        return items;
    }

    public void addItem(String item) {
        items.add(item);
    }

    public boolean updateItem(int id, String item) {
        if (id >= 0 && id < items.size()) {
            items.set(id, item);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteItem(int id) {
        if (id >= 0 && id < items.size()) {
            items.remove(id);
            return true;
        } else {
            return false;
        }
    }
}