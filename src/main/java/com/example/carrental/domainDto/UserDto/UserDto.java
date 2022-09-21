package com.example.carrental.domainDto.UserDto;

import lombok.*;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class UserDto {

    @NotNull
    private String userLogin;
    @NotNull
    private String userPassword;
    @NotNull
    private String userName;
    @NotNull
    private String userLastName;
    @NotNull
    private String userEMail;
    @NotNull
    private String userAddress;

    private Long userCarId;
    @NotNull
    private String role;
    @NotNull
    private String status;

}
