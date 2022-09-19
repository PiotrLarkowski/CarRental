package com.example.carrental.domain.User;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Table(name="CarRentalUser")
public class CarRentalUser {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String userLogin;

    private String userPassword;

    private String userName;

    private String userLastName;

    @Column(unique = true)
    private String userEMail;

    private String userAddress;

    private Long userCarId;

    private String role;

    private String status;
}
