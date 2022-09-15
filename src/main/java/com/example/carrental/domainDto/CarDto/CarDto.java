package com.example.carrental.domainDto.CarDto;

import com.example.carrental.domain.Car.CarStatus;
import com.example.carrental.domain.RentalOffice.CarRentalOffice;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class CarDto {

    private Long rentalBranchId;
    private String mark;
    private String model;
    private String bodyType;
    private int yearOfProduction;
    private String colour;
    private int run;
    private CarStatus carStatus;
    private BigDecimal dayPrice;

}
