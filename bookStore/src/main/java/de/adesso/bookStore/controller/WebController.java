package de.adesso.bookStore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String index() {
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
