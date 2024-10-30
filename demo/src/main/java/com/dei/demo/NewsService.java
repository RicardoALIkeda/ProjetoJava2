package com.dei.demo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NewsService {

    private final RestTemplate restTemplate;

    @Value("${newsapi.key}")
    private String apiKey;

    public NewsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getLatestNews() {
        String url = "https://newsapi.org/v2/top-headlines?country=us&apiKey=" + apiKey;
        return restTemplate.getForObject(url, String.class);
    }
}