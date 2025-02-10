package com.expensetracker.controller;

import com.expensetracker.model.Expense;
import com.expensetracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    // Get all expenses
    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
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

    @PutMapping("/{id}")
    public Expense updateExpense(@PathVariable Long id, @RequestBody Expense expenseDetails) {
        return expenseService.updateExpense(id, expenseDetails);
    }

    @GetMapping("/search")
    public List<Expense> searchExpenses(@RequestParam String name) {
        return expenseService.searchExpenses(name);
    }

    @GetMapping("/filter")
    public List<Expense> filterExpenses(
            @RequestParam String category,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate
    ) {
        return expenseService.filterExpenses(category, startDate, endDate);
    }

    @GetMapping
    public Page<Expense> getAllExpenses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return expenseService.getAllExpenses(pageable);
    }

    @GetMapping("/summary")
    public BigDecimal getMonthlySummary(
            @RequestParam int month,
            @RequestParam int year
    ) {
        return expenseService.getMonthlySummary(month, year);
    }


}
