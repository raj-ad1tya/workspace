package com.examly.springappuser.model;

public class LoginDTO {
    private String token;

    public LoginDTO(String token) {
        this.token = token;
    }

    public String getJwt() {
        return token;
    }

    @Override
    public String toString() {
        return "LoginDTO [token=" + token + "]";
    }
}
