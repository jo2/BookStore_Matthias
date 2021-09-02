package de.adesso.bookStore.service;

import de.adesso.bookStore.domain.Book;
import de.adesso.bookStore.domain.Invoice;
import de.adesso.bookStore.domain.InvoiceLineItem;
import de.adesso.bookStore.persistence.InvoiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Invoice> findBoughtInvoicesSortedByDateTime() {
        return findBoughtInvoices()
                .stream()
                .sorted(Comparator.comparing(Invoice::getInvoiceDate).reversed())
                .collect(Collectors.toList());
    }

    public List<Invoice> findBoughtInvoices() {
        List<Invoice> invoices = invoiceRepo.findByIdLessThan(findMaxId());
        int maxId = findMaxId();
        Invoice invoice = findById(maxId);
        if (invoice != null && invoiceLineItemService.findRecentItems().isEmpty()) {
            return invoiceRepo.findAll();
        }
        return invoices;
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
