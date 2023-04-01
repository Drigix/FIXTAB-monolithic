package com.fixtab.app.models.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class LoginResponse {
    private String accessToken;
    private Date expirationDate;
}
