package com.fixtab.app.models.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EmployeeResponse {
    private int employeeId;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private Date birthDate;
    private String gender;
    private String pesel;
}