package com.example.carrental.domainDto.UserDto;

import com.example.carrental.domain.RentalOffice.CarRentalOffice;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class UserDto {

    private String userLogin;

    private String userPassword;

    private String userName;

    private String userLastName;

    private String userEMail;

    private String userAddress;

    private Long userCarId;

    private String role;

    private String status;

    private List<CarRentalOffice> rentalOfficeList;
}
