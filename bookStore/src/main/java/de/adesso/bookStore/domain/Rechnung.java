package de.adesso.bookStore.domain;

import java.util.Date;
import java.util.List;

public class Rechnung {

    private Date invoiceDate;
    private int inoviceTotal;
    private List<Rechnungsposition> invoiceLineItems;

}
