package com.treinamento.EcommerceBackend.entities;

import com.treinamento.EcommerceBackend.entities.enums.TypeClientEnum;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ClientEntity implements Serializable {
    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String documentNumber;
    private TypeClientEnum typeClient;

    private List<AddressEntity> addressList = new ArrayList<>();

}
