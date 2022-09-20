package com.example.carrental.service.IncomeService;

import com.example.carrental.repository.IncomeRepository;
import com.example.carrental.service.IncomeTestValues;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class IncomeServiceImplTest {
    @Mock
    private IncomeRepository incomeRepository;

    @Test
    public void shouldReturnSumOfRentedCars(){
        //given

        IncomeServiceImpl incomeService = new IncomeServiceImpl(incomeRepository);
        Mockito.when(incomeRepository.findAll()).thenReturn(new ArrayList<>(Arrays.asList(IncomeTestValues.exampleIncome,IncomeTestValues.exampleIncome)));

        //when

        BigDecimal resultSum = incomeService.getMonthlyIncome(LocalDateTime.now().getMonth());

        //then
        assertEquals(resultSum,(IncomeTestValues.exampleIncome.getOneCarIncome().multiply(BigDecimal.valueOf(2))));
    }

}