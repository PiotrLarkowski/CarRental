package com.example.carrental.domain.Car;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class Car {
    @Id
    @Column(nullable = false)
    private String id;

    private String mark;

    private String model;

    private String bodyType;

    private int yearOfProduction;

    private String colour;

    private int run;

    private CarStatus carStatus;

    private BigDecimal dayPrice;


}
