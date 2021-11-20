package com.treinamento.EcommerceBackend.entities;

import com.treinamento.EcommerceBackend.entities.enums.TypeClientEnum;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class ClientEntity implements Serializable {
    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String documentNumber;
    private Integer typeClient;
    private Set<String> phones = new HashSet<>();

    private List<AddressEntity> addressList = new ArrayList<>();


    public ClientEntity(Integer id, String name, String email, String documentNumber, TypeClientEnum typeClient) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.documentNumber = documentNumber;
        this.typeClient = typeClient.getNumber();
    }

    public TypeClientEnum getTypeClient() {
        return TypeClientEnum.codigoParaEnum(typeClient);
    }

    public void setTypeClient(TypeClientEnum typeClient) {
        this.typeClient = typeClient.getNumber();
    }
}
