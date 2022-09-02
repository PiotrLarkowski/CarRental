package com.example.carrental.domain.User;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name="CarRentalUser")
public class User {

    @Id
    private String userId;

    private String userLogin;

    private String userPassword;

    private String userName;

    private String userLastName;

    private String userEMail;

    private String userAddress;

    private String userCarId;

    private String role;

    private String status;
}
