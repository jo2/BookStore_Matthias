package de.adesso.bookStore.controller;

import de.adesso.bookStore.domain.Book;
import de.adesso.bookStore.domain.InvoiceLineItem;
import de.adesso.bookStore.service.BookService;
import de.adesso.bookStore.service.InvoiceLineItemService;
import de.adesso.bookStore.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class WebController {

    @Autowired
    BookService bookService;

    @Autowired
    InvoiceService invoiceService;

    @Autowired
    InvoiceLineItemService invoiceLineItemService;

    @GetMapping("/")
    public String index(final Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "index";
    }

    @GetMapping("/accounting")
    public String accounting() {
        return "accounting";
    }

    @GetMapping("/createBook")
    public String createBook(Model model) {
        model.addAttribute("book", new Book());
        return "createBook";
    }

    @GetMapping("updateBook/{id}")
    public String updateBook(@PathVariable("id") final int id, final Model model) {
        Book book = bookService.findById(id);
        model.addAttribute("book", book);
        return "updateOneBook";
    }

    @GetMapping("shoppingCart")
    public String shoppingCart(final Model model) {
        List<InvoiceLineItem> items = invoiceLineItemService.findRecentItems();
        model.addAttribute("items", items);
        return "shoppingCart";
    }

    @PostMapping("updateBook/{id}")
    public String updateBook(@PathVariable("id") final int id, @ModelAttribute final Book changedBook) {
        bookService.update(changedBook, id);
        return "redirect:/";
    }

    @PostMapping("/createBook")
    public String createBook(@ModelAttribute final Book book, final Model model) {
        model.addAttribute("book", book);
        bookService.save(book);
        return "redirect:/";
    }

    @PostMapping("deleteBook/{id}")
    public String deleteBook(@PathVariable("id") final int id) {
        bookService.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("shoppingCart/{bookId}")
    public String shoppingCart(@PathVariable("bookId") final int bookId) {
        invoiceLineItemService.fillShoppingCart(bookId);
        return "redirect:/";
    }

}
