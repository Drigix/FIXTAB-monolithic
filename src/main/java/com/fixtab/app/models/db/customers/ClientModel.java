package com.fixtab.app.models.db.customers;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "client")
@Entity
public class ClientModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clientid")
    private int clientId;

    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "phonenumber")
    private String phoneNumber;
    @Column(name = "email")
    private String email;

    @Column(name = "birthdate")
    private Date birthDate;
    @Column(name = "gender")
    private String gender;
    @Column(name = "pesel")
    private String pesel;

    /*
    * OUR FOREIGN KEYS TO JOIN !!!
    * */
    @Column(name = "addressid")
    private Integer addressId;
}
