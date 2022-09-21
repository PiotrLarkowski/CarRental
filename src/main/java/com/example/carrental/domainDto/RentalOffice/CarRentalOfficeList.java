package com.example.carrental.domainDto.RentalOffice;

import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CarRentalOfficeList {

    @NotNull
    String userName;


    @NotNull
    Long carId;

    private LocalDateTime localDateTimeOfRent;

    private LocalDateTime getLocalDateTimeOfReturn;
}
