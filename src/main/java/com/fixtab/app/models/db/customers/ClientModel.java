package com.fixtab.app.models.db.customers;

import com.fixtab.app.models.db.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "client")
@Entity
public class ClientModel extends BaseEntity {
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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressid")
    private AddressModel address;
}
