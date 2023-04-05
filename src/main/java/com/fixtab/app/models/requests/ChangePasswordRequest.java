package com.fixtab.app.models.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ChangePasswordRequest {
    private String oldPassword;
    private String password;
}
