package de.adesso.bookStore.controller;

import de.adesso.bookStore.domain.Book;
import de.adesso.bookStore.service.BookService;
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
        //
        model.addAttribute("book", new Book());
        //
        return "createBook";
    }

    @GetMapping("updateBook/{id}")
    public String updateBook(@PathVariable("id") final int id, final Model model) {
        Book book = bookService.findById(id);
        model.addAttribute("book", book);
        return "updateOneBook";
    }

    @GetMapping("shoppingCart")
    public String shoppingCart() {
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

}
