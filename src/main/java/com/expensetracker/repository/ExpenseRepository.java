package com.expensetracker.repository;

import com.expensetracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByCategory(String category);

    List<Expense> findByNameContainingIgnoreCase(String name);

    List<Expense> findByCategoryAndDateBetween(String category, LocalDate startDate, LocalDate endDate);

    @Query("SELECT SUM(e.amount) FROM Expense e WHERE MONTH(e.date) = :month AND YEAR(e.date) = :year")
    BigDecimal getMonthlyExpenseSummary(@Param("month") int month, @Param("year") int year);

}
