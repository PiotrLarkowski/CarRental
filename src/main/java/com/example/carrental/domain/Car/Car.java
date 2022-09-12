package com.example.carrental.domain.Car;

import com.example.carrental.domain.RentalOffice.CarRentalOffice;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


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

    private String mark;

    private String model;

    private String bodyType;

    private int yearOfProduction;

    private String colour;

    private int run;

    private CarStatus carStatus;

    private BigDecimal dayPrice;

    @OneToMany(mappedBy = "car")
    private List<CarRentalOffice> rentalOfficeList;

}
