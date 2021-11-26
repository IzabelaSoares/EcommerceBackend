package com.treinamento.EcommerceBackend.dto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.treinamento.EcommerceBackend.services.validations.ClientInsert;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@ClientInsert
public class ClientNewDTO implements Serializable {
    private static final long serialVersionUID = 1;

    @NotEmpty(message = "Preenchimento obrigatório!")
    @Email(message = "Email invalid!")
    private String email;

    @NotEmpty(message = "Preenchimento obrigatório!")
    private String password;

    @NotEmpty(message = "Preenchimento obrigatório!")
    @Length(min=5, max = 120, message = "O nome do cliente deve conter no mínimo 5 e no máximo 120 caracteres!")
    private String name;

    private Integer typeClient;

    @NotEmpty(message = "Preenchimento obrigatório!")
    private String documentNumber;

    private Integer number;

    private String complement;

    @NotEmpty(message = "Preenchimento obrigatório!")
    private String street;

    @NotEmpty(message = "Preenchimento obrigatório!")
    private String district;

    @NotEmpty(message = "Preenchimento obrigatório!")
    private String code;

    private String phone1;
    private String phone2;
    private String phone3;

    private Integer cityId;

    public ClientNewDTO() {
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getPhone3() {
        return phone3;
    }

    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTypeClient() {
        return typeClient;
    }

    public void setTypeClient(Integer typeClient) {
        this.typeClient = typeClient;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
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

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
