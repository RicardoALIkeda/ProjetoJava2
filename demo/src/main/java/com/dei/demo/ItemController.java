package com.dei.demo;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

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
    public String uploadImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "Falha no upload: o arquivo está vazio";
        }
    
        try {
            String uploadDir = "uploads/"; // Diretório dentro do JAR
    
            // Obter a URL do diretório
            URL uploadDirURL = getClass().getClassLoader().getResource(uploadDir);
    
            if (uploadDirURL != null) {
                // Converter a URL para um File
                File uploadDirFile = new File(uploadDirURL.getFile());
    
                // Verificar se o diretório existe e, se não, criá-lo
                if (!uploadDirFile.exists()) {
                    uploadDirFile.mkdirs();
                }
    
                // Salvar o arquivo no diretório
                String filePath = uploadDirFile.getAbsolutePath() + "/" + file.getOriginalFilename();
                file.transferTo(new File(filePath));
    
                return "Arquivo enviado com sucesso: " + filePath;
            } else {
                return "Falha no upload: diretório não encontrado";
            }
        } catch (IOException e) {
            return "Falha no upload do arquivo: " + e.getMessage();
        }
    }
}