package de.adesso.bookStore.service;

import de.adesso.bookStore.domain.Invoice;
import de.adesso.bookStore.persistence.InvoiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    @Autowired
    InvoiceRepo invoiceRepo;

    public int findMaxId() {
        return findAll()
                .stream()
                .map(Invoice::getId)
                .max(Integer::compareTo)
                .orElse(0);
    }

    public List<Invoice> findAll() {
         return invoiceRepo.findAll();
    }

}
