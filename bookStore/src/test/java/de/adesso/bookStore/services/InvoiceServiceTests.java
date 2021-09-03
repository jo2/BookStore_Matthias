package de.adesso.bookStore.services;

import de.adesso.bookStore.domain.Book;
import de.adesso.bookStore.domain.Invoice;
import de.adesso.bookStore.persistence.InvoiceRepo;
import de.adesso.bookStore.service.BookService;
import de.adesso.bookStore.service.InvoiceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InvoiceServiceTests {

    @InjectMocks
    private InvoiceService invoiceService;

    @Mock
    private InvoiceRepo invoiceRepo;

    @Test
    void testFindMaxId() {
        Invoice invoice1 = new Invoice();
        invoice1.setId(1);
        Invoice invoice2 = new Invoice();
        invoice2.setId(5);
        Invoice invoice3 = new Invoice();
        invoice3.setId(3);
        List<Invoice> invoices = List.of(invoice1, invoice2, invoice3);
        when(invoiceRepo.findAll()).thenReturn(invoices);

        int maxId = invoiceService.findMaxId();

        assertThat(maxId).isEqualTo(5);
    }

    @Test
    void testFindAll() {
        Invoice invoice1 = new Invoice();
        Invoice invoice2 = new Invoice();
        Invoice invoice3 = new Invoice();
        List<Invoice> invoices = List.of(invoice1, invoice2, invoice3);
        when(invoiceRepo.findAll()).thenReturn(invoices);

        List<Invoice> foundInvoices = invoiceService.findAll();

        assertThat(foundInvoices).isEqualTo(List.of(invoice1, invoice2, invoice3));
    }

}
