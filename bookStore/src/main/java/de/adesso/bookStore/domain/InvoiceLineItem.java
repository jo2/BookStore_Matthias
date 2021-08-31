package de.adesso.bookStore.domain;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.UUID;

public class InvoiceLineItem {

    private int id;
    @Size(min=2, max=30)
    private String bookTitle;
    @Size(min=2, max=30)
    private String bookAuthor;
    @Min(1)
    private double bookPrice;
    @Min(0)
    @Max(100)
    private double discount;
    @Min(0)
    private int amount;
    private double summedCosts;
    private int invoiceId;

}
