package com.dei.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NewsController {

    private final NewsService newsService;
    private final ItemService itemService;

    public NewsController(NewsService newsService, ItemService itemService) {
        this.newsService = newsService;
        this.itemService = itemService;
    }

    @GetMapping("/news")
    public String getNews() {
        return newsService.getLatestNews();
    }

    @GetMapping("/items")
    public List<String> getItems() {
        return itemService.getItems();
    }

    @GetMapping("/news-with-items")
    public String getNewsWithItems() {
        String news = newsService.getLatestNews();
        List<String> items = itemService.getItems();
        return news + "\nItems: " + String.join(", ", items);
    }
}