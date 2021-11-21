package com.treinamento.EcommerceBackend.entities;

import com.treinamento.EcommerceBackend.entities.enums.TypeClientEnum;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Client")
public class ClientEntity implements Serializable {
    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private Integer typeClient;

    @Column(unique = true)

    private String documentNumber;


    @ElementCollection
    @CollectionTable(name = "Phone", foreignKey = @ForeignKey(name = "FkClient"))
    private Set<String> phonesList = new HashSet<>();

    @OneToMany(mappedBy = "client")
    private List<AddressEntity> addressList = new ArrayList<>();


    public ClientEntity(String name, String email, String documentNumber, TypeClientEnum typeClient) {
        this.id = null;
        this.name = name;
        this.email = email;
        this.documentNumber = documentNumber;
        this.typeClient = typeClient.getNumber();
    }

    public TypeClientEnum getTypeClient() {
        return TypeClientEnum.toEnum(typeClient);
    }

    public void setTypeClient(TypeClientEnum typeClient) {
        this.typeClient = typeClient.getNumber();
    }

    public Set<String> getPhonesList() {
        return phonesList;
    }

    public List<AddressEntity> getAddressList() {
        return addressList;
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

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    @Override
    public String toString() {
        return "ClientEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", documentNumber='" + documentNumber + '\'' +
                ", typeClient=" + typeClient +
                ", phones=" + phonesList +
                ", addressList=" + addressList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientEntity that = (ClientEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
