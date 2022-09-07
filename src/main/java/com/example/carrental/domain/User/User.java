package com.example.carrental.domain.User;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name="CarRentalUser")
public class User {

    @Id
    private String userId;

    @Column(unique = true)
    private String userLogin;

    private String userPassword;

    private String userName;

    private String userLastName;

    @Column(unique = true)
    private String userEMail;

    private String userAddress;

    private String userCarId;

    private String role;

    private String status;
}
