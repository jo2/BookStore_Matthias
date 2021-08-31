package de.adesso.bookStore.persistence;

import de.adesso.bookStore.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends CrudRepository<Book, Integer> {

    @Override
    Book save(Book book);

    Book findById(int id);

    @Override
    List<Book> findAll();

}
