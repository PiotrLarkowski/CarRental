package com.example.carrental.domain.Car;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Table(name="CarRentalCar")
public class Car {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
