package de.adesso.bookStore.service;

import de.adesso.bookStore.domain.Book;
import de.adesso.bookStore.persistence.BookRepo;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@NoArgsConstructor
@Setter
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    public List<Book> findAll() {
        return bookRepo.findAll();
    }

    public Book findById(int id) {
        return bookRepo.findById(id);
    }

    public void save(Book book) {
        bookRepo.save(book);
    }

    public void update(Book book) {
        //Book book = bookRepo.findById(book.);
        //book
    }

}
