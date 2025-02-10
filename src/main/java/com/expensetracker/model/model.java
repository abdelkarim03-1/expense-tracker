package com.expensetracker.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data // Lombok annotation to generate getters/setters automatically
@NoArgsConstructor
@AllArgsConstructor
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Name of the expense (e.g., "Groceries")

    private BigDecimal amount; // Expense amount

    private String category; // Category (e.g., "Food", "Rent", "Shopping")

    private LocalDate date; // Date of the expense
}
