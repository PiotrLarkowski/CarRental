package com.example.carrental.domainDto.RentalOffice;

import com.example.carrental.domain.Car.Car;
import com.example.carrental.domain.User.CarRentalUser;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CarRentalOfficeDto {

    @NotNull
    private CarRentalUser user;

    @NotNull
    private Car car;

    private LocalDateTime localDateTimeOfRent;

    private LocalDateTime getLocalDateTimeOfReturn;
}