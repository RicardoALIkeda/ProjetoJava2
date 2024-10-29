package com.dei.demo;
import java.util.ArrayList;
import java.util.List;
public class ItemManager {
    private List<String> items = new ArrayList<>();
    public List<String> getAllItems() {
        return items;
    }
    public String createItem(String item) {
        items.add(item);
        return "Item criado: " + item;
    }
    public String updateItem(int id, String item) {
        if (id >= 0 && id < items.size()) {
            items.set(id, item);
            return "Item atualizado: " + item;
        } else {
            return "Item não encontrado";
        }
    }
    public String deleteItem(int id) {
        if (id >= 0 && id < items.size()) {
            String removedItem = items.remove(id);
            return "Item deletado: " + removedItem;
        } else {
            return "Item não encontrado";
        }
    }
}
