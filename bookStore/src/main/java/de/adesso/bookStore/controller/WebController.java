package de.adesso.bookStore.controller;

import de.adesso.bookStore.domain.Book;
import de.adesso.bookStore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
    public String createBook() {
        return "createBook";
    }

    @GetMapping("updateBook")
    public String updateBook() {
        return "updateOneBook";
    }

    @GetMapping("shoppingCart")
    public String shoppingCart() {
        return "shoppingCart";
    }

}
