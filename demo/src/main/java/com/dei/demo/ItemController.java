package com.dei.demo;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemManager itemManager = new ItemManager();

    // Obter todos os itens
    @GetMapping
    public ResponseEntity<Map<Integer, Item>> getAllItems() {
        Map<Integer, Item> items = itemManager.getItems();
        return ResponseEntity.ok(items);
    }

    // Criar um novo item
    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        // Aqui você pode salvar a imagem no servidor
        String filename = file.getOriginalFilename();
        String filePath = "uploads/" + filename;
        File destinationFile = new File(filePath);
        file.transferTo(destinationFile);
    
        // Retornar a URL da imagem salva
        return ResponseEntity.ok("Imagem salva com sucesso! URL: " + filePath);
    }


    // Obter item por ID
    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable int id) {
        Item item = itemManager.getItemById(id);
        if (item != null) {
            return ResponseEntity.ok(item);
        }
        return ResponseEntity.notFound().build();
    }

    // Atualizar item por ID
    @PutMapping("/{id}")
   public ResponseEntity<String> updateItem(@PathVariable int id, @RequestBody Item updatedItem) {
    Item item = itemManager.getItemById(id);
    if (item != null) {
        item.setName(updatedItem.getName());
        item.setDescription(updatedItem.getDescription());
        item.setImageUrl(updatedItem.getImageUrl());
        return ResponseEntity.ok("Item atualizado com sucesso.");
    }
    return ResponseEntity.notFound().build();
}
    // Deletar item por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable int id) {
        Item item = itemManager.getItemById(id);
        if (item != null) {
            itemManager.getItems().remove(id);
            return ResponseEntity.ok("Item deletado");
        }
        return ResponseEntity.notFound().build();
    }
}
