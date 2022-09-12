package com.example.carrental.domainDto.RentalOffice;

import com.example.carrental.domain.Car.Car;
import com.example.carrental.domain.User.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CarRentalOfficeDto {

    @ManyToOne
    private User user;

    @ManyToOne
    private Car car;

    private LocalDateTime localDateTimeOfRent;

    private LocalDateTime getLocalDateTimeOfReturn;
}