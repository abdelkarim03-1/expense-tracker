package com.expensetracker.service;

import com.expensetracker.model.Expense;
import com.expensetracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    // Get all expenses
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    // Get expense by ID
    public Optional<Expense> getExpenseById(Long id) {
        return expenseRepository.findById(id);
    }

    // Add new expense
    public Expense createExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    // Delete expense by ID
    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

    public List<Expense> searchExpenses(String name) {
        return expenseRepository.findByNameContainingIgnoreCase(name);
    }

    public Expense updateExpense(Long id, Expense expenseDetails) {
        Optional<Expense> optionalExpense = expenseRepository.findById(id);
        if (optionalExpense.isPresent()) {
            Expense expense = optionalExpense.get();
            expense.setName(expenseDetails.getName());
            expense.setAmount(expenseDetails.getAmount());
            expense.setCategory(expenseDetails.getCategory());
            expense.setDate(expenseDetails.getDate());
            return expenseRepository.save(expense);
        } else {
            throw new RuntimeException("Expense not found with id " + id);
        }
    }

    public List<Expense> filterExpenses(String category, LocalDate startDate, LocalDate endDate) {
        return expenseRepository.findByCategoryAndDateBetween(category, startDate, endDate);
    }

    public Page<Expense> getAllExpenses(Pageable pageable) {
        return (Page<Expense>) expenseRepository.findAll(pageable);
    }

    public BigDecimal getMonthlySummary(int month, int year) {
        return expenseRepository.getMonthlyExpenseSummary(month, year);
    }


}
