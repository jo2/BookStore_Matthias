package de.adesso.bookStore.persistence;

import de.adesso.bookStore.domain.Book;
import de.adesso.bookStore.domain.Invoice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface InvoiceRepo extends CrudRepository<Invoice, Integer> {

    List<Invoice> findAll();

}
