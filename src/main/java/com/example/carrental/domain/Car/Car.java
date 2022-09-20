package com.example.carrental.domain.Car;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return yearOfProduction == car.yearOfProduction && mark.equals(car.mark) && model.equals(car.model) && bodyType.equals(car.bodyType) && colour.equals(car.colour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mark, model, bodyType, yearOfProduction, colour);
    }
}
