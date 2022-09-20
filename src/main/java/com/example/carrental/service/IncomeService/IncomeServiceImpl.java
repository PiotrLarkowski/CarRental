package com.example.carrental.service.IncomeService;

import com.example.carrental.domain.Income.Income;
import com.example.carrental.domain.Income.IncomeException;
import com.example.carrental.repository.IncomeRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IncomeServiceImpl implements IncomesService {

    private final IncomeRepository incomeRepository;

    public IncomeServiceImpl(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }

    @Override
    public Income createIncome(BigDecimal price) {
        Income income = Income.builder()
                .oneCarIncome(price)
                .localDateTime(LocalDateTime.now())
                .build();

        incomeRepository.save(income);
        return income;
    }

    @Override
    public BigDecimal getMonthlyIncome(Month month) {
        List<Income> monthlyIncome = incomeRepository.findAll().stream()
                .filter(i -> i.getLocalDateTime().getMonth().equals(month))
                .collect(Collectors.toList());
        BigDecimal sum = BigDecimal.valueOf(0);
        for (int i = 0; i < monthlyIncome.size(); i++) {
            sum = sum.add(monthlyIncome.get(i).getOneCarIncome());
        }
        return sum;
    }

    @Override
    public List<Income> getAllIncomes() {
        return incomeRepository.findAll();
    }

    @Override
    public Income getIncomeById(Long id) {
        Long incomeId = Optional.of(id)
                .orElseThrow(() -> new IncomeException("No income found in DB"));
        return incomeRepository.findIncomeById(incomeId);
    }
}
