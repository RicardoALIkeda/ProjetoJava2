package com.dei.demo;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "*") // Ajuste conforme necessário
public class ItemController {

    private final ItemManager itemManager = new ItemManager();

    @Value("${file.upload-dir}")
    private String uploadDir;

    @GetMapping
    public Map<Integer, Item> getAllItems() {
        return itemManager.getItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable int id) {
        Item item = itemManager.getItemById(id);
        if (item != null) {
            return ResponseEntity.ok(item);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Método PUT atualizado para aceitar JSON
    @PutMapping("/{id}")
    public ResponseEntity<String> updateItem(
            @PathVariable int id,
            @RequestBody Item updatedItem) {
        
        // Caso o imageUrl não seja enviado, não atualizar
        String imageUrl = updatedItem.getImageUrl();

        boolean updated = itemManager.updateItem(id, updatedItem.getName(), updatedItem.getDescription(), imageUrl);
        if (updated) {
            return ResponseEntity.ok("Item atualizado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item não encontrado.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable int id) {
        boolean deleted = itemManager.deleteItem(id);
        if (deleted) {
            return ResponseEntity.ok("Item deletado");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item não encontrado");
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
                boolean created = uploadDirFile.mkdirs();
                if (!created) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("Não foi possível criar o diretório de upload");
                }
            }

            String filePath = uploadDirFile.getAbsolutePath() + "/" + file.getOriginalFilename();
            file.transferTo(new File(filePath));

            return ResponseEntity.ok("/Img/" + file.getOriginalFilename()); // Retorna o path da imagem
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Falha no upload do arquivo: " + e.getMessage());
        }
    }
}