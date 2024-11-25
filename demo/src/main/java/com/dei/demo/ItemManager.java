package com.dei.demo;

import java.util.HashMap;
import java.util.Map;

public class ItemManager {

    private Map<Integer, Item> items = new HashMap<>();
    private int nextId = 1;

    public ItemManager() {
        addItem(new Item(nextId++, "Brasil vence copa do mundo", "Descrição da notícia de destaque.", "gabigol.jpg"));
        addItem(new Item(nextId++, "Trump vence eleição", "Descrição da notícia 2.", "trump.jpg"));
        addItem(new Item(nextId++, "Lula preso amanhã!!!", "Descrição da notícia 3.", "lula.jpg"));
        addItem(new Item(nextId++, "Yakuza é preso", "Descrição da notícia 4.", "IMG_0887.JPG"));
        addItem(new Item(nextId++, "Maria é presa", "Descrição da mais lida 5.", "saporrquimem.jpg"));
        addItem(new Item(nextId++, "Romario joga bola", "Descrição da mais lida 6.", "bope.jpg"));
        addItem(new Item(nextId++, "Davy Jones joga games", "Descrição da mais lida 7.", "IMG_0908.jpg"));
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