package com.example.carrental.service;

import com.example.carrental.domain.User.CarRentalUser;

public class UserTestValues {

    private static final Long userId = 2L;
    public static final CarRentalUser userWithNoCarRented = new CarRentalUser(userId, "Login","Password","name","lastName","aa@op.pl","address",null,"USER","ACTIVE");

    public static final CarRentalUser userWithCarRented = new CarRentalUser(userId, "Login","Password","name","lastName","aa@op.pl","address",2L,"USER","ACTIVE");

}
