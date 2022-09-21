package com.example.carrental.domainDto.CarDto;

import com.example.carrental.domain.Car.CarStatus;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class CarDtoNoList {

    @NotNull
    private Long id;
    @NotNull
    private String mark;
    @NotNull
    private String model;
    @NotNull
    private String bodyType;
    @NotNull
    private int yearOfProduction;
    @NotNull
    private String colour;
    @NotNull
    private int run;
    @NotNull
    private CarStatus carStatus;
    @NotNull
    private BigDecimal dayPrice;
}
