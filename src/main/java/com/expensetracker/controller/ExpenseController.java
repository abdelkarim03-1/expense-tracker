package com.expensetracker.controller;

import com.expensetracker.model.Expense;
import com.expensetracker.service.ExpenseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    // Get expense by ID
    @GetMapping("/{id}")
    public Optional<Expense> getExpenseById(@PathVariable Long id) {
        return expenseService.getExpenseById(id);
    }

    // Create a new expense
    @PostMapping
    public Expense createExpense(@RequestBody Expense expense) {
        return expenseService.createExpense(expense);
    }

    // Delete an expense
    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
    }

    // Update an expense
    @PutMapping("/{id}")
    public Expense updateExpense(@PathVariable Long id, @RequestBody Expense expenseDetails) {
        return expenseService.updateExpense(id, expenseDetails);
    }

    // Search expenses by name
    @GetMapping("/search")
    public List<Expense> searchExpenses(@RequestParam String name) {
        return expenseService.searchExpenses(name);
    }

    // Filter expenses by category & date range
    @GetMapping("/filter")
    public List<Expense> filterExpenses(
            @RequestParam String category,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate
    ) {
        return expenseService.filterExpenses(category, startDate, endDate);
    }

    // Get all expenses with pagination (secured)
    @GetMapping
    @PreAuthorize("hasAuthority('USER')")
    public Page<Expense> getAllExpenses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return (Page<Expense>) expenseService.getAllExpenses(pageable);
    }

    // Get monthly expense summary
    @GetMapping("/summary")
    public BigDecimal getMonthlySummary(
            @RequestParam int month,
            @RequestParam int year
    ) {
        return expenseService.getMonthlySummary(month, year);
    }
}
