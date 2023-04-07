package com.fixtab.app.models.requests;

import com.fixtab.app.models.db.customers.AddressModel;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ClientRequest {
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private Date birthDate;
    private String gender;
    private String pesel;
    private AddressModel address;
}
