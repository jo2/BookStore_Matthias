package de.adesso.bookStore.service;

import de.adesso.bookStore.domain.Invoice;
import de.adesso.bookStore.persistence.InvoiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    InvoiceRepo invoiceRepo;

    @Autowired
    InvoiceLineItemService invoiceLineItemService;

    public int findMaxId() {
        return findAll()
                .stream()
                .map(Invoice::getId)
                .max(Integer::compareTo)
                .orElse(0);
    }

    public void deleteRecentInvoice() {
        int maxId = findMaxId();
        Invoice invoice = findById(maxId);
        if (invoice == null) {
            return;
        }
        invoiceRepo.delete(invoice);
    }

    public Invoice findById(int id) {
        return invoiceRepo.findById(id).orElse(null);
    }
    public void buy() {
        int maxId = findMaxId();
        Invoice invoice = findById(maxId);
        if (invoice == null) {
            return;
        }
        invoice.setInvoiceDate(LocalDate.now());
        invoice.setInvoiceDateTime(LocalTime.now());
        invoice.setInvoiceTotal(invoiceLineItemService.calculateSum(invoiceLineItemService.findRecentItems()));
        invoiceRepo.save(invoice);
    }

    public List<Invoice> findAll() {
         return invoiceRepo.findAll();
    }

}
