package com.dei.demo;

import java.util.HashMap;
import java.util.Map;

public class ItemManager {

    private Map<Integer, Item> items = new HashMap<>();
    private int nextId = 1;

    public ItemManager() {
        addItem(new Item(nextId++, "Destaque Principal", "Descrição da notícia de destaque.", "IMG_1549.JPG"));
        addItem(new Item(nextId++, "Trump vence eleição", "Descrição da notícia 2.", "IMG_0871.JPG"));
        addItem(new Item(nextId++, "Lula preso amanhã!!!", "Descrição da notícia 3.", "IMG_1512.JPG"));
        addItem(new Item(nextId++, "Yakuza é preso", "Descrição da notícia 4.", "IMG_0908.jpg"));
        addItem(new Item(nextId++, "Maria é presa", "Descrição da mais lida 5.", "IMG_XXXXX.jpg"));
        addItem(new Item(nextId++, "Novo Item 6", "Descrição da mais lida 6.", "IMG_YYYYY.jpg"));
        addItem(new Item(nextId++, "Novo Item 7", "Descrição da mais lida 7.", "IMG_ZZZZZ.jpg"));
        // Adicione mais itens conforme necessário
    }

    public Map<Integer, Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        items.put(item.getId(), item);
    }

    public Item getItemById(int id) {
        return items.get(id);
    }

    // Método atualizado para aceitar imageUrl
    public boolean updateItem(int id, String newName, String newDescription, String newImageUrl) {
        Item item = items.get(id);
        if (item != null) {
            item.setName(newName);
            item.setDescription(newDescription);
            if (newImageUrl != null && !newImageUrl.isEmpty()) {
                item.setImageUrl(newImageUrl);
            }
            return true;
        }
        return false;
    }

    public boolean deleteItem(int id) {
        return items.remove(id) != null;
    }
}