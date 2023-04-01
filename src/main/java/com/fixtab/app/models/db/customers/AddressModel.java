package com.fixtab.app.models.db.customers;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "address")
@Entity
public class AddressModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "addressid")
    private String addressId;

    @Column(name = "street")
    private String street;
    @Column(name = "city")
    private String city;
    @Column(name = "postcode")
    private String postCode;
    @Column(name = "flatnumber")
    private String flatNumber;
    @Column(name = "streetnumber")
    private String streetNumber;
    @Column(name = "country")
    private String country;


}
