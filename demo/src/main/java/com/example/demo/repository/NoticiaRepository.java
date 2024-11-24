package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Noticia;

@Repository
public interface NoticiaRepository extends JpaRepository<Noticia, Long> {
    // findAll method is already provided by JpaRepository
}