package com.fixtab.app.models.requests;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
