package com.example.SistemaBiblioteca.Dtos;

public class AuthenticationDto {

    private String token;

    public AuthenticationDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
