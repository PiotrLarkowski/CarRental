package com.example.carrental.domainDto.RentalBranchDto;

import com.example.carrental.domain.Car.Car;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class RentalBranchDto {
    private List<Car> cars = new ArrayList<>();

    @NotNull
    private String city;

    @NotNull
    private String street;

    @NotNull
    private String postCode;
}
