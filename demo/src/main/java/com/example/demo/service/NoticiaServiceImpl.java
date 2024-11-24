package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Noticia;
import com.example.demo.repository.NoticiaRepository;

@Service
public class NoticiaServiceImpl implements NoticiaService {

    @Autowired
    private NoticiaRepository noticiaRepository;

    @Override
    public List<Noticia> findAll() {
        return noticiaRepository.findAll();
    }

    @Override
    public void save(Noticia noticia) {
        noticiaRepository.save(noticia);
    }

    @Override
    public void deleteById(Long id) {
        noticiaRepository.deleteById(id);
    }

    @Override
    public Noticia findById(Long id) {
        return noticiaRepository.findById(id).orElse(null);
    }
}