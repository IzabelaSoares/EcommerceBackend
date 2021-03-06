package com.treinamento.EcommerceBackend.dto;

public class CredentialsDTO {

    private String password;
    private String email;

    public CredentialsDTO() {
    }

    public CredentialsDTO(String password, String email) {
        this.password = password;
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
