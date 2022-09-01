package com.example.carrental.domain.User;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class User {

    @Id
    private String userId;

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
