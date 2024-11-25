package com.dei.demo;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    private List<Item> items = new ArrayList<>();
    private int nextId = 1;

    public ItemManager() {
        items.add(new Item(nextId++, "Trump vence eleição"));
        items.add(new Item(nextId++, "Lula preso amanha!!!"));
        items.add(new Item(nextId++, "yakuza é preso"));
        items.add(new Item(nextId++, "maria é presa"));
        items.add(new Item(nextId++, "jose aldo veio a obito"));
        
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(String name) {
        Item newItem = new Item(nextId++, name);
        items.add(newItem);
    }

    public Item getItemById(int id) {
        for (Item item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null; // Ou lançar uma exceção se o item não for encontrado
    }   
    public List<Item> getUltimasNoticias() {
        // Retorna as 3 últimas notícias
        return items.subList(Math.max(items.size() - 3, 0), items.size());
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