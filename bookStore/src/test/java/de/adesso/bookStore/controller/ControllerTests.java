package de.adesso.bookStore.controller;

import de.adesso.bookStore.service.BookService;
import de.adesso.bookStore.service.InvoiceLineItemService;
import de.adesso.bookStore.service.InvoiceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;

@WebMvcTest(WebController.class)
public class ControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private InvoiceService invoiceService;

    @MockBean
    private InvoiceLineItemService invoiceLineItemService;

    @Test
    void index() {
        //when(bookService.findAllSortedByTitle()).thenReturn();
    }

}
