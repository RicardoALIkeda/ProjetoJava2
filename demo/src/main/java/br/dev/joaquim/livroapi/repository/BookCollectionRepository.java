package br.dev.joaquim.livroapi.repository;

import org.springframework.data.repository.CrudRepository;
import br.dev.joaquim.livroapi.entity.BookCollection;

public interface BookCollectionRepository extends CrudRepository<BookCollection, Long> {

}
