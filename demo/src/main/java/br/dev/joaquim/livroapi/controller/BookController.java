package br.dev.joaquim.livroapi.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.dev.joaquim.livroapi.entity.Book;
import br.dev.joaquim.livroapi.repository.BookRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/livros")
public class BookController {
    @Autowired
    private BookRepository repository;

    @PostMapping()
    public ResponseEntity<Book> create(@RequestBody Book entity) {
        if(entity.getIsbn() == null || "".equals(entity.getIsbn())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "precisa informar o ISBN");
        }

        Optional<Book> existe = repository.findById(entity.getIsbn());
        if(existe.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "já existe um livro com este ISBN");
        }

        Book newEntity = repository.save(entity);
        return new ResponseEntity<Book>(newEntity, HttpStatus.CREATED);
    }

    // rota1: /api/livros?title="voltar"   <--- retorna filtrando pelo titulo
    // rota2: /api/livros                  <--- retorna todos os livros
    @GetMapping()
    public Iterable<Book> readByTitle(@RequestParam(required = false) String title) {
        if(title == null) {
            return repository.findAll();
        }
        return repository.findByTitleContainingIgnoreCase(title);
    }

    // rota: /api/livros/123
    @GetMapping("/{isbn}")
    public ResponseEntity<Book> readById(@PathVariable String isbn) {
        Optional<Book> resultado = repository.findById(isbn);

        if(resultado.isPresent()) {
            return ResponseEntity.ok(resultado.get());
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ISBN " + isbn + " não encontrado");
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<Book> update(@RequestBody Book newBook, @PathVariable String isbn) {
        //1. Verifica se o livro existe pelo ISBN
        Optional<Book> entity = repository.findById(isbn);

        //2. Se nao existe devolve erro 404
        if(!entity.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        //3. Se existe altera objeto e salva no banco
        Book book = entity.get();
        book.setTitle(newBook.getTitle());
        book.setAuthor(newBook.getAuthor());
        book.setYear(newBook.getYear());

        //4. Devolve novo livro
        repository.save(book);
        return ResponseEntity.ok(book);
    }

    // @DeleteMapping("/{isbn}")
    // public ResponseEntity<Void> deleteBook(@PathVariable String isbn) {
    //     repository.deleteById(isbn);
    //     return ResponseEntity.noContent().build();
    // }
    
    @DeleteMapping("/{isbn}")
    public ResponseEntity<Book> deleteBook(@PathVariable String isbn) {
        //1. Verifica se o livro existe pelo ISBN
        Optional<Book> entity = repository.findById(isbn);

        //2. Se nao existe devolve erro 404
        if(!entity.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        //3. Se existe apaga o objeto do banco
        Book book = entity.get();
        repository.delete(book);
        return ResponseEntity.ok(book);
    }
}
