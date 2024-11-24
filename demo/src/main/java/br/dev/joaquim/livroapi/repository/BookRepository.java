package br.dev.joaquim.livroapi.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import br.dev.joaquim.livroapi.entity.Book;

public interface BookRepository extends CrudRepository<Book, String> {
    public List<Book> findByTitleContainingIgnoreCase(String title);
}
