package com.example.carrental.service.IncomeService;

import com.example.carrental.domain.Income.Income;

import java.math.BigDecimal;
import java.time.Month;
import java.util.List;

public interface IncomesService {

    Income createIncome(BigDecimal price);

    BigDecimal getMonthlyIncome(Month month);

    List<Income> getAllIncomes();

    Income getIncomeById(Long id);

}
