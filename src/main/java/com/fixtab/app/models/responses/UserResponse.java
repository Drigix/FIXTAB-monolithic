package com.fixtab.app.models.responses;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserResponse {
    private String firstName;
    private String lastName;
    private String email;
}
