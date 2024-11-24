package br.dev.joaquim.livroapi.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.dev.joaquim.livroapi.entity.Book;
import br.dev.joaquim.livroapi.entity.BookCollection;
import br.dev.joaquim.livroapi.repository.BookCollectionRepository;
import br.dev.joaquim.livroapi.repository.BookRepository;

@RestController
@RequestMapping("/api/colecoes")
public class BookCollectionController {
    @Autowired
    private BookCollectionRepository repository;
    @Autowired
    private BookRepository bookRepo;

    @PostMapping()
    public ResponseEntity<BookCollection> create(@RequestBody BookCollection collection) {
        BookCollection newCollection = repository.save(collection);
        return new ResponseEntity<>(newCollection, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BookCollection> addBook(@RequestBody String isbn, @PathVariable long id) {
        Optional<Book> bookEntity = bookRepo.findById(isbn);
        if(!bookEntity.isPresent()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "livro não existe");

        Optional<BookCollection> collectionEntity = repository.findById(id);
        if(!collectionEntity.isPresent()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "coleção não existe");

        Book book = bookEntity.get();
        BookCollection collection = collectionEntity.get();

        collection.getBooks().add(book);
        book.setCollection(collection);

        BookCollection newCollection = repository.save(collection);
        return ResponseEntity.ok(newCollection);
    }

    @GetMapping()
    public Iterable<BookCollection> readAll() {
        return repository.findAll();
    }
}
