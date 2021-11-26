package com.treinamento.EcommerceBackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.treinamento.EcommerceBackend.entities.ClientEntity;
import com.treinamento.EcommerceBackend.services.validations.ClientUpdate;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@ClientUpdate
public class ClientDTO implements Serializable {
    private static final long serialVersionUID = 1;
    private Integer id;

    @NotEmpty(message = "Preenchimento obrigatório!")
    @Email(message = "Email inválid!")
    private String email;

    @NotEmpty(message = "Preenchimento obrigatório!")
    private String password;

    @NotEmpty(message = "Preenchimento obrigatório!")
    @Length(min=5, max = 120, message = "O nome do cliente deve conter no mínimo 5 e no máximo 120 caracteres!")
    private String name;

    public ClientDTO() {
    }

    public ClientDTO(ClientEntity client) {
        id = client.getId();
        name = client.getName();
        email = client.getEmail();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
