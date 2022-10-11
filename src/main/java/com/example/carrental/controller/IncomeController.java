package com.example.carrental.controller;

import com.example.carrental.domain.Income.Income;
import com.example.carrental.domain.Income.IncomeException;
import com.example.carrental.service.IncomeService.IncomesService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping(path = "/income")
public class IncomeController {

    private final IncomesService incomesService;

    public IncomeController(IncomesService incomesService) {
        this.incomesService = incomesService;
    }
    @Secured({"ROLE_ADMIN"})
    @GetMapping(path = "/month/{month}")
    public BigDecimal getMonthlyIncome(@PathVariable Month month){
        return incomesService.getMonthlyIncome(month);
    }
    @Secured({"ROLE_ADMIN"})
    @GetMapping
    public List<Income> getAllIncomes() {
        return incomesService.getAllIncomes();
    }
    @Secured({"ROLE_ADMIN"})
    @GetMapping(path = "/{id}")
    public Income getIncomeById(@PathVariable Long id) throws IncomeException {
        return incomesService.getIncomeById(id);
    }
}
