package com.dei.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
@Component
public class ItemManager {

    // Armazenamento de itens
    private Map<Integer, Item> items = new HashMap<>();
    private int nextId = 1;

    // Construtor que inicializa alguns itens
    public ItemManager() {
        addItem("Trump vence eleição", "Descricao1" ,"/img/trump.jpg");
        addItem("Lula preso amanhã!!!","Descricao2" , "/img/lula.jpg");
        addItem("Yakuza é preso", "Descricao3" ,"/img/yakuza.jpg");
        addItem("Maria é presa","Descricao4" , "/img/maria.jpg");
        addItem("Jose Aldo veio a óbito","Descricao5" , "/img/josealdo.jpg");
    }

    // Retorna todos os itens armazenados
    public Map<Integer, Item> getItems() {
        return items;
    }

    // Adiciona um novo item
    public void addItem(String name, String description, String imageUrl ) {
        Item newItem = new Item(nextId, name, description, imageUrl);
        items.put(nextId++, newItem);  // Inserindo no mapa com o próximo ID
    }

    // Obtém um item pelo seu ID
    public Item getItemById(int id) {
        return items.get(id);
    }

    // Método de atualização do item (opcional, se necessário)
    public void updateItem(int id, String name, String descricao , String imageUrl) {
        Item item = items.get(id);
        if (item != null) {
            item.setName(name);
            item.setDescription(descricao);
            item.setImageUrl(imageUrl);
        }
    }

    // Método de exclusão de item (opcional, se necessário)
    public void deleteItem(int id) {
        items.remove(id);
    }
}
