package de.adesso.bookStore.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Invoice {

    @Id
    private int id;
    private LocalDate invoiceDate;
    private LocalTime invoiceDateTime;
    private double invoiceTotal;

}
