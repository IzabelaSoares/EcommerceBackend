package com.treinamento.EcommerceBackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.treinamento.EcommerceBackend.entities.enums.ProfileUserEnum;
import com.treinamento.EcommerceBackend.entities.enums.TypeClientEnum;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import java.util.stream.Collectors;

@Entity
@Table(name = "Client", uniqueConstraints ={ @UniqueConstraint(name = "UkDocument", columnNames ="documentNumber"),
        @UniqueConstraint(name = "UkEmail", columnNames = {"email"})})
public class ClientEntity implements Serializable {

    private static final long serialVersionUID = 1;

    private String email;

    @JsonIgnore
    private String password;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer typeClient;

    @Column(nullable = false)
    private String documentNumber;

    @ElementCollection
    @CollectionTable(name = "Phone", foreignKey = @ForeignKey(name = "FkClient"))
    private Set<String> phonesList = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "Profiles")
    private Set<Integer> profileList = new HashSet<>();

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<AddressEntity> addressList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<OrderEntity> orderList = new ArrayList<>();

    public ClientEntity() {
        addProfile(ProfileUserEnum.CLIENT);
    }

    public ClientEntity(String name, String email, String documentNumber, TypeClientEnum typeClient, String password) {
        this.id = null;
        this.name = name;
        this.email = email;
        this.documentNumber = documentNumber;
        this.typeClient = typeClient.getNumber();
        this.password = password;
        addProfile(ProfileUserEnum.CLIENT);
    }

    public ClientEntity(Integer id, String name, String email, String password){
        this.id = id;
        this.name = name;
        this.email = email;
        this.documentNumber = null;
        this.typeClient = null;
        this.password = password;
        addProfile(ProfileUserEnum.CLIENT);
    }

    public TypeClientEnum getTypeClient() {
        return TypeClientEnum.toEnum(typeClient);
    }

    public void setTypeClient(TypeClientEnum typeClient) {
        this.typeClient = typeClient.getNumber();
    }

    public Set<ProfileUserEnum> getProfileList() {
        return profileList.stream().map(profile -> ProfileUserEnum.toEnum(profile)).collect(Collectors.toSet());
    }

    public void addProfile(ProfileUserEnum profile){
        profileList.add(profile.getNumber());
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<OrderEntity> getOrderList() {
        return orderList;
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
