package com.leonardofadul.springboot.ionic.learning.project.dto;

import com.leonardofadul.springboot.ionic.learning.project.dto.validations.ClientInsert;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@ClientInsert
public class ClientNewDTO implements Serializable {

    @NotEmpty(message = "Field 'name' required.")
    @Size(min = 5, max = 120, message = "Length must be between 5 and 120 characters.")
    private String name;

    @NotEmpty(message = "Field 'email' required.")
    @Email(message = "Invalid email.")
    private String email;

    @NotEmpty(message = "Field 'password' required.")
    private String password;

    @NotEmpty(message = "Field 'cpfOrCnpj' required.")
    private String cpfOrCnpj;

    private Integer clientType;

    @NotEmpty(message = "Field 'street' required.")
    private String street;

    @NotEmpty(message = "Field 'number' required.")
    private String number;

    private String complement;
    private String district;

    @NotEmpty(message = "Field 'zipCode' required.")
    private String zipCode;

    @NotEmpty(message = "Field 'telephone1' required.")
    private String telephone1;

    private String telephone2;
    private String telephone3;

    private Integer cityId;

    public ClientNewDTO(){
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

    public String getCpfOrCnpj() {
        return cpfOrCnpj;
    }

    public void setCpfOrCnpj(String cpfOrCnpj) {
        this.cpfOrCnpj = cpfOrCnpj;
    }

    public Integer getClientType() {
        return clientType;
    }

    public void setClientType(Integer clientType) {
        this.clientType = clientType;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getTelephone1() {
        return telephone1;
    }

    public void setTelephone1(String telephone1) {
        this.telephone1 = telephone1;
    }

    public String getTelephone2() {
        return telephone2;
    }

    public void setTelephone2(String telephone2) {
        this.telephone2 = telephone2;
    }

    public String getTelephone3() {
        return telephone3;
    }

    public void setTelephone3(String telephone3) {
        this.telephone3 = telephone3;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
