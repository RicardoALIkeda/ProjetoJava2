package com.example.demo.controller;

import java.security.Principal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Noticia;
import com.example.demo.service.NoticiaService;

@Controller
@RequestMapping("/noticias")
public class NoticiaController {
    @Autowired
    private NoticiaService noticiaService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("noticias", noticiaService.findAll());
        return "noticias/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("noticia", new Noticia());
        return "noticias/form";
    }

    @PostMapping
    public String create(@ModelAttribute Noticia noticia, Principal principal) {
        noticia.setAuthor(principal.getName());
        noticia.setPublishedAt(LocalDateTime.now());
        noticiaService.save(noticia);
        return "redirect:/noticias";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("noticia", noticiaService.findById(id));
        return "noticias/form";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id, @ModelAttribute Noticia noticia) {
        noticiaService.save(noticia);
        return "redirect:/noticias";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        noticiaService.deleteById(id);
        return "redirect:/noticias";
    }
}