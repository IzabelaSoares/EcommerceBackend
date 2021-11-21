package com.treinamento.EcommerceBackend.entities;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Address")
public class AddressEntity implements Serializable {
    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String street;
    private Integer number;
    private String district;
    private String code;
    private String complement;

    @ManyToOne
    @JoinColumn(name = "IdCity", foreignKey = @ForeignKey(name = "FkClient"))
    private CityEntity city;

    @ManyToOne
    @JoinColumn(name = "IdClient", foreignKey = @ForeignKey(name = "FkClient"))
    private ClientEntity client;

    public AddressEntity() {
    }

    public AddressEntity(String street, Integer number, String district,
                         String code, String complement, CityEntity city, ClientEntity client) {
        this.id = null;
        this.street = street;
        this.number = number;
        this.district = district;
        this.code = code;
        this.complement = complement;
        this.city = city;
        this.client = client;
    }

    @Override
    public String toString() {
        return "AddressEntity{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", number=" + number +
                ", district='" + district + '\'' +
                ", code='" + code + '\'' +
                ", complement='" + complement + '\'' +
                ", client=" + client +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressEntity that = (AddressEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }
}

