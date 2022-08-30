package com.example.carrental.domain;

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
public class Client {

    @Id
    private String clientId;

    private String clientName;

    private String clientLastName;

    private String clientEMail;

    private String clientAddress;
}
