package com.example.carrental.service;

import com.example.carrental.domain.Income.Income;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class IncomeTestValues {

    public static final Income exampleIncome = new Income(1L, BigDecimal.valueOf(15000), LocalDateTime.now());
}
