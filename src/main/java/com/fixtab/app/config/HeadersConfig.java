package com.fixtab.app.config;

import lombok.Getter;
import org.springframework.http.HttpHeaders;

@Getter
public class HeadersConfig {
    private HttpHeaders headers;

    public HeadersConfig(String headerName, String headerValue) {
        this.headers = new HttpHeaders();
        headers.add(headerName, headerValue);
    }
}
