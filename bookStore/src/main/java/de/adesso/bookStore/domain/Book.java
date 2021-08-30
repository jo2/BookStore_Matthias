package de.adesso.bookStore.domain;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.UUID;

public class Book {

    private int id;
    @Size(min=2, max=30)
    private String title;
    @Size(min=2, max=30)
    private String author;
    @Min(1000)
    @Max(2050)
    private int year;
    @Min(1)
    private double price;
    @Min(0)
    private int amount;

}
