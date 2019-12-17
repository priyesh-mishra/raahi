package com.priyesh.raahi.model;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class AuthenticationResponse implements Serializable {
    private String jwt;

    public AuthenticationResponse(String jwt){
        this.jwt = jwt;
    }

    AuthenticationResponse() { }

    public String getJwt() {
        return jwt;
    }
}
