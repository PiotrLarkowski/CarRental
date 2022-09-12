package com.example.carrental.domain.User;

import com.example.carrental.domain.RentalOffice.CarRentalOffice;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Table(name="CarRentalUser")
public class User {

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

    @OneToMany(mappedBy = "user")
    private List<CarRentalOffice> rentalOfficeList;
}
