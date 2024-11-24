package com.dei.demo;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    private List<String> items = new ArrayList<>();
    public ItemManager() {
<<<<<<< HEAD
        items.add(new Item(nextId++, "Muito divertido, viu"));
        items.add(new Item(nextId++, "Maravilha!!!"));
        items.add(new Item(nextId++, "Nao Acredito!!!"));
=======
        items.add("Item 1");
        items.add("Item 2");
        items.add("Item 3");
>>>>>>> parent of 32e6130 (teste bao)
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