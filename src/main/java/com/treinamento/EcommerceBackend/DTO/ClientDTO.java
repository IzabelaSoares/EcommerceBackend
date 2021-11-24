package com.treinamento.EcommerceBackend.DTO;

import com.treinamento.EcommerceBackend.entities.ClientEntity;
import com.treinamento.EcommerceBackend.services.validations.ClientUpdate;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Objects;

@ClientUpdate
public class ClientDTO implements Serializable {
    private static final long serialVersionUID = 1;
    private Integer id;

    @NotEmpty
    @Email(message = "Email inválido!")
    private String email;

    @NotEmpty
    @Length(min=5, max = 120, message = "O nome do cliente deve conter no mínimo 5 e no máximo 120 caracteres!")
    private String name;

    public ClientDTO() {
    }

    public ClientDTO(ClientEntity client) {
        this.id = client.getId();
        this.name = client.getName();
        this.email = client.getEmail();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientDTO that = (ClientDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
