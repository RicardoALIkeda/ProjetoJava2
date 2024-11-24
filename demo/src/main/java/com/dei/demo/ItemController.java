package com.dei.demo;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
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

    @Value("${file.upload-dir}")
    private String uploadDir;

    @GetMapping("/api/itens/{id}")
public ResponseEntity<Item> getItemById(@PathVariable int id) {
    Item item = itemManager.getItemById(id); // Implemente o método getItemById no ItemManager
    if (item != null) {
        return ResponseEntity.ok(item);
    } else {
        return ResponseEntity.notFound().build();
    }
}
    @GetMapping
    public List<Item> getAllItems() {
        return itemManager.getItems();
    }

    @PostMapping
    public String createItem(@RequestBody String name) {
        itemManager.addItem(name);
        return "Item criado: " + name;
    }

    @PutMapping("/{id}")
    public String updateItem(@PathVariable int id, @RequestBody String name) {
        boolean updated = itemManager.updateItem(id, name);
        if (updated) {
            return "Item atualizado: " + name;
        } else {
            return "Item não encontrado";
        }
    }

    @DeleteMapping("/{id}")
    public String deleteItem(@PathVariable int id) {
        boolean deleted = itemManager.deleteItem(id);
        if (deleted) {
            return "Item deletado";
        } else {
            return "Item não encontrado";
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Falha no upload: o arquivo está vazio");
        }

        try {
            File uploadDirFile = new File(uploadDir);
        
            if (!uploadDirFile.exists()) {
                boolean created = uploadDirFile.mkdirs(); // Tenta criar o diretório
                if (!created) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                         .body("Não foi possível criar o diretório de upload");
                }
            }
        
            String filePath = uploadDirFile.getAbsolutePath() + "/" + file.getOriginalFilename();
            file.transferTo(new File(filePath));
        
            return ResponseEntity.ok("Arquivo enviado com sucesso: " + filePath);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Falha no upload do arquivo: " + e.getMessage());
        }
    }
}
