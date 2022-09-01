package com.example.carrental.domainDto.UserDto;

import lombok.*;

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

    //TODO wiek klienta

    private String userEMail;

    private String userAddress;

    private String userCarId;

    private String role;

    private String status;
}
