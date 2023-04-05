package com.fixtab.app.models.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class CreateEmployeeRequest {
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private Date birthDate;
    private String gender;
    private String pesel;
    private Integer roleId;
}
