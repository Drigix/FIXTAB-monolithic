package com.fixtab.app.models.responses;

import com.fixtab.app.models.db.customers.AddressModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ClientResponse {
    private int clientId;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private Date birthDate;
    private String gender;
    private String pesel;
    private AddressModel address;
}
