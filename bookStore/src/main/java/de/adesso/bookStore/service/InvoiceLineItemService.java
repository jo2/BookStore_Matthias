package de.adesso.bookStore.service;

import de.adesso.bookStore.domain.Book;
import de.adesso.bookStore.domain.Invoice;
import de.adesso.bookStore.domain.InvoiceLineItem;
import de.adesso.bookStore.persistence.InvoiceLineItemRepo;
import de.adesso.bookStore.persistence.InvoiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceLineItemService {

    @Autowired
    InvoiceLineItemRepo invoiceLineItemRepo;

    @Autowired
    InvoiceService invoiceService;

    @Autowired
    InvoiceRepo invoiceRepo;

    @Autowired
    BookService bookService;

    public List<InvoiceLineItem> findRecentItems() {
        return invoiceLineItemRepo.findByBought(false);
    }

    public void fillShoppingCart(int bookId) {
        Book book = bookService.findById(bookId);
        InvoiceLineItem item = new InvoiceLineItem();
        item.setBookTitle(book.getTitle());
        item.setBookAuthor(book.getAuthor());
        item.setBookPrice(book.getPrice());
        item.setDiscount(0.0);
        item.setAmount(1);
        item.setSummedCosts(item.getBookPrice() * item.getAmount());
        item.setBought(false);

        int numberOfItemsInCart = findRecentItems().size();
        if (numberOfItemsInCart < 1) {
            Invoice invoice = new Invoice();
            invoice.setInvoiceTotal(0);
            invoiceRepo.save(invoice);
        }
        item.setInvoiceId(invoiceService.findMaxId());
        invoiceLineItemRepo.save(item);
    }

    public double calculateSum(List<InvoiceLineItem> items) {
        items.forEach(item -> item.setSummedCosts(item.getAmount()*item.getBookPrice()*(1-(item.getDiscount()/100.0))));
        return items
                .stream()
                .map(InvoiceLineItem::getSummedCosts)
                .mapToDouble(Double::intValue)
                .sum();
    }

    public void clearCart() {
        invoiceLineItemRepo.deleteByBought(false);
        invoiceService.deleteRecentInvoice();
    }

    public void buy() {
        List<Book> updatedBooks = new ArrayList<>();
        /*
        findRecentItems().forEach(item -> {
            Book book = bookService.findByTitleAndAuthor(item.getBookTitle(), item.getBookAuthor());
            if (book.getAmount() - item.getAmount() < 0) {
                return;
            }
            book.setAmount(book.getAmount()- item.getAmount());
            updatedBooks.add(book);
        });

         */
        /*
        for (Book updatedBook : updatedBooks) {
            if (updatedBook.getAmount() < 0) {
                return;
            }
        }

         */

        for (InvoiceLineItem item : findRecentItems()) {
            Book book = bookService.findByTitleAndAuthor(item.getBookTitle(), item.getBookAuthor());
            if (book.getAmount() - item.getAmount() < 0) {
                return;
            }
            book.setAmount(book.getAmount()- item.getAmount());
            updatedBooks.add(book);
        }


        System.out.println("Test");
        invoiceService.buy();
        findRecentItems().forEach(item -> {
            item.setBought(true);
            invoiceLineItemRepo.save(item);
        });
        updatedBooks.forEach(book -> {
            bookService.save(book);
        });
    }

    public List<InvoiceLineItem> findItemsByInvoice(Invoice invoice) {
        return invoiceLineItemRepo.findByInvoiceId(invoice.getId());
    }

}
