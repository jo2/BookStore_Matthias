package de.adesso.bookStore.persistence;

import de.adesso.bookStore.domain.InvoiceLineItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InvoiceLineItemRepo extends CrudRepository<InvoiceLineItem, Integer> {

    List<InvoiceLineItem> findByInvoiceId();

}
