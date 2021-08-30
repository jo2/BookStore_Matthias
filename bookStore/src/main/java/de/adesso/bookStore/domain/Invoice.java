package de.adesso.bookStore.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class Invoice {

    private LocalDate invoiceDate;
    private LocalTime invoiceDateTime;
    private double inoviceTotal;
    private List<InvoiceLineItem> invoiceLineItems;

}
