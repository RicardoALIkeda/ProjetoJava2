package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Noticia;

public interface NoticiaService {
    List<Noticia> findAll();
    void save(Noticia noticia);
    void deleteById(Long id);
    Noticia findById(Long id);
}