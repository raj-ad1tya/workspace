package com.examly.springappuser.model;

public class LoginDTO {
    private String jwt;

    public LoginDTO(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
